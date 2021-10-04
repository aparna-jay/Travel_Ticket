package com.uee.travel_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uee.travel_ticket.Models.PaymentInfoModel;
import com.uee.travel_ticket.Models.UserModel;

import java.util.List;

public class PaymentInformationActivity extends AppCompatActivity {

    TextView cardHoldername, cType, ccNum, cvv, exDate, rAmount;
    List<PaymentInfoModel> users;
    DatabaseReference databaseUsers;
    Button addUser;
    UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);

        databaseUsers = FirebaseDatabase.getInstance().getReference("LocalPassnger");
        cardHoldername = (TextView) findViewById(R.id.tbName);
        cType = (TextView) findViewById(R.id.tbCardType);
        ccNum = (TextView) findViewById(R.id.tbccNumber);
        cvv = (TextView) findViewById(R.id.tbCVV);
        exDate = (TextView) findViewById(R.id.tbEXPDate);
        rAmount = (TextView) findViewById(R.id.tbramount);
        addUser = (Button) findViewById(R.id.btnpay);

        ImageButton back = (ImageButton) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentInformationActivity.this, CreditBalanceActivity.class);
                startActivity(intent);
            }
        });


    }
}