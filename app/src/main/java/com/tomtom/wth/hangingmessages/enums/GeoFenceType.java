package com.tomtom.wth.hangingmessages.enums;


public enum GeoFenceType {
    CIRCLE(0);

    private final int sId;

    private GeoFenceType(int id) {
        this.sId = id;
    }

    public int getId() {
        return sId;
    }

    public static GeoFenceType getById(int id) {
        for (GeoFenceType type : values()) {
            if (type.sId == id)
                return type;
        }
        throw new IllegalArgumentException("Incorrect message type id");
    }
}
