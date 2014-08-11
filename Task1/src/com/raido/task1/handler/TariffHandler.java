package com.raido.task1.handler;

import com.raido.task1.entity.MobileTariff;
import jdk.internal.org.xml.sax.Attributes;
import jdk.internal.org.xml.sax.SAXException;
import jdk.internal.org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.List;

/**
 * Created by Raido_DDR on 8/8/2014.
 */
public class TariffHandler extends DefaultHandler {

    private List<MobileTariff> mobileTariffs;

    private MobileTariff currentTariff = null;

    private EnumSet
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

    }
}
