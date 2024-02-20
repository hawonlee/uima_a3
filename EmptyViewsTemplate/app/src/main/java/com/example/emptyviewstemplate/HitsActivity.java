package com.example.emptyviewstemplate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
    SharedPreferences.Editor peditor;
    private TextView homeCity;
    private String homeCityStr;

    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hits);

        myPrefs = getSharedPreferences("myUserPrefs", Context.MODE_PRIVATE);

        homeCityStr = myPrefs.getString("homeCity", "");

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.times_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        homeCity = (TextView) findViewById(R.id.textView3);
        //spinner.setOnItemSelectedListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        TextView timezone = (TextView) findViewById(R.id.textView4);
        TextView timezoneName = (TextView) findViewById(R.id.textView3);

        if (text.equals("America/New_York")) {
            timezone.setText("GMT -05:00");
            timezoneName.setText("America/New_York");
            homeCityStr = "America/New_York";
        } else if (text.equals("America/Los_Angeles")) {
            timezone.setText("GMT -08:00");
            timezoneName.setText("America/Los_Angeles");
            homeCityStr = "America/Los_Angeles";
        } else if (text.equals("Europe/Berlin")) {
            timezone.setText("GMT +01:00");
            timezoneName.setText("Europe/Berlin");
            homeCityStr = "Europe/Berlin";
        } else if (text.equals("Europe/Istanbul")) {
            timezone.setText("GMT +02:00");
            timezoneName.setText("Europe/Istanbul");
            homeCityStr = "Europe/Istanbul";
        } else if (text.equals("Asia/Singapore")) {
            timezone.setText("GMT +08:00");
            timezoneName.setText("Asia/Singapore");
            homeCityStr = "Asia/Singapore";
        } else if (text.equals("Asia/Tokyo")) {
            timezone.setText("GMT +09:00");
            timezoneName.setText("Asia/Tokyo");
            homeCityStr = "Asia/Tokyo";
        } else if (text.equals("Australia/Canberra")) {
            timezone.setText("GMT +10:00");
            timezoneName.setText("Australia/Canberra");
            homeCityStr = "Australia/Canberra";
        } else {
            timezone.setText("GMT +05:30");
            timezoneName.setText("Maldives/Male");
            homeCityStr = "Maldives/Male";
        }

        homeCityStr = homeCity.getText().toString();

        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString("textView3", homeCityStr);
        editor.apply();
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putString("homeCity", homeCityStr);
        peditor.apply();

        super.onPause();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void mainActivity(View view) {
        Intent intent = new Intent(HitsActivity.this, MainActivity.class);
        intent.putExtra("newHomeCity", homeCityStr);
        startActivity(intent);
    }
}

