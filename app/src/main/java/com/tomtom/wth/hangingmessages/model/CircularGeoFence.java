package com.tomtom.wth.hangingmessages.model;

import com.tomtom.wth.hangingmessages.enums.GeoFenceType;

public class CircularGeoFence extends GeoFence {

    private final double centerLatitude;
    private final double centerLongitude;
    private final int radius;

    public CircularGeoFence(long id, GeoFenceType geoFenceType, float centerLat, float centerLon, int radius) {
        super(id, geoFenceType);
        this.centerLatitude = centerLat;
        this.centerLongitude = centerLon;
        this.radius = radius;
    }

    public double getCenterLat() {
        return centerLatitude;
    }

    public double getCenterLon() {
        return centerLongitude;
    }

    public int getRadius() {
        return radius;
    }
}
