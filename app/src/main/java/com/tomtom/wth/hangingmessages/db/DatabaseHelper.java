package com.tomtom.wth.hangingmessages.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tomtom.wth.hangingmessages.model.Message;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = DatabaseHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "hanging_messages.db";

    public static final String BROADCAST = "broadcast";
    public static final String RECEIVED = "received";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Schema.MessageTable.CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Schema.MessageTable.DELETE_TABLE_SQL);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void insertMessage(Message message, String source) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            values.put(Schema.MessageTable._ID, message.getId());
            values.put(Schema.MessageTable.COLUMN_NAME_SENDER, message.getSender());
            values.put(Schema.MessageTable.COLUMN_NAME_RECEIVERS, message.getTo());
            values.put(Schema.MessageTable.COLUMN_NAME_SUBJECT, message.getSubject());
            values.put(Schema.MessageTable.COLUMN_NAME_TYPE, message.getType().getId());
            values.put(Schema.MessageTable.COLUMN_NAME_PRIORITY, message.getPriority().getId());
            values.put(Schema.MessageTable.COLUMN_NAME_CREATED_ON, message.getCreatedOn());
            values.put(Schema.MessageTable.COLUMN_NAME_VALID_FROM, message.getValidFrom());
            values.put(Schema.MessageTable.COLUMN_NAME_VALID_TO, message.getValidTo());
            values.put(Schema.MessageTable.COLUMN_NAME_CONTENT, message.getContent());
            values.put(Schema.MessageTable.COLUMN_NAME_SOURCE, source);

            long rowId = db.insert(Schema.MessageTable.TABLE_NAME, null, values);
            Log.d(LOG_TAG, "Inserted message with row id : " + rowId);
            values.clear();
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getLocalizedMessage());
        } finally {
            db.close();
        }
    }

    private Cursor getMessageBySource(long id, String source) {
        String query = " SELECT * FROM " + Schema.MessageTable.TABLE_NAME
                + " WHERE "
                + Schema.MessageTable._ID + " = " + id
                + " AND "
                + Schema.MessageTable.COLUMN_NAME_SOURCE + " = " + "'" + source + "'";

        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(query, new String[0]);
    }

    private Cursor getAllMessagesBySource(String source) {
        String query = " SELECT * FROM " + Schema.MessageTable.TABLE_NAME
                + " WHERE "
                + Schema.MessageTable.COLUMN_NAME_SOURCE + " = " + "'" + source + "'"
                + " ORDER BY " + Schema.MessageTable.COLUMN_NAME_CREATED_ON + " DESC ";

        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(query, new String[0]);
    }

    public Cursor getAllMessages() {
        String query = " SELECT * FROM " + Schema.MessageTable.TABLE_NAME
                + " ORDER BY " + Schema.MessageTable.COLUMN_NAME_CREATED_ON + " DESC ";

        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(query, new String[0]);
    }

    public void insertBroadcastMessage(Message message) {
        insertMessage(message, BROADCAST);
    }

    public Cursor getBroadcastMessage(long id) {
        return getMessageBySource(id, BROADCAST);
    }

    public Cursor getAllBroadcastMessages() {
        return getAllMessagesBySource(BROADCAST);
    }

    public void insertReceivedMessage(Message message) {
        insertMessage(message, RECEIVED);
    }

    public Cursor getReceivedMessage(long id) {
        return getMessageBySource(id, RECEIVED);
    }

    public Cursor getAllReceivedMessages() {
        return getAllMessagesBySource(RECEIVED);
    }
}

