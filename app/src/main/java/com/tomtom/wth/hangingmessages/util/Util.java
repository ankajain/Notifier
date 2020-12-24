package com.tomtom.wth.hangingmessages.util;

import java.util.Calendar;
import java.util.Random;

public final class Util {

    private Util() {
    }

    public static long convert2Timestamp(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTimeInMillis();
    }

    public static long addDuration2Date(long startingDate,
                                        int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startingDate);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTimeInMillis();
    }

    public static long generateRandomId() {
        Random random = new Random();
        return random.nextLong();
    }

    public static String formatTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return Constants.DATE_FORMATTER.format(calendar.getTime());
    }
}