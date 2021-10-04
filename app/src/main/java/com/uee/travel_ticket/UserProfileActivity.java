package com.uee.travel_ticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uee.travel_ticket.Models.UserModel;

public class UserProfileActivity extends AppCompatActivity {

    String user;
    private FirebaseUser fUser;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ImageButton back = (ImageButton) findViewById(R.id.back);
        Button logout = (Button) findViewById(R.id.logout);
        if (LoginActivity.loggedUser == null){
            user = "null";
        }
        else {
            user = LoginActivity.loggedUser;
        }
        Log.e("Logged User", user);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("LocalPassnger");
//        user = fUser.getUid();

        final TextView userNameT = (TextView) findViewById(R.id.username);
        final TextView userAddressT = (TextView) findViewById(R.id.userAddress);
        final TextView userNicT = (TextView) findViewById(R.id.nic);
        final TextView userPasswordT = (TextView) findViewById(R.id.password);

        reference.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userProfile = snapshot.getValue(UserModel.class);

                if(userProfile !=null){
                     String name = userProfile.name;
                     String address = userProfile.address;
                     String nic = userProfile.nic;
                     String password = userProfile.password;

                     userNameT.setText(name);
                     userAddressT.setText(address);
                     userNicT.setText(nic);
                     userPasswordT.setText(password);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfileActivity.this, "Error!", Toast.LENGTH_SHORT).show();

            }
        });

 }





}