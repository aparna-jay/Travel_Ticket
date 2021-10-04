package com.uee.travel_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uee.travel_ticket.Models.ReportPassengerModel;
import com.uee.travel_ticket.Models.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReportPassenger extends AppCompatActivity {

    String inspectorID;
    TextView tvInspectorID, date, time, tvpassengerID, tvdescription;
    List<ReportPassengerModel> reportPassengers;
    DatabaseReference databaseReference, updateReference;
    Button report, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_passenger);

        databaseReference = FirebaseDatabase.getInstance().getReference("PassangerComplain");
        tvInspectorID = (TextView) findViewById(R.id.inspectorID);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        tvpassengerID = (TextView) findViewById(R.id.passengerID);
        tvdescription = (TextView) findViewById(R.id.description);
        report = (Button) findViewById(R.id.report);
        back = (Button) findViewById(R.id.back);

        reportPassengers = new ArrayList<>();

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

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addComplain();
                updateData();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportPassenger.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                reportPassengers.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ReportPassengerModel user = postSnapshot.getValue(ReportPassengerModel.class);
                    reportPassengers.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addComplain() {
        //getting the values to save
        String inspectorID1 = tvInspectorID.getText().toString().trim();
        String passengerID1 = tvpassengerID.getText().toString().trim();
        String date1 = date.getText().toString().trim();
        String time1 = time.getText().toString().trim();
        String description1 = tvdescription.getText().toString().trim();


        //checking if the value is provided
        if (!TextUtils.isEmpty(passengerID1)) {

            String id = passengerID1;

            //creating an Artist Object
            ReportPassengerModel user = new ReportPassengerModel(date1, description1, passengerID1, inspectorID1, time1);

            //Saving the Artist
            databaseReference.child(id).setValue(user);

            //displaying a success toast
            Toast.makeText(this, "User added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

    private void updateData() {
        String passengerID1 = tvpassengerID.getText().toString().trim();
        Log.e("", passengerID1);
        updateReference = FirebaseDatabase.getInstance().getReference();
        //ref.child("myDb").child("awais@gmailcom").child("leftSpace").setValue("YourDateHere");
        updateReference.child("LocalPassnger").child(passengerID1).child("accStatus").setValue("hold");

        //setting edittext to blank again
        tvpassengerID.setText("");
        tvdescription.setText("");
    }
}