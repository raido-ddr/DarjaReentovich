package com.raido.task4.entity;

import com.raido.task4.tariffstatus.TariffStatus;

public abstract class MobileTariff {

    private String id;

    private String tariffName;

    private TariffStatus status;

    private float monthlyFee;

    private float smsCharge;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public TariffStatus getStatus() {
        return status;
    }

    public void setStatus(TariffStatus status) {
        this.status = status;
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
                || (status != that.status)
                || (!tariffName.equals(that.tariffName)) ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = tariffName.hashCode();
        result = 31 * result;
        result = 31 * result +
                (monthlyFee != +0.0f ? Float.floatToIntBits(monthlyFee) : 0);
        result = 31 * result +
                (smsCharge != +0.0f ? Float.floatToIntBits(smsCharge) : 0);
        result = 31 * result +
                ((status != null) ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");


        sb.append(newLine)
            .append("Tariff name: '")
            .append(tariffName)
            .append('\'')
            .append(", Status: ")
            .append(status)
            .append(", Monthly fee: ")
            .append(monthlyFee)
            .append(", Sms charge: ")
            .append(smsCharge);

        return sb.toString();
    }
}
