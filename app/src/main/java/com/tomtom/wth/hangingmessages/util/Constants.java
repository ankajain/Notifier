package com.tomtom.wth.hangingmessages.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public interface Constants {

    String DATE_FORMAT = "dd/MM/yyyy";
    String TIME_FORMAT = "hh:mm a";
    String DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm a";

    int DEFAULT_VALID_FOR_HOURS = 1;
    int DEFAULT_VALID_FOR_DAYS = 0;

    DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.US);

    int MAX_CIRCULAR_ZONE_RADIUS = 2000;
    int DEF_CIRCULAR_ZONE_RADIUS = 50;
}

