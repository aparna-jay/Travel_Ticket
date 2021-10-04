package com.uee.travel_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReportPassenger extends AppCompatActivity {

    String inspectorID;
    TextView tvInspectorID, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_passenger);

        tvInspectorID = (TextView) findViewById(R.id.inspectorID);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);

        //Get inspector ID
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.inspectorID = extras.getString("user");
        }

        //Get current date and format it
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        //Get current time
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        //Set values to text views
        tvInspectorID.setText(inspectorID);
        date.setText(formattedDate);
        time.setText(currentTime.toString());


    }
}