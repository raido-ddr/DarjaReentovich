package com.raido.task1.entity;

public abstract class MobileTariff {

    private int id;

    private String tariffName;

    private int subscribersCount;

    private float monthlyFee;

    private float smsCharge;



    public MobileTariff(int id, String tariffName, int subscribersCount,
            float monthlyFee, float smsCharge) {
        this.id = id;
        this.tariffName = tariffName;
        this.subscribersCount = subscribersCount;
        this.monthlyFee = monthlyFee;
        this.smsCharge = smsCharge;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }

    public int getSubscribersCount() {
        return subscribersCount;
    }

    public void setSubscribersCount(int subscribersCount) {
        this.subscribersCount = subscribersCount;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public float getSmsCharge() {
        return smsCharge;
    }

    public void setSmsCharge(float smsCharge) {
        this.smsCharge = smsCharge;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ( (null == obj) || (getClass() != obj.getClass()) ) {
            return false;
        }

        MobileTariff that = (MobileTariff) obj;

        if ( (Float.compare(that.monthlyFee, monthlyFee) != 0)
                || (Float.compare(that.smsCharge, smsCharge) != 0)
                || (subscribersCount != that.subscribersCount)
                || (!tariffName.equals(that.tariffName)) ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = tariffName.hashCode();
        result = 31 * result + subscribersCount;
        result = 31 * result +
                (monthlyFee != +0.0f ? Float.floatToIntBits(monthlyFee) : 0);
        result = 31 * result +
                (smsCharge != +0.0f ? Float.floatToIntBits(smsCharge) : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        sb.append("* Mobile Tariff  *")
                .append(newLine)
                .append("Tariff name: '")
                .append(tariffName)
                .append('\'')
                .append(", Number of subscribers: ")
                .append(subscribersCount)
                .append(", Monthly fee: ")
                .append(monthlyFee)
                .append(", Sms charge: ")
                .append(smsCharge);

        return sb.toString();
    }
}
