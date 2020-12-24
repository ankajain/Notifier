package com.tomtom.wth.hangingmessages;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.tomtom.wth.hangingmessages.db.DatabaseHelper;

public class ViewMessagesActivity extends BaseActivity {
    private static final String LOG_TAG = ViewMessagesActivity.class.getSimpleName();

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_messages);

        Log.d(LOG_TAG, "Initializing database");
        dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.getAllMessages();
        Log.d(LOG_TAG, "Messages in DB : " + cursor.getCount());

        ListView messagesList = (ListView) findViewById(R.id.messages_list);
        MessagesCursorAdapter adapter = new MessagesCursorAdapter(this, cursor);
        messagesList.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        dbHelper.close();
        super.onStop();
    }

    public void clickHome(View view) {
        navigate(ViewMessagesActivity.class);
    }

    public void clickBroadcast(View view) {
        navigate(EditMessageActivity.class);
    }
}
