package com.raido.task1.entity;


public class GuestTariff extends LocalTariff {

    private float stayLengthBasedDiscount;

    public GuestTariff(int id, String tariffName, int subscribersCount, float monthlyFee,
        float smsCharge, float internalCallCharge,
        float externalCallCharge, float stayLengthBasedDiscount) {

        super(id, tariffName, subscribersCount, monthlyFee, smsCharge,
                internalCallCharge, externalCallCharge);

        this.stayLengthBasedDiscount = stayLengthBasedDiscount;
    }

    public float getStayLengthBasedDiscount() {
        return stayLengthBasedDiscount;
    }

    public void setStayLengthBasedDiscount(float stayLengthBasedDiscount) {
        this.stayLengthBasedDiscount = stayLengthBasedDiscount;
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

        GuestTariff that = (GuestTariff) obj;

        if (Float.compare(that.stayLengthBasedDiscount,
                stayLengthBasedDiscount) != 0) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result +
                (stayLengthBasedDiscount != +0.0f
                        ? Float.floatToIntBits(stayLengthBasedDiscount)
                        : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString())
                .append(", Stay length based discount: ")
                .append(stayLengthBasedDiscount);

        return sb.toString();
    }

}
