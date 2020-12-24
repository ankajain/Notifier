package com.tomtom.wth.hangingmessages;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.tomtom.wth.hangingmessages.db.DatabaseHelper;
import com.tomtom.wth.hangingmessages.enums.MessageType;
import com.tomtom.wth.hangingmessages.enums.PriorityType;
import com.tomtom.wth.hangingmessages.model.Message;
import com.tomtom.wth.hangingmessages.model.UserInput;
import com.tomtom.wth.hangingmessages.util.Constants;
import com.tomtom.wth.hangingmessages.util.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditMessageActivity extends BaseActivity implements
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener,
        AdapterView.OnItemSelectedListener {
    private static final String LOG_TAG = EditMessageActivity.class.getSimpleName();

    private final SimpleDateFormat sDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.US);
    private final SimpleDateFormat sTimeFormat = new SimpleDateFormat(Constants.TIME_FORMAT, Locale.US);

    private TextView errorMessageTV;
    private EditText fromET;
    private EditText toET;
    private EditText subjectET;
    private TextView validFromDateTV;
    private TextView validFromTimeTV;
    private Spinner validForDaysSpinner;
    private Spinner validForHoursSpinner;
    private EditText textET;

    private UserInput userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_message);

        userInput = getUserInput();

        // Initialize message builder default values
        Calendar c = Calendar.getInstance();
        userInput.year = c.get(Calendar.YEAR);
        userInput.month = c.get(Calendar.MONTH);
        userInput.day = c.get(Calendar.DAY_OF_MONTH);
        userInput.hour = c.get(Calendar.HOUR_OF_DAY);
        userInput.minute = c.get(Calendar.MINUTE);

        userInput.validForDays = Constants.DEFAULT_VALID_FOR_DAYS;
        userInput.validForHours = Constants.DEFAULT_VALID_FOR_HOURS;

        userInput.messageType = MessageType.INFORMATION;
        userInput.messagePriority = PriorityType.LOW;

        // Initialize views
        errorMessageTV = (TextView) findViewById(R.id.error_message_tv);
        fromET = (EditText) findViewById(R.id.message_from_et);
        toET = (EditText) findViewById(R.id.message_to_et);
        subjectET = (EditText) findViewById(R.id.message_subject_et);
        validFromDateTV = (TextView) findViewById(R.id.message_valid_from_date_tv);
        validFromTimeTV = (TextView) findViewById(R.id.message_valid_from_time_tv);
        textET = (EditText) findViewById(R.id.message_text_et);
        validForDaysSpinner = (Spinner) findViewById(R.id.msg_valid_for_days_sp);
        validForHoursSpinner = (Spinner) findViewById(R.id.msg_valid_for_hours_sp);

        // Initialize spinners adapters
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.msg_valid_for_days_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        validForDaysSpinner.setAdapter(adapter);
        validForDaysSpinner.setOnItemSelectedListener(this);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.msg_valid_for_hours_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        validForHoursSpinner.setAdapter(adapter);
        validForHoursSpinner.setOnItemSelectedListener(this);

        // Display default values in views
        updateValidFromDate();
        updateValidFromTime();
        updateValidForDays();
        updateValidForHours();
    }

    public void onMessageTypeSelected(View view) {
        MessageType messageType = null;

        boolean checked = ((RadioButton) view).isChecked();
        if (checked) {
            // Check which radio button was clicked
            switch (view.getId()) {
                case R.id.msg_type_info_rb:
                    messageType = MessageType.INFORMATION;
                    break;
                case R.id.msg_type_advt_rb:
                    messageType = MessageType.ADVERTISEMENT;
                    break;
                case R.id.msg_type_instr_rb:
                    messageType = MessageType.INSTRUCTION;
                    break;
                case R.id.msg_type_warn_rb:
                    messageType = MessageType.WARNING;
                    break;
            }
            userInput.messageType = messageType;
        }
    }

    public void onPriorityTypeSelected(View view) {
        PriorityType priorityType = null;
        boolean checked = ((RadioButton) view).isChecked();
        if (checked) {
            // Check which radio button was clicked
            switch (view.getId()) {
                case R.id.msg_priority_low_rb:
                    priorityType = PriorityType.LOW;
                    break;
                case R.id.msg_priority_medium_rb:
                    priorityType = PriorityType.MEDIUM;
                    break;
                case R.id.msg_priority_high_rb:
                    priorityType = PriorityType.HIGH;
                    break;
            }
            userInput.messagePriority = priorityType;
        }
    }

    public void pickDate(View view) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setOuter(this);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment {
        private EditMessageActivity sOuter;

        public void setOuter(EditMessageActivity outer) {
            sOuter = outer;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new DatePickerDialog(getActivity(), sOuter, sOuter.userInput.year,
                    sOuter.userInput.month, sOuter.userInput.day);
        }
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Log.d(LOG_TAG, "Date set as a year: " + year + ", month: " + month + ", day: " + day);
        userInput.year = year;
        userInput.month = month;
        userInput.day = day;
        updateValidFromDate();
    }

    public void pickTime(View view) {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setOuter(this);
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public static class TimePickerFragment extends DialogFragment {
        private EditMessageActivity sOuter;

        public void setOuter(EditMessageActivity outer) {
            sOuter = outer;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new TimePickerDialog(getActivity(), sOuter,
                    sOuter.userInput.hour,
                    sOuter.userInput.minute,
                    DateFormat.is24HourFormat(getActivity()));
        }
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d(LOG_TAG, "Time set as hour: " + hourOfDay + ", minute: " + minute);
        userInput.hour = hourOfDay;
        userInput.minute = minute;
        updateValidFromTime();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int viewId = adapterView.getId();
        switch (viewId) {
            case R.id.msg_valid_for_days_sp:
                Log.d(LOG_TAG, "Days selected : " + adapterView.getItemAtPosition(i));
                int selectedDays = Integer.parseInt(String.valueOf(adapterView.getItemAtPosition(i)));
                userInput.validForDays = selectedDays;
                updateValidForDays();
                break;
            case R.id.msg_valid_for_hours_sp:
                Log.d(LOG_TAG, "Hours selected : " + adapterView.getItemAtPosition(i));
                int selectedHours = Integer.parseInt(String.valueOf(adapterView.getItemAtPosition(i)));
                userInput.validForHours = selectedHours;
                updateValidForHours();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        errorMessageTV.setText("Select");
    }

    private String formatValidFromDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, userInput.year);
        c.set(Calendar.MONTH, userInput.month);
        c.set(Calendar.DAY_OF_MONTH, userInput.day);
        return sDateFormat.format(c.getTime());
    }

    private void updateValidFromDate() {
        validFromDateTV.setText(formatValidFromDate());
    }

    private String formatValidFromTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, userInput.hour);
        c.set(Calendar.MINUTE, userInput.minute);
        return sTimeFormat.format(c.getTime());
    }

    private void updateValidFromTime() {
        validFromTimeTV.setText(formatValidFromTime());
    }

    private void updateValidForDays() {
        validForDaysSpinner.setSelection(userInput.validForDays);
    }

    private void updateValidForHours() {
        validForHoursSpinner.setSelection(userInput.validForHours);
    }

    public void clickNext(View view) {
        String sender = fromET.getText().toString();
        String to = toET.getText().toString();
        String subject = subjectET.getText().toString();
        String text = textET.getText().toString();

        Message.MessageBuilder messageBuilder = new Message.MessageBuilder();
        Message message = messageBuilder.id(Util.generateRandomId())
                .sender(sender)
                .to(to)
                .subject(subject)
                .type(userInput.messageType)
                .priority(userInput.messagePriority)
                .messageContent(text)
                .validFromDate(userInput.year, userInput.month, userInput.day)
                .validFromTime(userInput.hour, userInput.minute)
                .validFor(userInput.hour, userInput.minute).build();
        Log.d(LOG_TAG, message.toString());

        // write message to local database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.insertBroadcastMessage(message);
        Log.d(LOG_TAG, "Inserted message into DB");

        navigate(ViewMessagesActivity.class);
    }

    public void clickHome(View view) {
        navigate(ViewMessagesActivity.class);
    }

    public void clickBroadcast(View view) {
        navigate(EditMessageActivity.class);
    }
}
