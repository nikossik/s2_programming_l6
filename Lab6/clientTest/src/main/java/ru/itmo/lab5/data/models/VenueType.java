package ru.itmo.lab5.data.models;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum VenueType implements Serializable {
    LOFT,
    OPEN_AREA,
    THEATRE,
    STADIUM;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var venueType : values()) {
            nameList.append(venueType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
