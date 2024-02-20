package com.example.emptyviewstemplate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.app.TimePickerDialog;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String timezonecurrent;
    private SharedPreferences myPrefs;

    SharedPreferences.Editor peditor;

    Button timeButton;
    int hour, minute;

    private String homeCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.times_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        timeButton = findViewById(R.id.timeButton);

        /* SharedPreferences at activity only level, not shared by others
        myPrefs = this.getPreferences(Activity.MODE_PRIVATE);
        */

        ImageButton convert = (ImageButton)findViewById(R.id.convertButton);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTime();
            }
        });


        myPrefs = getApplicationContext().getSharedPreferences("myUserPrefs", Context.MODE_PRIVATE);
        homeCity = myPrefs.getString("homeCity", "");

        /*
        super.onCreate(savedInstanceState);
        set ContentView(R.layout.activity_main);
        */
    }

    public void convertTime() {
        TextView timezone = (TextView) findViewById(R.id.textView10);
        SharedPreferences.Editor peditor = myPrefs.edit();



        //int newHour =

        timezone.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
    }

    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;

                String am_pm = "am";
                if (hour > 12) {
                    am_pm = "pm";
                    hour = hour % 12;
                } else if (hour == 12) {
                    hour = 12;
                }
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d %s",hour, minute, am_pm));
            }
        };

        // int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();


        //numHits = myPrefs.getInt("hitsValue", 0);
        //TextView hits = (TextView) findViewById(R.id.hits_value);
        //hits.setText(numHits.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {

        peditor.apply();

        super.onPause();
    }

    @Override
    protected void onStop() {

        peditor.putInt("hitsValue", 10);
        peditor.apply();

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // do stuff here
        super.onDestroy();
    }

    /** Called when the user clicks the HITME button */
    public void hitme(View view) {
        // Do something in response to button

        Intent intent = new Intent(MainActivity.this, HitsActivity.class);
        startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        TextView timezone = (TextView) findViewById(R.id.textViewTimeHours);
        TextView timezoneName = (TextView) findViewById(R.id.textViewCurrentZone);

        if (text.equals("America/New_York")) {
            timezone.setText("GMT -05:00");
            timezoneName.setText("America/New_York");
        } else if (text.equals("America/Los_Angeles")) {
            timezone.setText("GMT -08:00");
            timezoneName.setText("America/Los_Angeles");
        }else if (text.equals("Europe/Berlin")) {
            timezone.setText("GMT +01:00");
            timezoneName.setText("Europe/Berlin");
        }else if (text.equals("Europe/Istanbul")) {
            timezone.setText("GMT +02:00");
            timezoneName.setText("Europe/Istanbul");
        }else if (text.equals("Asia/Singapore")) {
            timezone.setText("GMT +08:00");
            timezoneName.setText("Asia/Singapore");
        }else if (text.equals("Asia/Tokyo")) {
            timezone.setText("GMT +09:00");
            timezoneName.setText("Asia/Tokyo");
        }else if (text.equals("Australia/Canberra")) {
            timezone.setText("GMT +10:00");
            timezoneName.setText("Australia/Canberra");
        }else {
            timezone.setText("GMT +05:30");
            timezoneName.setText("India/Kolkata");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void settings(View view) {
        Intent intent = new Intent(MainActivity.this, HitsActivity.class);
        startActivity(intent);
    }

}