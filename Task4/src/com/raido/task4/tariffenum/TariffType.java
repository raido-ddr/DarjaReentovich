package com.raido.task4.tariffenum;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Raido_DDR on 8/17/2014.
 */
public enum TariffType {
    LOCAL_TARIFF("local-tariff"),
    ROAMING_TARIFF("roaming-tariff"),
    NETWORKING_TARIFF("networking-tariff"),
    GUEST_TARIFF("guest-tariff"),
    BONUS_TARIFF("bonus-tariff");

    private String tagName;

    private static final Set<String> tagNameSet;

    static {
        tagNameSet = new HashSet<>();
        for(TariffType type : TariffType.values()) {
            tagNameSet.add(type.getTagName());
        }
    }

    private TariffType(String tagText) {
        this.tagName = tagText;
    }

    public String getTagName() {
        return tagName;
    }

    public static Set<String> getTagNameSet() {
        return tagNameSet;
    }
}
