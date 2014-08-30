package com.raido.task4.builder;

import com.raido.task4.entity.MobileTariff;
import com.raido.task4.exception.LogicalException;
import com.raido.task4.exception.TechnicalException;

import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public abstract class TariffBuilder {

    protected static final ResourceBundle messageBundle =
            ResourceBundle.getBundle("exception_message");

    protected Set<MobileTariff> mobileTariffs;

    public TariffBuilder() {
        mobileTariffs = new HashSet<>();
    }

    public Set<MobileTariff> getMobileTariffs() {
        return Collections.unmodifiableSet(mobileTariffs);
    }

    public abstract void buildTariffsSet(String sourceFileName)
            throws TechnicalException, LogicalException;
}
