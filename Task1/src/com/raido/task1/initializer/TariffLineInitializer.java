package com.raido.task1.initializer;

import com.raido.task1.container.TariffLine;
import com.raido.task1.entity.*;
import com.raido.task1.factory.TariffFactory;

public class TariffLineInitializer {

    public static TariffLine CreateTariffLine() {
        return new TariffLine();
    }

    public static void populateTariffLine(TariffLine tariffLine) {

        LocalTariff localTariff = TariffFactory.createLocalTariff("Hello, world!",
                235, 20.5f, 0.5f, 2.5f, 3.7f);
        tariffLine.addTariff(localTariff);

        NetworkingTariff networkingTariff = TariffFactory.createNetworkingTariff(
                "MobileNet", 346, 28.5f, 0.4f, 2.6f, 3.7f, 100f, 2048f);
        tariffLine.addTariff(networkingTariff);

        SocialTariff socialTariff = TariffFactory.createSocialTariff(
                "Social", 154, 12.5f, 0.4f, 1.5f, 2.7f, 10f);
        tariffLine.addTariff(socialTariff);

        BonusTariff bonusTariff = TariffFactory.createBonusTariff(
                "Optimal", 276, 15f, 0.5f, 3.5f, 4.7f, 100f, 4096f, 30, 5, 1024f);
        tariffLine.addTariff(bonusTariff);

        RoamingTariff roamingTariff = TariffFactory.createRoamingTariff(
                "International", 113, 0f, 5.5f, 10f, 6f);
        tariffLine.addTariff(roamingTariff);

        GuestTariff guestTariff = TariffFactory.createGuestTariff(
                "Welcome", 97, 0f, 0.6f, 2.5f, 3.7f, 15f);
        tariffLine.addTariff(guestTariff);

    }


}
