package com.raido.task4.entity;

public class NetworkingTariff extends LocalTariff {

    private float connectionSpeed;

    public float getConnectionSpeed() {
        return connectionSpeed;
    }

    public void setConnectionSpeed(float connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
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

        return (Float.compare(that.connectionSpeed, connectionSpeed) == 0);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result +
                (connectionSpeed != +0.0f ? Float.floatToIntBits(connectionSpeed) : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString())
                .append(", Connection speed: ")
                .append(connectionSpeed);

        return sb.toString();
    }
}
