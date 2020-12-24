package com.tomtom.wth.hangingmessages.model;

import com.tomtom.wth.hangingmessages.enums.GeoFenceType;

public class GeoFence {

    private final long id;
    private final GeoFenceType geoFenceType;

    public GeoFence(long id, GeoFenceType geoFenceType) {
        super();
        this.id = id;
        this.geoFenceType = geoFenceType;
    }

    public long getId() {
        return id;
    }

    public GeoFenceType getGeoFenceType() {
        return geoFenceType;
    }
}
