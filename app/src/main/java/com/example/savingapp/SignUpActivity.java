package com.example.savingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.example.savingapp.databinding.ActivitySignUpBinding;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    ActivitySignUpBinding binding;
    Double buget,expns;
    FirebaseDatabase database;
    DatabaseReference mDatabase;
    private EditText sighupemail, sighuppass,sighupname;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignUpBinding.inflate(getLayoutInflater());
        database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(binding.getRoot());


        back = findViewById(R.id.button3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myenter = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myenter);
            }

        });
        auth = FirebaseAuth.getInstance();
        sighupemail = findViewById(R.id.sighup_email);
        sighuppass = findViewById(R.id.sighup_pass);

        binding.sighupLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String email = sighupemail.getText().toString().trim();
                String pass = sighuppass.getText().toString().trim();

                if (!email.isEmpty())
                {
                    checkEmailExists(email);
                }
                else
                {
                    sighupemail.setError("email cant be empty");
                }
                if (!pass.isEmpty() )
                {
                    auth.createUserWithEmailAndPassword(email,  pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "sign up successful" , Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, EnterActivity.class));
                            } else{
                                Toast.makeText( SignUpActivity.this, "Sign up failed" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    sighuppass.setError("password cant be empty");
                }
            }
        });
    }


    private void checkEmailExists(String email) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        String encodedemail =encodeEmail(email);
        reference.child(encodedemail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Email exists
                    Toast.makeText(SignUpActivity.this, "This email is already in use. Please try another.", Toast.LENGTH_SHORT).show();
                } else {
                    // Email doesn't exist
                    Toast.makeText(SignUpActivity.this, "This email is available", Toast.LENGTH_SHORT).show();
                    buget=10000.0;
                    expns=12.0;
                    Double earn=50.0;
                    Double intrate=0.1;
                    Double loc=0.0;
                    Double botloc=0.0;
                    Double botearn=45.0;
                    Double botbug=9000.0;
                    Double botexpn=12.0;
                    Double botrate=0.1;
                    Double debt=0.0;
                    Double botdebt=0.0;
                    String houses="0030000300303000030030300000303003003000";
                    Toast.makeText(SignUpActivity.this, "stage 1", Toast.LENGTH_SHORT).show();
                    final User users = new User(String.valueOf(encodedemail),Double.valueOf(buget),Double.valueOf(expns),earn,intrate,botexpn,botbug,botearn,botrate,debt,botdebt,loc,botloc,houses);
                    Toast.makeText(SignUpActivity.this, "stage 2", Toast.LENGTH_SHORT).show();
                    // Create a new user with the given email
                    mDatabase.child("Users").child(encodedemail).setValue(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(SignUpActivity.this, "we did it",Toast.LENGTH_SHORT);
                        }
                    });


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(SignUpActivity.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Function to encode the email
    private String encodeEmail(String email) {
        return email.replace(".", "_dot_")
                .replace("@", "_at_");
    }


}