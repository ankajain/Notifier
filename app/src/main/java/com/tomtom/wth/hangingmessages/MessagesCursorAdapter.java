package com.tomtom.wth.hangingmessages;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tomtom.wth.hangingmessages.db.DatabaseHelper;
import com.tomtom.wth.hangingmessages.db.Schema;
import com.tomtom.wth.hangingmessages.enums.MessageType;
import com.tomtom.wth.hangingmessages.enums.PriorityType;
import com.tomtom.wth.hangingmessages.util.Util;

public class MessagesCursorAdapter extends CursorAdapter {

    public MessagesCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.view_message_with_actions, parent, false);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView fromTV = (TextView) view.findViewById(R.id.message_from_tv);
        TextView toTV = (TextView) view.findViewById(R.id.message_to_tv);
        TextView subjectTV = (TextView) view.findViewById(R.id.message_subject_tv);
        ImageView typeIV = (ImageView) view.findViewById(R.id.message_type_iv);
        ImageView priorityIV = (ImageView) view.findViewById(R.id.priority_type_iv);
        TextView validFromTV = (TextView) view.findViewById(R.id.message_valid_from_tv);
        TextView validToTV = (TextView) view.findViewById(R.id.message_valid_to_tv);
        TextView msgContentTV = (TextView) view.findViewById(R.id.message_content_tv);

        // fetch values from database
        String sender = cursor.getString(cursor.getColumnIndex(Schema.MessageTable.COLUMN_NAME_SENDER));
        String receivers = cursor.getString(cursor.getColumnIndex(Schema.MessageTable.COLUMN_NAME_RECEIVERS));
        String subject = cursor.getString(cursor.getColumnIndex(Schema.MessageTable.COLUMN_NAME_SUBJECT));
        int type = cursor.getInt(cursor.getColumnIndex(Schema.MessageTable.COLUMN_NAME_TYPE));
        int priority = cursor.getInt(cursor.getColumnIndex(Schema.MessageTable.COLUMN_NAME_PRIORITY));
        long validFrom = cursor.getLong(cursor.getColumnIndex(Schema.MessageTable.COLUMN_NAME_VALID_FROM));
        long validTo = cursor.getLong(cursor.getColumnIndex(Schema.MessageTable.COLUMN_NAME_VALID_TO));
        String content = cursor.getString(cursor.getColumnIndex(Schema.MessageTable.COLUMN_NAME_CONTENT));
        String source = cursor.getString(cursor.getColumnIndex(Schema.MessageTable.COLUMN_NAME_SOURCE));

        if(DatabaseHelper.BROADCAST.equals(source)) {
            view.setBackgroundResource(R.drawable.coral_gradient);
        }

        // Assign values from database to text view
        fromTV.setText(sender);
        toTV.setText(receivers);
        subjectTV.setText(subject);

        setMessageTypeIV(typeIV, MessageType.getById(type));
        setPriorityTypeIV(priorityIV, PriorityType.getById(priority));

        validFromTV.setText(Util.formatTime(validFrom));
        validToTV.setText(Util.formatTime(validTo));
        msgContentTV.setText(content);
    }

    private void setMessageTypeIV(ImageView messageTypeIV, MessageType messageType) {
        switch (messageType) {
            case INFORMATION:
                messageTypeIV.setImageResource(R.drawable.ic_type_info);
                break;
            case ADVERTISEMENT:
                messageTypeIV.setImageResource(R.drawable.ic_type_advt);
                break;
            case INSTRUCTION:
                messageTypeIV.setImageResource(R.drawable.ic_type_instr);
                break;
            case WARNING:
                messageTypeIV.setImageResource(R.drawable.ic_type_warn);
                break;
        }
    }

    private void setPriorityTypeIV(ImageView priorityIV, PriorityType priorityType) {
        switch (priorityType) {
            case LOW:
                priorityIV.setImageResource(R.drawable.ic_prio_low);
                break;
            case MEDIUM:
                priorityIV.setImageResource(R.drawable.ic_prio_mid);
                break;
            case HIGH:
                priorityIV.setImageResource(R.drawable.ic_prio_high);
                break;
        }
    }
}
