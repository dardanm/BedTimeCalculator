package com.meha.dardan.bedtimecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private Button timePickerButton;
    private Button wakeUpButtons[] = new Button[9];
    private Button sleepButtons[] = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wakeUpButtons[0] = (Button) findViewById(R.id.wakeUpButtonOne);
        wakeUpButtons[1] = (Button) findViewById(R.id.wakeUpButtonTwo);
        wakeUpButtons[2] = (Button) findViewById(R.id.wakeUpButtonThree);
        wakeUpButtons[3] = (Button) findViewById(R.id.wakeUpButtonFour);
        wakeUpButtons[4] = (Button) findViewById(R.id.wakeUpButtonFive);
        wakeUpButtons[5] = (Button) findViewById(R.id.wakeUpButtonSix);
        wakeUpButtons[6] = (Button) findViewById(R.id.wakeUpButtonSeven);
        wakeUpButtons[7] = (Button) findViewById(R.id.wakeUpButtonEight);
        wakeUpButtons[8] = (Button) findViewById(R.id.wakeUpButtonNine);

        sleepButtons[0] = (Button) findViewById(R.id.sleepButtonOne);
        sleepButtons[1] = (Button) findViewById(R.id.sleepButtonTwo);
        sleepButtons[2] = (Button) findViewById(R.id.sleepButtonThree);
        sleepButtons[3] = (Button) findViewById(R.id.sleepButtonFour);
        sleepButtons[4] = (Button) findViewById(R.id.sleepButtonFive);
        sleepButtons[5] = (Button) findViewById(R.id.sleepButtonSix);
        sleepButtons[6] = (Button) findViewById(R.id.sleepButtonSeven);
        sleepButtons[7] = (Button) findViewById(R.id.sleepButtonEight);
        sleepButtons[8] = (Button) findViewById(R.id.sleepButtonNine);

        Calendar c = Calendar.getInstance();
        int currentHour = c.get(Calendar.HOUR_OF_DAY);
        int currentMinutes = c.get(Calendar.MINUTE);

        boolean isPM = (currentHour >= 12);

        for (int i = 0; i<wakeUpButtons.length;i++){
            currentHour++;
            currentMinutes += 30;
            while(currentMinutes > 60){
                currentMinutes -= 60;
                currentHour++;
            }
            isPM = (currentHour >= 12);
            //currentHour += hourToAdd;
            String currentTime = (String.format("%02d:%02d %s", (currentHour == 12 || currentHour == 0) ? 12 : currentHour % 12, currentMinutes, isPM ? "PM" : "AM"));
            //wakeUpButtons[i].setText(currentHour + ":" + currentMinutes);
            wakeUpButtons[i].setText(currentTime);

        }

    }

    public void showTimePickerDialog(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), getString(R.string.time_picker));
    }

    public void processTimePickerResult(int currentHour, int currentMinutes) {
        //convert time elements into strings
        String hour_string = Integer.toString(currentHour);
        String minute_string = Integer.toString(currentMinutes);

        boolean isPM = (currentHour >= 12);


        Calendar c = Calendar.getInstance();
        //int currentHour = hourOfDay;
        //int currentMinutes = minute;

        for (int i = 0; i<sleepButtons.length;i++){
            if (currentHour > 24){
                currentHour = 0;
            }
            currentHour++;
            currentMinutes += 30;
            while(currentMinutes > 60){
                currentMinutes -= 60;
                currentHour++;
                if (currentHour > 24){
                    currentHour = 0;
                }
            }
            isPM = (currentHour >= 12);

            String currentTime = (String.format("%02d:%02d %s", (currentHour == 12 || currentHour == 0) ? 12 : currentHour % 12, currentMinutes, isPM ? "PM" : "AM"));
            //sleepButtons[i].setText(currentHour + ":" + currentMinutes);
            sleepButtons[i].setText(currentTime);
        }
    }

    public void showTimeToGoToSleep(View view) {
    }
}

//    String timeMessage = (String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
//
//        Toast.makeText(this, "Time: " + timeMessage, Toast.LENGTH_SHORT).show();
//
//                Button timePickerButton = (Button) findViewById(R.id.timePickerButton);
//                timePickerButton.setText(timeMessage);
