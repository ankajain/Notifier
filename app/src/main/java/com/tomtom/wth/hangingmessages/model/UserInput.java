package com.tomtom.wth.hangingmessages.model;

import com.tomtom.wth.hangingmessages.enums.GeoFenceType;
import com.tomtom.wth.hangingmessages.enums.MessageType;
import com.tomtom.wth.hangingmessages.enums.PriorityType;

public final class UserInput {
    public String sender;
    public String receivers;
    public String subject;
    public MessageType messageType;
    public PriorityType messagePriority;
    public String content;
    public int day;
    public int month;
    public int year;
    public int hour;
    public int minute;
    public int validForDays;
    public int validForHours;

    public GeoFenceType geoFenceType;
    public double centerLatitude;
    public double centerLongitude;
    public int radius;

    public void reset() {
        sender = null;
        receivers = null;
        subject = null;
        messageType = MessageType.INFORMATION;
        messagePriority = PriorityType.LOW;
        content = null;
        day = 0;
        month = 0;
        year = 0;
        hour = 0;
        minute = 0;
        validForDays = 0;
        validForHours = 0;

        geoFenceType = GeoFenceType.CIRCLE;
        centerLatitude = 0;
        centerLongitude = 0;
        radius = 0;
    }
}
