package com.uee.travel_ticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.uee.travel_ticket.Models.PaymentInfoModel;
import com.uee.travel_ticket.Models.UserModel;

import java.text.BreakIterator;

public class CreditBalanceActivity extends AppCompatActivity {
    String user;
    private FirebaseUser fUser;
    private DatabaseReference reference, reference1;
    PaymentInfoModel pay1;
//    Button buttonUpdate;
    String rechargeAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_balance);

        ImageButton back = (ImageButton) findViewById(R.id.back);
        Button pay = (Button) findViewById(R.id.pay);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreditBalanceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        if (LoginActivity.loggedUser == null){
            user = "null";
        }
        else {
            user = LoginActivity.loggedUser;
        }
        Log.e("Logged User", user);

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("LocalPassnger");

//        user = fUser.getUid();

        final TextView userAccBalanceT = (TextView) findViewById(R.id.credit);

        reference.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel creditBalance = snapshot.getValue(UserModel.class);

                if(creditBalance !=null){
                    String accBalance = creditBalance.accBalance;

                    userAccBalanceT.setText(accBalance);

                    pay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(CreditBalanceActivity.this, PaymentInformationActivity.class);
                            intent.putExtra("creditBalance", accBalance);
                            startActivity(intent);
                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CreditBalanceActivity.this, "Error!", Toast.LENGTH_SHORT).show();

            }
        });

//        //update
//        buttonUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                reference1 = FirebaseDatabase.getInstance().getReference("credit");
//                String accBalance1 = accBalance.getText().toString().trim();
//
//                //  clearControls();
//
//                //checking if the value is provided
//                if (!TextUtils.isEmpty(accBalance1)) {
//
//                    //getting a unique id using push().getKey() method
//                    //it will create a unique id and we will use it as the Primary Key for our Artist
//                    String id = accBalance1;
//
//                    //creating an Artist Object
//                    PaymentInfoModel pay1 = new PaymentInfoModel(accBalance1);
//
//                    //Saving the Artist
//                    reference1.child(id).setValue(pay1);
//
//                    //setting edittext to blank again
//                    accBalance.setText("");
//
//                    //displaying a success toast
//                    Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
//                } else {
//                    //if the value is not given displaying a toast
//                    Toast.makeText(getApplicationContext(), "Enter valid details!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        });
    }
}