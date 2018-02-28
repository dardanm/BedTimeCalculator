package com.meha.dardan.bedtimecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private Button timePickerButton;
    private Button wakeUpButtons[] = new Button[9];
    private Button sleepButtons[] = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initalizeButtons();

        Calendar c = Calendar.getInstance();
        int currentHour = c.get(Calendar.HOUR);
        int currentMinutes = c.get(Calendar.MINUTE);
        int a = c.get(Calendar.AM_PM);

        // FIRST TIME NEEDS TO START 15 MIN EXTRA
        currentHour++;
        currentMinutes += 45;
        while(currentMinutes > 60){
            currentMinutes -= 60;
            currentHour++;
        }

        wakeUpButtons[0].setText(currentHour + ":" + currentMinutes);
        //wakeUpButtons[0].setText(dateFormat.format(Date());
        //wakeUpButtons[0].setText(currentTime);

        for (int i = 1; i<wakeUpButtons.length;i++){
            currentHour++;
            currentMinutes += 30;
            while(currentMinutes > 60){
                currentMinutes -= 60;
                currentHour++;
            }
            if (currentHour == 24){
                currentHour = 12;
            }
            if (currentHour == 13){
                currentHour = 1;
            } if (currentHour == 14){
                currentHour = 2;
            }
            wakeUpButtons[i].setText(currentHour + ":" + currentMinutes);
            //wakeUpButtons[i].setText(currentTime);
        }
    }

    public void initalizeButtons(){
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
    }

    public void showTimePickerDialog(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), getString(R.string.time_picker));
    }

    public void processTimePickerResult(int currentHour, int currentMinutes) {


        // FIRST TIME NEEDS TO START 15 MIN EXTRA
        currentMinutes -= 15;
        if (currentMinutes < 0){
            currentMinutes = 60 - 15;
            currentHour--;
        }

        currentMinutes -= 30;
        if (currentMinutes < 0){
            currentMinutes = 60 - 30;
            currentHour--;
        }
        currentHour--;

        sleepButtons[0].setText(currentHour + ":" + currentMinutes);
        //wakeUpButtons[0].setText(dateFormat.format(Date());
        //wakeUpButtons[0].setText(currentTime);

        for (int i = 1; i<sleepButtons.length;i++){
            currentMinutes -= 30;
            if (currentMinutes < 0){
                currentMinutes = 60 - 30;
                currentHour--;
            }
            currentHour--;
            sleepButtons[i].setText(currentHour + ":" + currentMinutes);
            //sleepButtons[i].setText(currentTime);
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
