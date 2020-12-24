package com.tomtom.wth.hangingmessages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tomtom.wth.hangingmessages.enums.GeoFenceType;
import com.tomtom.wth.hangingmessages.model.UserInput;
import com.tomtom.wth.hangingmessages.util.Constants;

public class GeoFenceCreatorActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

    private EditText searchET;
    private TextView radiusTV;
    private SeekBar adjustRadius;

    private UserInput userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_fence_creator);

        userInput = getUserInput();
        userInput.geoFenceType = GeoFenceType.CIRCLE;
        userInput.radius = Constants.DEF_CIRCULAR_ZONE_RADIUS;

        radiusTV = (TextView) findViewById(R.id.circular_geofence_radius_tv);

        adjustRadius = (SeekBar) findViewById(R.id.adjust_cir_geofence_radius_sb);
        adjustRadius.setMax(Constants.MAX_CIRCULAR_ZONE_RADIUS);
        adjustRadius.setProgress(Constants.DEF_CIRCULAR_ZONE_RADIUS);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        userInput.radius = progress;
        radiusTV.setText(userInput.radius);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void clickNext(View view) {
        navigate(ViewMessagesActivity.class);
    }

    public void clickBack(View view) {
        navigate(EditMessageActivity.class);
    }
}
