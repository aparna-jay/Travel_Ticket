package com.uee.travel_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uee.travel_ticket.Models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {
    TextView username, email, type, phone, password;
    List<UserModel> users;
    DatabaseReference databaseUsers;
    Button addUser;
    UserModel user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        username = (TextView) findViewById(R.id.username);
        type = (TextView) findViewById(R.id.type);
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone);
        password = (TextView) findViewById(R.id.password);
        addUser = (Button) findViewById(R.id.addUser);

        users = new ArrayList<>();

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUsers();
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

                //clearing the previous artist list
                users.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    UserModel user = postSnapshot.getValue(UserModel.class);
                    //adding artist to the list
                    Log.e("UserList", " " + user.getUsername()+ user.getType()+ user.getEmail()
                            + user.getPhone()+ user.getPassword());
                    users.add(user);
                }
//                //creating adapter
//                UserList artistAdapter = new ArtistList(MainActivity.this, artists);
//                //attaching adapter to the listview
//                listViewArtists.setAdapter(artistAdapter);
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
    private void addUsers() {
        //getting the values to save
        String name = username.getText().toString().trim();
        String userType = type.getText().toString().trim();
        String email1 = email.getText().toString().trim();
        String phone1 = phone.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        //       String genre = spinnerGenre.getSelectedItem().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = name;

            //creating an Artist Object
            UserModel user = new UserModel(id, name,userType,email1,phone1,password1, 0.00);

            //Saving the Artist
            databaseUsers.child(id).setValue(user);

            //setting edittext to blank again
            username.setText("");
            type.setText("");
            email.setText("");
            phone.setText("");
            password.setText("");

            //displaying a success toast
            Toast.makeText(this, "User added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}