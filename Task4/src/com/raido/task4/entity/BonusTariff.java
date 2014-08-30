package com.raido.task4.entity;


public class BonusTariff extends NetworkingTariff {

    private int freeMinutesLimit;

    private int favouriteNumbersLimit;


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
                || (favouriteNumbersLimit != that.favouriteNumbersLimit) ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + freeMinutesLimit;
        result = 31 * result + favouriteNumbersLimit;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString())
                .append(", Free minutes: ")
                .append(freeMinutesLimit)
                .append(", Favourite numbers: ")
                .append(favouriteNumbersLimit);

        return sb.toString();
    }
}
