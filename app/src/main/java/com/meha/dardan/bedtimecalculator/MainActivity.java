package com.meha.dardan.bedtimecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button timePickerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showTimePickerDialog(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), getString(R.string.time_picker));
    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        //convert time elements into strings
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);

        boolean isPM = (hourOfDay >= 12);

        String timeMessage = (String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));

        Toast.makeText(this, "Time: " + timeMessage, Toast.LENGTH_SHORT).show();

        Button timePickerButton = (Button) findViewById(R.id.timePickerButton);
        timePickerButton.setText(timeMessage);
    }

    public void showTimeToGoToSleep(View view) {
    }
}
