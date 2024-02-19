package com.example.emptyviewstemplate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.app.TimePickerDialog;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Integer numHits;
    private SharedPreferences myPrefs;

    SharedPreferences.Editor peditor;

    Button timeButton;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.times_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        timeButton = findViewById(R.id.timeButton);

        /* SharedPreferences at activity only level, not shared by others
        myPrefs = this.getPreferences(Activity.MODE_PRIVATE);
        */


        Context context = getApplicationContext(); // app level storage
        myPrefs = context.getSharedPreferences(String.valueOf(R.string.context_prefs), Context.MODE_PRIVATE);
        peditor = myPrefs.edit();
        peditor.putInt("hitsValue", -1);
        peditor.apply();

        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        */
    }

    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
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

        numHits = myPrefs.getInt("hitsValue", 0);
        //TextView hits = (TextView) findViewById(R.id.hits_value);
        //hits.setText(numHits.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {

        peditor.putInt("hitsValue", numHits);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if (text.equals("America/New_York")) {

        } else if (text.equals("Europe/Berlin")) {

        }else if (text.equals("Asia/Tokyo")) {

        }else if (text.equals("Asia/Singapore")) {

        }else if (text.equals("Australia/Canberra")) {

        }else if (text.equals("India/Kolkata")) {

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}