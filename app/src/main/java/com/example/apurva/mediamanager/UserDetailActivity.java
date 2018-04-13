package com.example.apurva.mediamanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDetailActivity extends AppCompatActivity implements View.OnClickListener{
    Intent intent;
    EditText Name, Mobile, Dob;
    RadioGroup gender;
    RadioButton Gender;
    String name_val, mobile_val, dob_val, gender_val;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.next_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_val=Name.getText().toString().trim();
                mobile_val=Mobile.getText().toString().trim();
                dob_val=Dob.getText().toString().trim();
                int selectedID  =gender.getCheckedRadioButtonId();
                Gender=findViewById(selectedID);
                gender_val= Gender.getText().toString().trim();
                setInfo();
                intent=new Intent(getApplicationContext(),MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        Name=findViewById(R.id.full_name);
        Mobile =findViewById(R.id.Mobile);
        Dob= findViewById(R.id.DateOfBirth);
        gender=findViewById(R.id.Gender_radio);

        //female=findViewById(R.id.Female);





    }

    private void setInfo(){
        database=FirebaseDatabase.getInstance().getReference().child("Users");


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        /*myRef= database.getReference("Name");
                myRef.setValue(name_val);
        myRef= database.getReference("Mobile");
        myRef.setValue(mobile_val);
        myRef= database.getReference("Date Of Birth");
        myRef.setValue(dob_val);
        myRef= database.getReference("Gender");
        myRef.setValue(gender_val);*/

    }

    @Override
    public void onClick(View view) {

    }
}
