package com.raido.task4.comparator;

import com.raido.task4.entity.MobileTariff;

import java.util.Comparator;

public class MonthlyFeeTariffComparator implements Comparator<MobileTariff> {

    private static MonthlyFeeTariffComparator instance;

    private MonthlyFeeTariffComparator() {

    }

    public static MonthlyFeeTariffComparator getInstance() {
        if (null == instance) {
            instance = new MonthlyFeeTariffComparator();
        }

        return instance;
    }

    @Override
    public int compare(MobileTariff t1, MobileTariff t2) {
        float monthlyFee1 = t1.getMonthlyFee();
        float monthlyFee2 = t2.getMonthlyFee();

        if (monthlyFee1 < monthlyFee2) {
            return -1;
        } else if (monthlyFee2 < monthlyFee1) {
            return 1;
        } else  {
            return 0;
        }
    }
}
