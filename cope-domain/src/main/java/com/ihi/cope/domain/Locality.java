package com.ihi.cope.domain;

import java.util.HashMap;
import java.util.Map;

public enum Locality {
    STATE("01"),
    COUNTY("02"),
    CITY("03"),
    UNKNOWN("04");

    private final String code;

    /**
     * The set of mesurements addressed by abbreviations.
     */
    private static final Map<String, Locality> LOCALITIES_BY_CODE = new HashMap<>();

    /* static initializer */
    static {
        for (Locality locality : values()) {
            LOCALITIES_BY_CODE.put(locality.getCode(), locality);
        }
    }

    Locality(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Locality valueOfCode(final String code) {
        return LOCALITIES_BY_CODE.getOrDefault(code, UNKNOWN);
    }

    @Override
    public String toString() {
        return name();
    }
}