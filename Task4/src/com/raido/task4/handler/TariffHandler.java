package com.raido.task4.handler;

import com.raido.task4.entity.*;
import com.raido.task4.exception.LogicalException;
import com.raido.task4.factory.TariffFactory;
import com.raido.task4.tariffenum.TariffProperty;
import com.raido.task4.tariffenum.TariffType;
import com.raido.task4.tariffstatus.TariffStatus;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;


public class TariffHandler extends DefaultHandler {

    private static final String ROOT_TAG_NAME = "tariffs";

    private TariffProperty currentTag = null;

    private Set<MobileTariff> mobileTariffs;

    private MobileTariff currentTariff = null;

    public TariffHandler() {
        mobileTariffs = new HashSet<>();
    }

    public Set<MobileTariff> getMobileTariffs() {
        return mobileTariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (isTariffTag(localName)) {
            //When encountered a new tariff element
            try {
                String tariffTypeName = localName.replace("-", "_").toUpperCase();

                currentTariff = TariffFactory.createTariff(tariffTypeName);

                if(attributes.getLength() > 0) {
                    currentTariff.setStatus(TariffStatus
                            .valueOf(attributes.getValue(0).toUpperCase()));
                }
            } catch (LogicalException e) {
                ResourceBundle messageBundle =
                        ResourceBundle.getBundle("exception_message");
                throw new SAXException(messageBundle.getString("unknown_tag"));
            }

            if(attributes.getLength() > 0) {
                currentTariff.setStatus(TariffStatus
                        .valueOf(attributes.getValue(0).toUpperCase()));
            }

        } else if(! ROOT_TAG_NAME.equals(localName)) {
            //When encountered a tariff property description element
            String tagName = localName.replace("-", "_").toUpperCase();

            try {
                currentTag = TariffProperty.valueOf(tagName);
            } catch (EnumConstantNotPresentException e) {
                ResourceBundle messageBundle =
                        ResourceBundle.getBundle("exception_message");
                throw new SAXException(messageBundle.getString("unknown_tag"));
            }

        }

    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if(isTariffTag(localName)) {
            mobileTariffs.add(currentTariff);
        }
    }

    @Override
    public void characters(char[] chars, int start, int length)
            throws SAXException {
        String tagText = new String(chars, start, length);

        if(currentTag != null) {
            setCurrentTariffProperty(tagText);
            currentTag = null;
        }

    }

    private void setCurrentTariffProperty(String tagText) {
        switch (currentTag) {
        case ID:
            currentTariff.setId(tagText);
            break;
        case NAME:
            currentTariff.setTariffName(tagText);
            break;
        case MONTHLY_FEE:
            currentTariff.setMonthlyFee(Float.parseFloat(tagText));
            break;
        case SMS_CHARGE:
            currentTariff.setSmsCharge(Float.parseFloat(tagText));
            break;
        case INTERNAL_CHARGE:
            ((LocalTariff) currentTariff)
                    .setInternalCallCharge(Float.parseFloat(tagText));
            break;
        case EXTERNAL_CHARGE:
            ((LocalTariff) currentTariff)
                    .setExternalCallCharge(Float.parseFloat(tagText));
            break;
        case INCOMING_CHARGE:
            ((RoamingTariff) currentTariff)
                    .setIncomingCallCharge(Float.parseFloat(tagText));
            break;
        case OUTGOING_CHARGE:
            ((RoamingTariff) currentTariff)
                    .setOutgoingCallCharge(Float.parseFloat(tagText));
            break;
        case CONNECTION_SPEED:
            ((NetworkingTariff) currentTariff)
                    .setConnectionSpeed(Float.parseFloat(tagText));
            break;
        case STAY_LENGTH_DISCOUNT:
            ((GuestTariff) currentTariff)
                    .setStayLengthBasedDiscount(Float.parseFloat(tagText));
            break;
        case FREE_MINUTES:
            ((BonusTariff) currentTariff)
                    .setFreeMinutesLimit(Integer.parseInt(tagText));
            break;
        case FAVOURITE_NUMBERS:
            ((BonusTariff) currentTariff)
                    .setFavouriteNumbersLimit(Integer.parseInt(tagText));
            break;
        default:
            throw new EnumConstantNotPresentException(currentTag.getDeclaringClass(),
                    currentTag.name());
        }
    }

    private boolean isTariffTag(String tagName) {
        return TariffType.getTagNameSet().contains(tagName);
    }


}
