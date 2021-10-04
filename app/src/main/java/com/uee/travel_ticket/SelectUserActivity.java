package com.uee.travel_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SelectUserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner usersSpinner;
    Button next;
    public static String userType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        next = findViewById(R.id.next);

        // spinner element
        usersSpinner = (Spinner) findViewById(R.id.userSpinner);
        // Spinner click listener
        usersSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        // Spinner Drop down elements
        List<String> users = new ArrayList<String>();
        users.add("Local Passenger");
        users.add("Foreign Passenger");
//        users.add("Inspector");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterType = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, users);
        // Drop down layout style - list view with radio button
        dataAdapterType.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        usersSpinner.setAdapter(dataAdapterType);

        //go to the create user profile acctivity.
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( userType.equals("Local Passenger") ) {
                    Intent intent = new Intent(SelectUserActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                }
                if ( userType.equals("Foreign Passenger") ) {
                    Intent intent = new Intent(SelectUserActivity.this, ForeignRegisterActivity.class);
                    startActivity(intent);
                }
//                if ( userType.equals("Inspector") ) {
//                    Intent intent = new Intent(LoginActivity.this, SplashScreenActivity.class);
//                    startActivity(intent);
//                }
            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        userType = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}