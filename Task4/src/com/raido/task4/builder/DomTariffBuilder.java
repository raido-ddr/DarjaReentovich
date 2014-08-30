package com.raido.task4.builder;


import com.raido.task4.entity.*;
import com.raido.task4.exception.LogicalException;
import com.raido.task4.exception.TechnicalException;
import com.raido.task4.factory.TariffFactory;
import com.raido.task4.tariffstatus.TariffStatus;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class DomTariffBuilder extends TariffBuilder {

    private static final String ROOT_TAG_NAME = "tariffs";

    private DocumentBuilder documentBuilder;

    public DomTariffBuilder() throws TechnicalException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new TechnicalException(messageBundle.getString("configuration_failed"));
        }
    }

    @Override
    public void buildTariffsSet(String sourceFileName)
            throws TechnicalException, LogicalException {
        Document document;

        try {
            document = documentBuilder.parse(sourceFileName);
            Element root = document.getDocumentElement();
            root.normalize();

            NodeList nodeList = root.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (isImmediateRootChild(node)
                        && (node.getNodeType() == Node.ELEMENT_NODE)) {
                    Element element = (Element) node;
                    MobileTariff tariff = buildTariff(element);
                    mobileTariffs.add(tariff);
                }
            }
        } catch (SAXException e) {
           throw new TechnicalException(messageBundle.getString("parsing_failed"));
        } catch (IOException e) {
            throw new TechnicalException(messageBundle.getString("file_not_found"));
        }

    }

    private boolean isImmediateRootChild(Node node) {
        return ROOT_TAG_NAME.equals(node.getParentNode().getNodeName());
    }

    private MobileTariff buildTariff(Element tariffElement)
            throws LogicalException {
        String elementName = tariffElement.getTagName();
        String tariffType = elementName.replace("-", "_").toUpperCase();

        MobileTariff tariff = TariffFactory.createTariff(tariffType);
        String statusValue = tariffElement.getAttribute("status").toUpperCase();
        if (! statusValue.isEmpty()) {
            tariff.setStatus(TariffStatus.valueOf(statusValue));
        }

        Node idNode = getChildNode(tariffElement, "id");
        if (idNode != null) {
            tariff.setId(idNode.getTextContent());
        }

        Node nameNode = getChildNode(tariffElement, "name");
        if (nameNode != null) {
            tariff.setTariffName(nameNode.getTextContent());
        }

        Node feeNode = getChildNode(tariffElement, "monthly-fee");
        if (feeNode != null) {
            float monthlyFee = Float.parseFloat(feeNode.getTextContent());
            tariff.setMonthlyFee(monthlyFee);
        }

        Node smsChargeNode = getChildNode(tariffElement, "sms-charge");
        if (smsChargeNode != null) {
            float smsCharge = Float.parseFloat(smsChargeNode.getTextContent());
            tariff.setSmsCharge(smsCharge);
        }

        Node internalNode = getChildNode(tariffElement, "internal-charge");
        if (internalNode != null) {
            float internalCharge =
                    Float.parseFloat(internalNode.getTextContent());
            ((LocalTariff) tariff).setInternalCallCharge(internalCharge);
        }

        Node externalNode = getChildNode(tariffElement, "external-charge");
        if (externalNode != null) {
            float externalCharge =
                    Float.parseFloat(externalNode.getTextContent());
            ((LocalTariff) tariff).setExternalCallCharge(externalCharge);
        }

        Node incomingNode = getChildNode(tariffElement, "incoming-charge");
        if (incomingNode != null) {
            float incomingCharge =
                    Float.parseFloat(incomingNode.getTextContent());
            ((RoamingTariff) tariff).setIncomingCallCharge(incomingCharge);
        }

        Node outgoingNode = getChildNode(tariffElement, "outgoing-charge");
        if (outgoingNode != null) {
            float outgoingCharge =
                    Float.parseFloat(outgoingNode.getTextContent());
            ((RoamingTariff) tariff).setOutgoingCallCharge(outgoingCharge);
        }

        Node connectionNode = getChildNode(tariffElement, "connection-speed");
        if (connectionNode != null) {
            float connectionSpeed =
                    Float.parseFloat(connectionNode.getTextContent());
            ((NetworkingTariff) tariff).setConnectionSpeed(connectionSpeed);
        }

        Node discountNode = getChildNode(tariffElement, "stay-length-discount");
        if(discountNode != null) {
            float discount = Float.parseFloat(discountNode.getTextContent());
            ((GuestTariff) tariff).setStayLengthBasedDiscount(discount);
        }

        Node freeMinutesNode = getChildNode(tariffElement, "free-minutes");
        if(freeMinutesNode != null) {
            int freeMinutesLimit =
                    Integer.parseInt(freeMinutesNode.getTextContent());
            ((BonusTariff) tariff).setFreeMinutesLimit(freeMinutesLimit);

        }

        Node favouriteNumbersNode = getChildNode(tariffElement, "favourite-numbers");
        if(favouriteNumbersNode != null) {
            int favouriteNumbersLimit =
                    Integer.parseInt(favouriteNumbersNode.getTextContent());
            ((BonusTariff) tariff).setFavouriteNumbersLimit(favouriteNumbersLimit);
        }

        return tariff;
    }

    private Node getChildNode(Element parent, String childName) {
        NodeList nodeList = parent.getElementsByTagName(childName);
        if(nodeList.getLength() > 0) {
            return nodeList.item(0);
        } else {
            return null;
        }
    }

}
