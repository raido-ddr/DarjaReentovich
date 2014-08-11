package com.raido.task1.builder;

import com.raido.task1.entity.MobileTariff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Raido_DDR on 8/8/2014.
 */
public abstract class TariffBuilder {

    protected List<MobileTariff> mobileTariffs;

    public TariffBuilder() {
        mobileTariffs = new ArrayList<>();
    }

    public List<MobileTariff> getMobileTariffs() {
        return Collections.unmodifiableList(mobileTariffs);
    }

    abstract public void buildTariffsList();

}
