package com.example.emptyviewstemplate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class HitsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Integer hits;
    private TextView msg;
    private SharedPreferences myPrefs;
    private EditText homeCity;
    String homeCityStr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hits);

        myPrefs = getSharedPreferences("myUserPrefs", Context.MODE_PRIVATE);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.times_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        homeCity = findViewById(R.id.textView8);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        TextView timezone = (TextView) findViewById(R.id.textView4);
        TextView timezoneName = (TextView) findViewById(R.id.textView3);

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

        homeCityStr = homeCity.getText().toString();

        SharedPreferences.Editor editor = myPrefs.edit();

        editor.putString("textView8", homeCityStr);
        editor.commit();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

