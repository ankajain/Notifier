package com.tomtom.wth.hangingmessages;

import android.app.Application;

import com.tomtom.wth.hangingmessages.model.UserInput;

public class HangingMessagesApplication extends Application {
    private UserInput userInput = new UserInput();

    public UserInput getUserInput() {
        return userInput;
    }

    public void reset() {
        userInput.reset();
    }
}
