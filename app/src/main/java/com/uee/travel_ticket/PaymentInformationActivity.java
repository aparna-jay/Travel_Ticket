package com.uee.travel_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uee.travel_ticket.Models.PaymentInfoModel;
import com.uee.travel_ticket.Models.UserModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentInformationActivity extends AppCompatActivity {

    TextView cardHoldername, cType, ccNum, cvv, exDate, rAmount;
    List<PaymentInfoModel> pay;
    DatabaseReference databaseUsers;
    Button addPayment;
    PaymentInfoModel pay1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);

        databaseUsers = FirebaseDatabase.getInstance().getReference("credit");
        //hooks
        cardHoldername = (TextView) findViewById(R.id.tbName);
        cType = (TextView) findViewById(R.id.tbCardType);
        ccNum = (TextView) findViewById(R.id.tbccNumber);
        cvv = (TextView) findViewById(R.id.tbCVV);
        exDate = (TextView) findViewById(R.id.tbEXPDate);
        rAmount = (TextView) findViewById(R.id.tbramount);
        addPayment = (Button) findViewById(R.id.btnpay);

        pay = new ArrayList<>();


        addPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPaymentInfo();

            }
        });

        //go to the next page using an intent
        addPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CreditBalanceActivity.class);
                startActivity(i);
            }
        });


        ImageButton back = (ImageButton) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentInformationActivity.this, CreditBalanceActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pay.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    PaymentInfoModel pay1 = postSnapshot.getValue(PaymentInfoModel.class);
                    pay.add(pay1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /*
     * This method is saving a new artist to the
     * Firebase Realtime Database
     * */
    private void addPaymentInfo() {
        //getting the values to save

        String cardHoldername1 = cardHoldername.getText().toString().trim();
        String cType1 = cType.getText().toString().trim();
        String ccNum1 = ccNum.getText().toString().trim();
        String cvv1 = cvv.getText().toString().trim();
        String exDate1 = exDate.getText().toString().trim();
        String rAmount1 = rAmount.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(cardHoldername1)) {

            //getting a unique id using push().getKey() method
            String id = cardHoldername1;

            //creating an Object
            PaymentInfoModel pay1 = new PaymentInfoModel(id, cardHoldername1,cType1, ccNum1,cvv1, exDate1, rAmount1);

            //Saving
            databaseUsers.child(id).setValue(pay1);

            //setting edittext to blank again
            cardHoldername.setText("");
            cType.setText("");
            ccNum.setText("");
            cvv.setText("");
            exDate.setText("");
            rAmount.setText("");

            //displaying a success toast
            Toast.makeText(this, "Payment Information added successfully!", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a valid card details!", Toast.LENGTH_LONG).show();
        }
    }

//    private void updatePaymentInfo(){
//
//        databaseUsers = FirebaseDatabase.getInstance().getReference().child("credit");
//
//        UserModel user = new UserModel(id, name, address, nic,password,accBalance, accStatus);
//        databaseUsers.setValue(rAmount.getText().toString().trim());
//
//    }


}