package com.tomtom.wth.hangingmessages.db;

import android.provider.BaseColumns;

public final class Schema {

    private Schema() {
    }

    public static class MessageTable implements BaseColumns {
        public static final String TABLE_NAME = "MESSAGE";

        public static final String COLUMN_NAME_SENDER = "sender";
        public static final String COLUMN_NAME_RECEIVERS = "receivers";
        public static final String COLUMN_NAME_SUBJECT = "subject";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_PRIORITY = "priority";
        public static final String COLUMN_NAME_CREATED_ON = "created_on";
        public static final String COLUMN_NAME_VALID_FROM = "valid_from";
        public static final String COLUMN_NAME_VALID_TO = "valid_to";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_SOURCE = "source";

        public static final String[] PROJECTION = new String[]{
                _ID,
                COLUMN_NAME_SENDER,
                COLUMN_NAME_RECEIVERS,
                COLUMN_NAME_SUBJECT,
                COLUMN_NAME_TYPE,
                COLUMN_NAME_PRIORITY,
                COLUMN_NAME_CREATED_ON,
                COLUMN_NAME_VALID_FROM,
                COLUMN_NAME_VALID_TO,
                COLUMN_NAME_CONTENT,
                COLUMN_NAME_SOURCE
        };

        public static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + _ID + " INT PRIMARY KEY, "
                + COLUMN_NAME_SENDER + " TEXT NOT NULL, "
                + COLUMN_NAME_RECEIVERS + " TEXT NOT NULL, "
                + COLUMN_NAME_SUBJECT + " TEXT NOT NULL, "
                + COLUMN_NAME_TYPE + " INT NOT NULL, "
                + COLUMN_NAME_PRIORITY + " INT NOT NULL, "
                + COLUMN_NAME_CREATED_ON + " INT NOT NULL, "
                + COLUMN_NAME_VALID_FROM + " INT NOT NULL, "
                + COLUMN_NAME_VALID_TO + " INT NOT NULL, "
                + COLUMN_NAME_CONTENT + " TEXT NOT NULL, "
                + COLUMN_NAME_SOURCE + " TEXT NOT NULL"
                + " )";

        public static final String DELETE_TABLE_SQL =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
