package com.uee.travel_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class UserProfileActivity extends AppCompatActivity {

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        if (LoginActivity.loggedUser == null){
            user = "null";
        }
        else {
            user = LoginActivity.loggedUser;
        }
        Log.e("Logged User", user);
    }
}