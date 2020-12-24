package com.tomtom.wth.hangingmessages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomtom.wth.hangingmessages.model.UserInput;

public abstract class BaseActivity extends AppCompatActivity {

    protected void navigate(Class targetActClass) {
        Intent intent = new Intent(this, targetActClass);
        startActivity(intent);
    }

    protected UserInput getUserInput() {
        return ((HangingMessagesApplication) getApplication()).getUserInput();
    }
}
