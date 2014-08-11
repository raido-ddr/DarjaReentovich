package com.raido.task1.entity;

public class NetworkingTariff extends LocalTariff {

    private float connectionSpeed;

    private float trafficLimit;

    public NetworkingTariff(int id, String tariffName, int subscribersCount, float monthlyFee,
        float smsCharge, float internalCallCharge, float externalCallCharge,
        float connectionSpeed, float trafficLimit) {

        super(id, tariffName, subscribersCount, monthlyFee, smsCharge,
                internalCallCharge, externalCallCharge);

        this.connectionSpeed = connectionSpeed;
        this.trafficLimit = trafficLimit;
    }

    public float getConnectionSpeed() {
        return connectionSpeed;
    }

    public void setConnectionSpeed(float connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }

    public float getTrafficLimit() {
        return trafficLimit;
    }

    public void setTrafficLimit(float trafficLimit) {
        this.trafficLimit = trafficLimit;
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
        
        NetworkingTariff that = (NetworkingTariff) obj;

        if( (Float.compare(that.connectionSpeed, connectionSpeed) != 0)
                || (Float.compare(that.trafficLimit, trafficLimit) != 0) ){
            return false;
        }

        return  true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result +
                (connectionSpeed != +0.0f ? Float.floatToIntBits(connectionSpeed) : 0);
        result = 31 * result +
                (trafficLimit != +0.0f ? Float.floatToIntBits(trafficLimit) : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString())
                .append(", Connection speed: ")
                .append(connectionSpeed)
                .append(", Traffic limit: ")
                .append(trafficLimit);

        return sb.toString();
    }
}
