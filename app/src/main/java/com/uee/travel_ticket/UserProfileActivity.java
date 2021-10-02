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

        if (LoginActivity.loggedUser == null){
            user = "null";
        }
        else {
            user = LoginActivity.loggedUser;
        }
        Log.e("Logged User", user);

        //go to the create account acctivity.
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
//        user = fUser.getUid();

        final TextView userNameT = (TextView) findViewById(R.id.username);
        final TextView userTypeT = (TextView) findViewById(R.id.userType);
        final TextView userEmailT = (TextView) findViewById(R.id.email);
        final TextView userPhoneT = (TextView) findViewById(R.id.phone);
        final TextView userPasswordT = (TextView) findViewById(R.id.password);
//        final TextView userCPasswordT = (TextView) findViewById(R.id.cpassword);

        reference.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userProfile = snapshot.getValue(UserModel.class);

                if(userProfile !=null){
//                    String username = userProfile.username;
//                    String type = userProfile.type;
//                    String email = userProfile.email;
//                    String phone = userProfile.phone;
                    String password = userProfile.password;
//                    String username = userProfile.username;

//                    userNameT.setText(username);
//                    userTypeT.setText(type);
//                    userEmailT.setText(email);
//                    userPhoneT.setText(phone);
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