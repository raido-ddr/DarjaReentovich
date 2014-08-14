package com.raido.task2.chaincreator;

import com.raido.task2.exception.TechnicalException;
import com.raido.task2.handler.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class HandlerChainCreator {

    private static final String EXCEPTION_MESSAGE_FILE_NAME =
            "exception_message";

    private ElementHandler currentHandler;

    private static HandlerChainCreator instance;

    private HandlerChainCreator() {

    }

    public static HandlerChainCreator getInstance() {
        if(instance == null) {
            instance = new HandlerChainCreator();
        }

        return instance;
    }

    public ElementHandler createHandlerChain()
            throws TechnicalException {

        Document document = getDocumentFromFile("resources/xml/handlers.xml");
        document.getDocumentElement().normalize();

        NodeList nodeList =
                document.getElementsByTagName("handler");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String handlerName =
                        node.getTextContent().trim();

                ElementHandler handler = createHandler(handlerName);

                if(currentHandler != null) {
                    handler.setSuccessor(currentHandler);
                }

                currentHandler = handler;
            }
        }

       return currentHandler;
    }

    private ElementHandler createHandler(String handlerName)
            throws TechnicalException {
        Object handler;

        try {
            handler = Class.forName(handlerName).newInstance();
        } catch (InstantiationException | IllegalAccessException
                 | ClassNotFoundException e) {
            ResourceBundle messageBundle =
                    ResourceBundle.getBundle(EXCEPTION_MESSAGE_FILE_NAME);
            throw new TechnicalException(messageBundle.getString("unknown_handler"));
        }

        return (ElementHandler) handler;
    }

    private Document getDocumentFromFile(String sourceFileName)
            throws TechnicalException {

        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            ResourceBundle messageBundle =
                    ResourceBundle.getBundle(EXCEPTION_MESSAGE_FILE_NAME);
            throw new TechnicalException(messageBundle.getString("xml_config_error"));
        }

        Document document;
        try {
            File file = new File(sourceFileName);
            document =
                    builder.parse(file);

        } catch (SAXException | IOException e1) {
            ResourceBundle messageBundle =
                    ResourceBundle.getBundle(EXCEPTION_MESSAGE_FILE_NAME);
            throw new TechnicalException(messageBundle.getString("handler_chain_error"));
        }
        return document;
    }

}
