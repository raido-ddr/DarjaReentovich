package com.raido.task4.builderfactory;

import com.raido.task4.builder.DomTariffBuilder;
import com.raido.task4.builder.SaxTariffBuilder;
import com.raido.task4.builder.StaxTariffBuilder;
import com.raido.task4.builder.TariffBuilder;
import com.raido.task4.exception.LogicalException;
import com.raido.task4.exception.TechnicalException;

import java.util.ResourceBundle;

public class TariffBuilderFactory {

    public TariffBuilder createTariffBuilder(String parserType)
            throws TechnicalException, LogicalException {

        switch (parserType.toUpperCase()) {
        case "DOM":
            return new DomTariffBuilder();
        case "SAX":
            return new SaxTariffBuilder();
        case "STAX":
            return new StaxTariffBuilder();
        default:
            ResourceBundle messageBundle =
                    ResourceBundle.getBundle("exception_message");
            throw new LogicalException(messageBundle.getString("unknown_parser"));
        }
    }
}
