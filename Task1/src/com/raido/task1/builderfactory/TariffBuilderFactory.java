package com.raido.task1.builderfactory;

import com.raido.task1.builder.DomTariffBuilder;
import com.raido.task1.builder.SaxTariffBuilder;
import com.raido.task1.builder.StaxTariffBuilder;
import com.raido.task1.builder.TariffBuilder;

/**
 * Created by Raido_DDR on 8/8/2014.
 */
public class TariffBuilderFactory {

    private enum XmlParserType {
        DOM, SAX, STAX
    }

    public TariffBuilder createTariffBuilder(String parserType) {
        XmlParserType type = XmlParserType.valueOf(parserType);

        switch (type) {
        case DOM:
            return new DomTariffBuilder();
        case SAX:
            return new SaxTariffBuilder();
        case STAX:
            return new StaxTariffBuilder();
        default:
            throw new LogicalException("Unknown parser type requested.");
        }
    }
}
