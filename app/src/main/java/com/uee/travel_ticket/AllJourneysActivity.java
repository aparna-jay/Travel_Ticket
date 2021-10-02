package com.uee.travel_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_journeys);



           // Intent intent = getIntent();

            /*
             * this line is important
             * this time we are not getting the reference of a direct node
             * but inside the node track we are creating a new child with the artist id
             * and inside that node we will store all the tracks with unique ids
             * */
            dbref = FirebaseDatabase.getInstance().getReference("Passenger_Journeys");

        journeylistView = (ListView) findViewById(R.id.journeylistView);

        journeyList = new ArrayList<>();

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
