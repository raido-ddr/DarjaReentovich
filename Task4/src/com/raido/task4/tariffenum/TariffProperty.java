package com.raido.task4.tariffenum;

public enum TariffProperty {
    ID("id"),
    NAME("tariff-name"),
    MONTHLY_FEE("monthly-fee"),
    SMS_CHARGE("sms-charge"),
    INTERNAL_CHARGE("internal-charge"),
    EXTERNAL_CHARGE("external-charge"),
    INCOMING_CHARGE("incoming-charge"),
    OUTGOING_CHARGE("outgoing-charge"),
    CONNECTION_SPEED("connection-speed"),
    STAY_LENGTH_DISCOUNT("stay-length-discount"),
    FREE_MINUTES("free-minutes"),
    FAVOURITE_NUMBERS("favourite-numbers");

    private String tagName;

    private TariffProperty(String tagText) {
        this.tagName = tagText;
    }

    public String getTagName() {
        return tagName;
    }

}
