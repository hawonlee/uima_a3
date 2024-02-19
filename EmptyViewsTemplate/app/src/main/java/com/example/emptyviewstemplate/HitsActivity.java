package com.example.emptyviewstemplate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HitsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Integer hits;
    private TextView msg;
    private SharedPreferences myPrefs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hits);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.times_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);


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

