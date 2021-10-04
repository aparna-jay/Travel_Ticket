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
import com.uee.travel_ticket.Models.ForeignUserModel;
import com.uee.travel_ticket.Models.UserModel;

public class ForeignerWelcomeActivity extends AppCompatActivity {
    String user;
    private FirebaseUser fUser;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreigner_welcome);

        Button start = (Button) findViewById(R.id.btnGenerateQRCode);

        if (LoginActivity.loggedUser == null){
            user = "null";
        }
        else {
            user = LoginActivity.loggedUser;
        }
        Log.e("Logged User", user);

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("ForeignPassanger");
//        user = fUser.getUid();

        final TextView packageName = (TextView) findViewById(R.id.packageName);

        reference.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ForeignUserModel creditBalance = snapshot.getValue(ForeignUserModel.class);

                if(creditBalance !=null){
                    String packageName1 = creditBalance.packageName;

                    packageName.setText(packageName1);

                    start.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ForeignerWelcomeActivity.this, ForeignerQRCodeActivity.class);
                            intent.putExtra("packageName", packageName1);
                            startActivity(intent);
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ForeignerWelcomeActivity.this, "Error!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}