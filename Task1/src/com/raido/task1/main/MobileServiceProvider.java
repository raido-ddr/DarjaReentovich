package com.raido.task1.main;

import com.raido.task1.container.TariffLine;
import com.raido.task1.initializer.TariffLineInitializer;

import java.util.List;

public class MobileServiceProvider {

    public static final String LIST_MARKER = "-";

    public static String SORTING_MESSAGE =
            "Sorting tariffs by monthly fee...";

    private static final String SUBSCRIBERS_COUNT =
            "Total number of subscribers: ";

    private static final String EMPTY_SEARCH_RESULT =
            "No tariffs meeting your request found.";

    private static final String OUTGOING_CALL_CHARGE_TITLE =
            "Outgoing call charge: ";

    private static final String INTERNAL_CALL_CHARGE_TITLE =
            "Internal call charge: ";

    private static final String FREE_MINUTES_TITLE =
            "Tariffs offering free minutes: ";

    /*private static void printTariffs(TariffLine tariffLine) {
        List<MobileTariff> mobileTariffs = tariffLine.getMobileTariffs();

        if (null == mobileTariffs) {
            System.out.println();
            System.out.println(EMPTY_LINE_TITLE);
        }

        System.out.println();
        System.out.println(TARIFF_LINE_TITLE);

        for (MobileTariff tariff : mobileTariffs) {
            printTariffNameInList(tariff.getTariffName());
        }
    }*/

    private static void printTariffNameInList(String tariffName) {
        System.out.println("\t" + LIST_MARKER + ' '
                + tariffName);
    }

    private static void printTariffSearchResults(List<String> tariffsNames) {
        if( (null == tariffsNames) || (tariffsNames.isEmpty()) ) {
            System.out.println(EMPTY_SEARCH_RESULT);
        } else {
            for(String tariffName : tariffsNames) {
                printTariffNameInList(tariffName);
            }
        }
    }

    private static void printTotalSubscribersCount(TariffLine tariffLine) {
        System.out.println(SUBSCRIBERS_COUNT
                + tariffLine.getTotalSubscribersCount());
    }

    public static void printTariffsByInternalCallCharge(TariffLine tariffLine,
             float minCharge, float maxCharge) {

        List<String> eligibleTariffsNames =
                tariffLine.findTariffsByInternalCallCharge(minCharge, maxCharge);

        System.out.println();
        System.out.println(INTERNAL_CALL_CHARGE_TITLE + minCharge +
                ".. " + maxCharge);

        printTariffSearchResults(eligibleTariffsNames);
    }

    public static void printTariffByOutgoingCallCharge(TariffLine tariffLine,
            float minCharge, float maxCharge) {

        List<String> eligibleTariffsNames =
                tariffLine.findTariffByOutgoingCallCharge(minCharge, maxCharge);

        System.out.println();
        System.out.println(OUTGOING_CALL_CHARGE_TITLE + minCharge +
                ".. " + maxCharge);

        printTariffSearchResults(eligibleTariffsNames);
    }

    public static void printTariffWithFreeMinutes(TariffLine tariffLine) {

        List<String> eligibleTariffsNames =
                tariffLine.findTariffWithFreeMinutes();

        System.out.println();
        System.out.println(FREE_MINUTES_TITLE);

        printTariffSearchResults(eligibleTariffsNames);
    }

    public static void main(String[] args) {

        TariffLine tariffLine = TariffLineInitializer.CreateTariffLine();
        TariffLineInitializer.populateTariffLine(tariffLine);

        printTotalSubscribersCount(tariffLine);

        System.out.println(tariffLine);
        System.out.println(SORTING_MESSAGE);
        tariffLine.sortTariffsByMonthlyFee();
        System.out.println(tariffLine);

        printTariffByOutgoingCallCharge(tariffLine, 2f, 6f);
        printTariffsByInternalCallCharge(tariffLine, 2.5f, 3.0f);
        printTariffWithFreeMinutes(tariffLine);
    }
}
