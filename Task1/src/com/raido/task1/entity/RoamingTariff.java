package com.raido.task1.entity;

public class RoamingTariff extends MobileTariff {

    private float incomingCallCharge;

    private float outgoingCallCharge;

    public RoamingTariff(int id, String tariffName, int subscribersCount, float monthlyFee,
        float smsCharge, float incomingCallCharge, float outgoingCallCharge) {

        super(id, tariffName, subscribersCount, monthlyFee, smsCharge);
        this.incomingCallCharge = incomingCallCharge;
        this.outgoingCallCharge = outgoingCallCharge;
    }

    public float getIncomingCallCharge() {
        return incomingCallCharge;
    }

    public void setIncomingCallCharge(float incomingCallCharge) {
        this.incomingCallCharge = incomingCallCharge;
    }

    public float getOutgoingCallCharge() {
        return outgoingCallCharge;
    }

    public void setOutgoingCallCharge(float outgoingCallCharge) {
        this.outgoingCallCharge = outgoingCallCharge;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ( (null == obj) || (getClass() != obj.getClass()) ) {
            return false;
        }

        if (!super.equals(obj)) {
            return false;
        }

        RoamingTariff that = (RoamingTariff) obj;

        if ( (Float.compare(that.incomingCallCharge, incomingCallCharge) != 0)
                || (Float.compare(that.outgoingCallCharge, outgoingCallCharge) != 0) ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result +
                (incomingCallCharge != +0.0f ? Float.floatToIntBits(incomingCallCharge) : 0);
        result = 31 * result +
                (outgoingCallCharge != +0.0f ? Float.floatToIntBits(outgoingCallCharge) : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString())
                .append(", Incoming call charge: ")
                .append(incomingCallCharge)
                .append(", Outgoing call charge: ")
                .append(outgoingCallCharge);

        return sb.toString();
    }
}
