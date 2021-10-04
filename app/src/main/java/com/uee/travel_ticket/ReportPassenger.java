package com.uee.travel_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReportPassenger extends AppCompatActivity {

    String inspectorID;
    TextView tvInspectorID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_passenger);

        tvInspectorID = (TextView) findViewById(R.id.inspectorID);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.inspectorID = extras.getString("user");
        }

        tvInspectorID.setText(inspectorID);
    }
}