package com.uee.travel_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uee.travel_ticket.Models.ForeignUserModel;
import com.uee.travel_ticket.Models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class ForeignRegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //registration
    TextView username, passportId, country, address,password, cpassword;
    List<ForeignUserModel> users;
    DatabaseReference databaseUsers;
    Button addUser, login;
    UserModel user;

//    //for spinner
    Spinner usersSpinner;
    public static String packageName;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreign_register);

        databaseUsers = FirebaseDatabase.getInstance().getReference("ForeignPassanger");

        username = (TextView) findViewById(R.id.fusername);
        country = (TextView) findViewById(R.id.fcountry);
        passportId = (TextView) findViewById(R.id.passportId);
        address = (TextView) findViewById(R.id.faddress);
        usersSpinner = (Spinner) findViewById(R.id.userSpinner);
        password = (TextView) findViewById(R.id.password);
        cpassword = (TextView) findViewById(R.id.cpassword);
        addUser = (Button) findViewById(R.id.addUser);
//        login = (Button) findViewById(R.id.addUser);

        users = new ArrayList<ForeignUserModel>();

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validatePassportID(passportId.getText().toString().trim())) {
                    if(checkIfPasswordsMatch(password.getText().toString().trim(), cpassword.getText().toString().trim())) {
                        addUsers();
                    }
                    else{
                       Toast.makeText(ForeignRegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(ForeignRegisterActivity.this, "Passport ID is invalid", Toast.LENGTH_SHORT).show();

                }            }
        });

        // spinner element
        usersSpinner = (Spinner) findViewById(R.id.userSpinner);
        // Spinner click listener
        usersSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        // Spinner Drop down elements
        List<String> packages = new ArrayList<String>();
        packages.add("Package Types");
        packages.add("Single and Returns");
        packages.add("Family Day Passes");
        packages.add("Day Passes");
        packages.add("Young People");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterType = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, packages);
        // Drop down layout style - list view with radio button
        dataAdapterType.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        usersSpinner.setAdapter(dataAdapterType);
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        packageName = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous user list
                users.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting Foreign user
                    ForeignUserModel user = postSnapshot.getValue(ForeignUserModel.class);

                    users.add(user);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addUsers() {
        //getting the values to save
        String name1 = username.getText().toString().trim();
        String passportID1 = passportId.getText().toString().trim();
        String packageName = ForeignRegisterActivity.packageName;
        String country1 = country.getText().toString().trim();
        String address1 = address.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        //       String genre = spinnerGenre.getSelectedItem().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name1)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = name1;

            //creating an Artist Object
            ForeignUserModel user = new ForeignUserModel(id, name1,passportID1, country1 ,address1,password1,packageName);

            //Saving the Artist
            databaseUsers.child(id).setValue(user);

            //setting edittext to blank again
            username.setText("");
            passportId.setText("");
            packageName.compareTo("");
            country.setText("");
            address.setText("");
            password.setText("");
            cpassword.setText("");

            //displaying a success toast
            Toast.makeText(this, "Foreign User added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

    public static boolean validatePassportID(String passportID){
        String passportIDPattern = "^(?!^0+$)[a-zA-Z0-9]{3,20}$";
        return(passportID.matches(passportIDPattern));
    }
    public static boolean checkIfPasswordsMatch(String userPassword, String cPassword){
        return(userPassword.equals(cPassword));
    }
}