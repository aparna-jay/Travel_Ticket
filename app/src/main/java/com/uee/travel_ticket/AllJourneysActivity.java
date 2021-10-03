package com.uee.travel_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uee.travel_ticket.Models.journeyclass;

import java.util.ArrayList;
import java.util.List;

public class AllJourneysActivity extends AppCompatActivity {
    ListView journeylistView;
    DatabaseReference dbref;
    List<journeyclass> journeyList;
    ImageButton back = (ImageButton) findViewById(R.id.back);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_journeys);

        dbref = FirebaseDatabase.getInstance().getReference("Passenger_Journeys");

        journeylistView = (ListView) findViewById(R.id.journeylistView);

        journeyList = new ArrayList<>();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllJourneysActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

       }

        @Override
        protected void onStart() {
            super.onStart();

            dbref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    journeyList.clear();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        journeyclass track = postSnapshot.getValue(journeyclass.class);
                        journeyList.add(track);
                    }
                    JourneyListAdapter trackListAdapter = new JourneyListAdapter(AllJourneysActivity.this, journeyList);
                    journeylistView.setAdapter(trackListAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
