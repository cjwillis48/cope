package com.ihi.cope.domain;

import java.util.HashMap;
import java.util.Map;

public enum DosageMeasurement {
    MILLIGRAM("Milligram", "mg"),
    MICROGRAM("Microgram", "mcg"),
    UNKNOWN("Unknown", "");


    /**
     * The measurement's name.
     */
    private final String name;

    /**
     * The measurement's abbreviation.
     */
    private final String abbreviation;

    /**
     * The set of mesurements addressed by abbreviations.
     */
    private static final Map<String, DosageMeasurement> MEASUREMENTS_BY_ABBR = new HashMap<>();

    /* static initializer */
    static {
        for (DosageMeasurement measurement : values()) {
            MEASUREMENTS_BY_ABBR.put(measurement.getAbbreviation(), measurement);
        }
    }

    /**
     * Constructs a new measurement.
     *
     * @param name         the measurement's name.
     * @param abbreviation the measurement's abbreviation.
     */
    DosageMeasurement(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    /**
     * Returns the measurement's abbreviation.
     *
     * @return the measurement's abbreviation.
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Gets the enum constant with the specified abbreviation.
     *
     * @param abbr the measurement's abbreviation.
     * @return the enum constant with the specified abbreviation.
     */
    public static DosageMeasurement valueOfAbbreviation(final String abbr) {
        return MEASUREMENTS_BY_ABBR.getOrDefault(abbr, UNKNOWN);
    }

    public static DosageMeasurement valueOfName(final String name) {
        final String enumName = name.toUpperCase().replace(" ", "_");
        try {
            return valueOf(enumName);
        } catch (final IllegalArgumentException e) {
            return DosageMeasurement.UNKNOWN;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
