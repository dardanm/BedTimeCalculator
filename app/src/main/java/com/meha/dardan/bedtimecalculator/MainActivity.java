package com.meha.dardan.bedtimecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private Button timePickerButton;
    private Button wakeButtons[] = new Button[9];
    private Button sleepButtons[] = new Button[9];

    private TextView textViewTitle;

    private Calendar c = Calendar.getInstance();
    private boolean isPM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initalizeButtons();

        textViewTitle.setText("If you go to sleep at " + timeMessage() + ",");

        //
        // Set default time for wake buttons at top
        //

        c.add(Calendar.HOUR_OF_DAY,1);
        c.add(Calendar.MINUTE,45);

        wakeButtons[0].setText(timeMessage());

        for (int i = 1; i<wakeButtons.length;i++){
            c.add(Calendar.HOUR_OF_DAY,1);
            c.add(Calendar.MINUTE,30);
            wakeButtons[i].setText(timeMessage());
        }

        //
        // Set default time for sleep buttons at bottom
        //
        c.set(Calendar.HOUR_OF_DAY,8);
        c.set(Calendar.MINUTE,0);

        timePickerButton.setText(timeMessage());

        c.add(Calendar.HOUR_OF_DAY,-1);
        c.add(Calendar.MINUTE,-45);

        sleepButtons[8].setText(timeMessage());

        for (int i = 7; i>=0; i--){
            c.add(Calendar.HOUR_OF_DAY,-1);
            c.add(Calendar.MINUTE,-30);
            sleepButtons[i].setText(timeMessage());
        }

    }

    public void showTimePickerDialog(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), getString(R.string.time_picker));
    }

    public void processTimePickerResult(int currentHour, int currentMinutes) {

        c.set(Calendar.HOUR_OF_DAY,currentHour);
        c.set(Calendar.MINUTE,currentMinutes);

        timePickerButton.setText(timeMessage());

        c.add(Calendar.HOUR_OF_DAY,-1);
        c.add(Calendar.MINUTE,-45);

        sleepButtons[5].setText(timeMessage());

        for (int i = 4; i>=0; i--){
            c.add(Calendar.HOUR_OF_DAY,-1);
            c.add(Calendar.MINUTE,-30);
            sleepButtons[i].setText(timeMessage());
        }
    }

    public String timeMessage(){
        isPM = (c.get(Calendar.HOUR_OF_DAY) >= 12);
        String timeMessage = (String.format("%02d:%02d %s", (c.get(Calendar.HOUR_OF_DAY) == 12 || c.get(Calendar.HOUR_OF_DAY) == 0) ? 12 : c.get(Calendar.HOUR_OF_DAY) % 12, c.get(Calendar.MINUTE), isPM ? "PM" : "AM"));
        return timeMessage;
    }

    public void initalizeButtons(){
        timePickerButton = (Button) findViewById(R.id.timePickerButton);

        textViewTitle = (TextView) findViewById(R.id.textViewTitle);

        wakeButtons[0] = (Button) findViewById(R.id.wakeButtonOne);
        wakeButtons[1] = (Button) findViewById(R.id.wakeButtonTwo);
        wakeButtons[2] = (Button) findViewById(R.id.wakeButtonThree);
        wakeButtons[3] = (Button) findViewById(R.id.wakeButtonFour);
        wakeButtons[4] = (Button) findViewById(R.id.wakeButtonFive);
        wakeButtons[5] = (Button) findViewById(R.id.wakeButtonSix);
        wakeButtons[6] = (Button) findViewById(R.id.wakeButtonSeven);
        wakeButtons[7] = (Button) findViewById(R.id.wakeButtonEight);
        wakeButtons[8] = (Button) findViewById(R.id.wakeButtonNine);

        sleepButtons[0] = (Button) findViewById(R.id.sleepButtonOne);
        sleepButtons[1] = (Button) findViewById(R.id.sleepButtonTwo);
        sleepButtons[2] = (Button) findViewById(R.id.sleepButtonThree);
        sleepButtons[3] = (Button) findViewById(R.id.sleepButtonFour);
        sleepButtons[4] = (Button) findViewById(R.id.sleepButtonFive);
        sleepButtons[5] = (Button) findViewById(R.id.sleepButtonSix);
        sleepButtons[6] = (Button) findViewById(R.id.sleepButtonSeven);
        sleepButtons[7] = (Button) findViewById(R.id.sleepButtonEight);
        sleepButtons[8] = (Button) findViewById(R.id.sleepButtonNine);

    }
}

//    String timeMessage = (String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
//
//        Toast.makeText(this, "Time: " + timeMessage, Toast.LENGTH_SHORT).show();
//
//                Button timePickerButton = (Button) findViewById(R.id.timePickerButton);
//                timePickerButton.setText(timeMessage);
