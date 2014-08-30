package com.raido.task4.builder;

import com.raido.task4.exception.TechnicalException;
import com.raido.task4.handler.TariffHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class SaxTariffBuilder extends TariffBuilder {

    private TariffHandler handler;

    private XMLReader reader;

    public SaxTariffBuilder() throws TechnicalException {
        handler = new TariffHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.setFeature("http://apache.org/xml/features/validation/schema", Boolean.TRUE);
        } catch (SAXException e) {
            throw new TechnicalException(messageBundle.getString("sax_failed"));
        }

    }

    @Override
    public void buildTariffsSet(String sourceFileName)
            throws TechnicalException {

        try {
            reader.parse(sourceFileName);
        } catch (IOException e) {
            throw new TechnicalException(messageBundle.getString("file_not_found"));
        } catch (SAXException e) {
            throw new TechnicalException(messageBundle.getString("parsing_failed"));
        }
        mobileTariffs = handler.getMobileTariffs();
    }
}
