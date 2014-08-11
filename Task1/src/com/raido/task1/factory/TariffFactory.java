package com.raido.task1.factory;

import com.raido.task1.entity.*;
import com.raido.task1.type.TariffType;

public final class TariffFactory {

    private TariffFactory() {

    }

    public static LocalTariff createLocalTariff(int id, String tariffName, int subscribersCount,
        float monthlyFee, float smsCharge,
        float internalCallCharge, float externalCallCharge){

        LocalTariff localTariff =
                new LocalTariff(id, tariffName, subscribersCount, monthlyFee,
        smsCharge, internalCallCharge, externalCallCharge);

        return localTariff;
    }

    public static RoamingTariff createRoamingTariff(int id, String tariffName, int subscribersCount,
        float monthlyFee, float smsCharge,
        float incomingCallCharge, float outgoingCallCharge){

        RoamingTariff roamingTariff =
                new RoamingTariff(id, tariffName, subscribersCount, monthlyFee,
                        smsCharge, incomingCallCharge, outgoingCallCharge);

        return roamingTariff;
    }

    public static NetworkingTariff createNetworkingTariff(int id, String tariffName, int subscribersCount,
        float monthlyFee, float smsCharge, float internalCallCharge,
        float externalCallCharge, float connectionSpeed, float trafficLimit){

        NetworkingTariff networkingTariff =
                new NetworkingTariff(id, tariffName, subscribersCount, monthlyFee,
                        smsCharge, internalCallCharge, externalCallCharge,
                        connectionSpeed, trafficLimit);

        return networkingTariff;
    }

    public static BonusTariff createBonusTariff(int id, String tariffName, int subscribersCount,
        float monthlyFee, float smsCharge, float internalCallCharge,
        float externalCallCharge, float connectionSpeed, float trafficLimit,
        int freeMinutesLimit, int favouriteNumbersLimit, float extraTrafficAmount){

        BonusTariff bonusTariff =
                new BonusTariff(id, tariffName, subscribersCount, monthlyFee,
                        smsCharge, internalCallCharge, externalCallCharge,
                        connectionSpeed, trafficLimit,
                        freeMinutesLimit, favouriteNumbersLimit,
                        extraTrafficAmount);

        return bonusTariff;
    }

    public static GuestTariff createGuestTariff(int id, String tariffName, int subscribersCount,
        float monthlyFee, float smsCharge, float internalCallCharge,
        float externalCallCharge,float stayLengthBasedDiscount){

        GuestTariff guestTariff =
                new GuestTariff(id, tariffName, subscribersCount, monthlyFee,
                        smsCharge, internalCallCharge, externalCallCharge,
                        stayLengthBasedDiscount);

        return guestTariff;
    }

}
