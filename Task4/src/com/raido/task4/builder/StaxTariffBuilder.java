package com.raido.task4.builder;

import com.raido.task4.entity.*;
import com.raido.task4.exception.LogicalException;
import com.raido.task4.exception.TechnicalException;
import com.raido.task4.factory.TariffFactory;
import com.raido.task4.tariffenum.TariffProperty;
import com.raido.task4.tariffstatus.TariffStatus;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class StaxTariffBuilder extends TariffBuilder {

    private static final HashSet<String> complexElements = new HashSet<>(Arrays.asList(
            "local-tariff",
            "roaming-tariff",
            "networking-tariff",
            "guest-tariff",
            "bonus-tariff"
    ));

    private XMLInputFactory xmlInputFactory;

    public StaxTariffBuilder() {
        xmlInputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildTariffsSet(String sourceFileName)
            throws TechnicalException, LogicalException {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String elementName;

        try {
            inputStream = new FileInputStream(new File(sourceFileName));
            reader = xmlInputFactory.createXMLStreamReader(inputStream);

            while(reader.hasNext()) {
                int type = reader.next();

                if(type == XMLStreamConstants.START_ELEMENT) {
                    elementName = reader.getLocalName();
                    if(complexElements.contains(elementName)) {
                        MobileTariff tariff = buildTariff(elementName, reader);
                        mobileTariffs.add(tariff);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new LogicalException(messageBundle.getString("file_not_found"));
        } catch (XMLStreamException e1) {
            throw new TechnicalException(messageBundle.getString("parsing_failed"));
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new TechnicalException(messageBundle.getString("closing_failed"));
            }
        }
    }

    private MobileTariff buildTariff(String tagName, XMLStreamReader reader)
            throws LogicalException, XMLStreamException {
        String tariffType = tagName.replace("-", "_").toUpperCase();
        MobileTariff tariff = TariffFactory.createTariff(tariffType);

        if(reader.getAttributeCount() > 0) {
            TariffStatus status =
                    TariffStatus.valueOf(reader.getAttributeValue(0).toUpperCase());
            tariff.setStatus(status);
        }

        String localName;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
            case XMLStreamConstants.START_ELEMENT:
                localName = reader.getLocalName();
                TariffProperty propertyTag =
                        TariffProperty.valueOf(localName.replace("-", "_").toUpperCase());
                setTariffProperty(tariff, propertyTag, reader);
                break;
            case XMLStreamConstants.END_ELEMENT:
                localName = reader.getLocalName();
                if(complexElements.contains(localName)) {
                    return tariff;
                }
                break;
            }
        }

        throw new LogicalException(messageBundle.getString("unknown_tag"));
    }

    private void setTariffProperty(MobileTariff tariff,
            TariffProperty propertyTag, XMLStreamReader reader) throws XMLStreamException {

        String tagText = getXmlText(reader);
        switch (propertyTag) {
        case ID:
            tariff.setId(tagText);
            break;
        case NAME:
            tariff.setTariffName(tagText);
            break;
        case MONTHLY_FEE:
            tariff.setMonthlyFee(Float.parseFloat(tagText));
            break;
        case SMS_CHARGE:
            tariff.setSmsCharge(Float.parseFloat(tagText));
            break;
        case INTERNAL_CHARGE:
            ((LocalTariff) tariff)
                    .setInternalCallCharge(Float.parseFloat(tagText));
            break;
        case EXTERNAL_CHARGE:
            ((LocalTariff) tariff)
                    .setExternalCallCharge(Float.parseFloat(tagText));
            break;
        case INCOMING_CHARGE:
            ((RoamingTariff) tariff)
                    .setIncomingCallCharge(Float.parseFloat(tagText));
            break;
        case OUTGOING_CHARGE:
            ((RoamingTariff) tariff)
                    .setOutgoingCallCharge(Float.parseFloat(tagText));
            break;
        case CONNECTION_SPEED:
            ((NetworkingTariff) tariff)
                    .setConnectionSpeed(Float.parseFloat(tagText));
            break;
        case STAY_LENGTH_DISCOUNT:
            ((GuestTariff) tariff)
                    .setStayLengthBasedDiscount(Float.parseFloat(tagText));
            break;
        case FREE_MINUTES:
            ((BonusTariff) tariff)
                    .setFreeMinutesLimit(Integer.parseInt(tagText));
            break;
        case FAVOURITE_NUMBERS:
            ((BonusTariff) tariff)
                    .setFavouriteNumbersLimit(Integer.parseInt(tagText));
            break;
        default:
            throw new EnumConstantNotPresentException(propertyTag.getDeclaringClass(),
                    propertyTag.name());
        }
    }

    private String getXmlText(XMLStreamReader reader)
            throws XMLStreamException {
        String xmlText = null;
        if(reader.hasNext()) {
            reader.next();
            xmlText = reader.getText();
        }

        return xmlText;
    }


}
