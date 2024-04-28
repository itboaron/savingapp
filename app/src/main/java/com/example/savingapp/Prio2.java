package com.example.savingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.savingapp.databinding.ActivityPrio2Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Prio2 extends AppCompatActivity {


    ActivityPrio2Binding binding;
    String email="";
    Double buget,expns;
    FirebaseDatabase database;
    DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrio2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();



        binding.contin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent mycon;
                    mycon = new Intent(Prio2.this, Monopol.class);
                    startActivity(mycon);
                    Toast.makeText( Prio2.this, "enter your name and press the button enter your name.", Toast.LENGTH_SHORT).show();
            }

        });


        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Prio2.this,"Info",Toast.LENGTH_SHORT).show();
                email=binding.name.getText().toString();
                buget=10000.0;
                expns=0.0;
                Double earn=50.0;
                Double intrate=0.1;
                Double loc=0.0;
                Double botloc=0.0;
                Double botearn=45.0;
                Double botbug=9000.0;
                Double botexpn=12.0;
                Double debt=0.0;
                Double botdebt=0.0;
                Double botrate=0.1;
                String houses="0030000300303000030030300000303003003000";
                 User users= new User(email,Double.valueOf(buget),Double.valueOf(expns),earn,intrate,debt,botdebt,botexpn,botbug,botearn,botrate,loc,botloc,houses);
                 mDatabase.child("Users").child(email).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         Toast.makeText(Prio2.this,"Info saved",Toast.LENGTH_SHORT).show();
                         Intent mycon= new Intent(Prio2.this, Monopol.class);
                         startActivity(mycon);

                     }
                 });
            }
        });

    }
}