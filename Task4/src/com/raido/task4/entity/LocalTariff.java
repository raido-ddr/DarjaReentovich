package com.raido.task4.entity;


public class LocalTariff extends MobileTariff {

    private float internalCallCharge;

    private float externalCallCharge;  

    public float getInternalCallCharge() {
        return internalCallCharge;
    }

    public void setInternalCallCharge(float internalCallCharge) {
        this.internalCallCharge = internalCallCharge;
    }

    public float getExternalCallCharge() {
        return externalCallCharge;
    }

    public void setExternalCallCharge(float externalCallCharge) {
        this.externalCallCharge = externalCallCharge;
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

        LocalTariff that = (LocalTariff) obj;

        if ( (Float.compare(that.externalCallCharge, externalCallCharge) != 0)
                ||(Float.compare(that.internalCallCharge, internalCallCharge) != 0) ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result +
                (internalCallCharge != +0.0f ? Float.floatToIntBits(internalCallCharge) : 0);
        result = 31 * result +
                (externalCallCharge != +0.0f ? Float.floatToIntBits(externalCallCharge) : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString())
                .append(", Internal call charge: ")
                .append(internalCallCharge)
                .append(", External call charge: ")
                .append(externalCallCharge);

        return sb.toString();
    }
}
