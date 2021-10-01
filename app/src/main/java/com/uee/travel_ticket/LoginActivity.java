package com.uee.travel_ticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Button createAccount, login;
    EditText username, password;
    DatabaseReference databaseUsers;
//    login_to userprofile;
    public static String loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//Initialize.
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        createAccount = findViewById(R.id.signUp);
        login = findViewById(R.id.signUp);

//        userprofile = new login_to();

        //go to the create account acctivity.
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateAcount();
            }
        });

        //go to the create user profile acctivity.
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin();
            }
        });

    }

    public void openCreateAcount() {
        Intent i = new Intent(this, RegistrationActivity.class);
        startActivity(i);
    }

    public void openlogin() {

        final String userEnteredUsername = username.getText().toString().trim();
        final String userEnteredPassword = password.getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");

        Query checkUser = reference.orderByChild("id").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);


                    if (passwordFromDB.equals(userEnteredPassword)) {
                        Toast.makeText(getApplicationContext(), "valid user", Toast.LENGTH_SHORT).show();
                        loggedUser = username.getText().toString();
                        Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);
                        intent.putExtra("user",username.getText().toString());
                        startActivity(intent);
                        finish();


                    } else {
                        password.setError("Invalid Password");
//                        Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }else{
                    password.setError("Invalid Username");
//                    Toast.makeText(getApplicationContext(), "No such User exist", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}