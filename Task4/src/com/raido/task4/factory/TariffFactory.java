package com.raido.task4.factory;

import com.raido.task4.entity.*;
import com.raido.task4.exception.LogicalException;
import com.raido.task4.tariffenum.TariffType;

import java.util.ResourceBundle;

public final class TariffFactory {

    private TariffFactory() {

    }

    public static MobileTariff createTariff(String tariffTypeName)
            throws LogicalException {

        TariffType tariffType;

        try {
            tariffType = TariffType.valueOf(tariffTypeName);
        } catch (EnumConstantNotPresentException e) {
            ResourceBundle messageBundle =
                    ResourceBundle.getBundle("exception_message");
            throw new LogicalException(messageBundle.getString("unknown_tariff"));
        }

        switch (tariffTypeName) {
            case "LOCAL_TARIFF":
                return new LocalTariff();
            case "ROAMING_TARIFF":
                return new RoamingTariff();
            case "NETWORKING_TARIFF":
                return new NetworkingTariff();
            case "GUEST_TARIFF":
                return new GuestTariff();
            case "BONUS_TARIFF":
                return new BonusTariff();
            default:
               return null;
               /*
                This case is eliminated by exception possibly
                thrown in course of obtaining tariff type
                */
        }
    }

}
