package com.raido.task1.entity;


import java.util.Objects;

public class BonusTariff extends NetworkingTariff {

    private int freeMinutesLimit;

    private int favouriteNumbersLimit;

    private float extraTrafficAmount;

    public BonusTariff(int id, String tariffName, int subscribersCount, float monthlyFee,
        float smsCharge, float internalCallCharge, float externalCallCharge,
        float connectionSpeed, float trafficLimit,
        int freeMinutesLimit, int favouriteNumbersLimit,
        float extraTrafficAmount) {

        super(id, tariffName, subscribersCount, monthlyFee, smsCharge,
                internalCallCharge, externalCallCharge,
                connectionSpeed, trafficLimit);

        this.freeMinutesLimit = freeMinutesLimit;
        this.favouriteNumbersLimit = favouriteNumbersLimit;
        this.extraTrafficAmount = extraTrafficAmount;
    }

    public int getFreeMinutesLimit() {
        return freeMinutesLimit;
    }

    public void setFreeMinutesLimit(int freeMinutesLimit) {
        this.freeMinutesLimit = freeMinutesLimit;
    }

    public int getFavouriteNumbersLimit() {
        return favouriteNumbersLimit;
    }

    public void setFavouriteNumbersLimit(int favouriteNumbersLimit) {
        this.favouriteNumbersLimit = favouriteNumbersLimit;
    }

    public float getExtraTrafficAmount() {
        return extraTrafficAmount;
    }

    public void setExtraTrafficAmount(float extraTrafficAmount) {
        this.extraTrafficAmount = extraTrafficAmount;
    }

    public boolean hasFreeMinutes() {
        return (freeMinutesLimit > 0) ?
                true :
                false;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if( (null == obj) || (getClass() != obj.getClass()) ) {
            return false;
        }

        if(!super.equals(obj)) {
            return false;
        }

        BonusTariff that = (BonusTariff) obj;

        if( (freeMinutesLimit != that.freeMinutesLimit)
                || (favouriteNumbersLimit != that.favouriteNumbersLimit)
                || (Float.compare(extraTrafficAmount, that.extraTrafficAmount)) != 0 ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + freeMinutesLimit;
        result = 31 * result + favouriteNumbersLimit;
        result = 31 * result +
                (extraTrafficAmount != +0.0f ? Float.floatToIntBits(extraTrafficAmount) : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString())
                .append(", Free minutes: ")
                .append(freeMinutesLimit)
                .append(", Favourite numbers: ")
                .append(favouriteNumbersLimit)
                .append(", Extra traffic: ")
                .append(extraTrafficAmount);

        return sb.toString();
    }
}
