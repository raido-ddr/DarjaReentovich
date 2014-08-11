package com.raido.task1.container;

import com.raido.task1.entity.*;
import com.raido.task1.comparator.MonthlyFeeTariffComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TariffLine {

    private static final String TARIFF_LINE_TITLE =
            "Tariff plans currently available:";

    private static final String LIST_MARKER = "-";

    private static final String EMPTY_LINE_TITLE =
            "Tariff line is empty.";

    private static final String NEW_LINE_PROPERTY =
            "line.separator";

    private List<MobileTariff> mobileTariffs;

    public TariffLine() {
        mobileTariffs = new ArrayList<MobileTariff>();
    }

    public List<MobileTariff> getMobileTariffs() {
        return Collections.unmodifiableList(mobileTariffs);
    }

    public void addTariff(MobileTariff tariff) {
        mobileTariffs.add(tariff);
    }

    public void sortTariffsByMonthlyFee() {
        Collections.sort(mobileTariffs, MonthlyFeeTariffComparator.getInstance());
    }

    public int getTotalSubscribersCount() {
        int totalSubscribersCount = 0;
        for (MobileTariff tariff : mobileTariffs) {
            totalSubscribersCount += tariff.getSubscribersCount();
        }

        return totalSubscribersCount;
    }

    public List<String> findTariffsByInternalCallCharge(float minCharge,
        float maxCharge) {

        List<String> eligibleTariffsNames = new ArrayList<>();
        for (MobileTariff tariff : mobileTariffs) {
            if (isTariffLocal(tariff)) {
                LocalTariff localTariff = (LocalTariff) tariff;

                if ( valueFitsRange(localTariff.getInternalCallCharge(),
                    minCharge, maxCharge) ) {
                    eligibleTariffsNames.add(localTariff.getTariffName());
                }
            }
        }

        return eligibleTariffsNames;
    }

    public List<String> findTariffByOutgoingCallCharge(float minCharge,
        float maxCharge) {

        List<String> eligibleTariffsNames = new ArrayList<>();
        for (MobileTariff tariff : mobileTariffs) {
            if (isTariffRoaming(tariff)) {
                RoamingTariff roamingTariff = (RoamingTariff) tariff;

                if ( valueFitsRange(roamingTariff.getOutgoingCallCharge(),
                        minCharge, maxCharge) ) {
                    eligibleTariffsNames.add(roamingTariff.getTariffName());
                }
            }
        }

        return eligibleTariffsNames;
    }

    public List<String> findTariffWithFreeMinutes() {

        List<String> eligibleTariffsNames = new ArrayList<>();
        for (MobileTariff tariff : mobileTariffs) {
            if (tariffHasFreeMinutes(tariff)) {
               // RoamingTariff roamingTariff = (RoamingTariff) tariff;
               eligibleTariffsNames.add(tariff.getTariffName());
            }
        }

        return eligibleTariffsNames;
    }

    private boolean tariffHasFreeMinutes(MobileTariff tariff) {
        if (tariff instanceof BonusTariff) {
            BonusTariff bonusTariff = (BonusTariff) tariff;

            return bonusTariff.hasFreeMinutes();
        } else {
            return false;
        }
    }

    private boolean valueFitsRange(float value,
        float lowerLimit, float upperLimit) {

        return ((lowerLimit <= value)
                && (value <= upperLimit));
    }

    private boolean isTariffLocal(MobileTariff tariff) {
        return (tariff.getClass() == LocalTariff.class);
    }

    private boolean isTariffRoaming(MobileTariff tariff) {
        return (tariff.getClass() == RoamingTariff.class);
    }

    private boolean isTariffBonus(MobileTariff tariff) {
        return (tariff.getClass() == BonusTariff.class);
    }

    private boolean isTariffNetworking(MobileTariff tariff) {
        return (tariff.getClass() == NetworkingTariff.class);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ( (null == obj) || (getClass() != obj.getClass()) ) {
            return false;
        }

        TariffLine that = (TariffLine) obj;

        Iterator iterator = mobileTariffs.iterator();
        for (MobileTariff tariff : that.mobileTariffs) {
            if ( !tariff.equals(iterator.next()) ) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return mobileTariffs.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty(NEW_LINE_PROPERTY);

        sb.append(newLine);

        if (!mobileTariffs.isEmpty()) {
            sb.append(TARIFF_LINE_TITLE).append(newLine);

            for (MobileTariff tariff : mobileTariffs) {
                sb.append('\t')
                        .append(LIST_MARKER)
                        .append(' ')
                        .append(tariff.getTariffName())
                        .append(newLine);
            }
        } else {
            sb.append(EMPTY_LINE_TITLE).append(newLine);
        }

        return sb.toString();
    }

}
