package com.example.savingapp;

import static com.example.savingapp.R.id.backto_button;
import static com.example.savingapp.R.id.checkBox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

public class EnterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText enterpass,enteremail;
    CheckBox checkbox ;
    private Button enterbutton,tofirst;
    Random r = new Random();
    Double tag = -10.0 + r.nextDouble() * 20.0;

    Random d = new Random();
    Double maf = -10.0 + d.nextDouble() * 20.0;

    Random a = new Random();
    Double aapl = -10.0 + a.nextDouble() * 20.0;

    SharedPreferences sharedPreferences;
    Random b = new Random();
    Double boa = -10.0 + b.nextDouble() * 20.0;

    Random w = new Random();
    Double wwe = -10.0 + w.nextDouble() * 20.0;

    Random g = new Random();
    Double god = -10.0 + g.nextDouble() * 20.0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        auth = FirebaseAuth.getInstance();
        enteremail = findViewById(R.id.login_email);
        enterpass = findViewById(R.id.login_pass);
        enterbutton = findViewById(R.id.login_button);
        tofirst = findViewById(R.id.backto_button);
        tofirst.setOnClickListener(v -> showConfirmationDialog());

        enterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = enteremail.getText().toString();
                String pass = enterpass.getText().toString();

                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if(!pass.isEmpty()){
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        sharedPreferences= getSharedPreferences("mail",MODE_PRIVATE);

                                        Toast.makeText( EnterActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor editor=sharedPreferences.edit();
                                        editor.putString("mail",email);
                                        editor.apply();
                                        //sharedPreferences= getSharedPreferences("TAG",MODE_PRIVATE);
                                        //SharedPreferences.Editor editor1=sharedPreferences.edit();
                                        //editor1.putLong("TAG",Double.doubleToLongBits(tag));
                                        //editor1.apply();
                                        //sharedPreferences= getSharedPreferences("MAF",MODE_PRIVATE);
                                        //SharedPreferences.Editor editor2=sharedPreferences.edit();
                                        //editor2.putLong("MAF",Double.doubleToLongBits(maf));
                                        //editor2.apply();
                                        //sharedPreferences= getSharedPreferences("AAPL",MODE_PRIVATE);
                                        //SharedPreferences.Editor editor3=sharedPreferences.edit();
                                        //editor3.putLong("AAPL",Double.doubleToLongBits(aapl));
                                        //editor3.apply();
                                        //sharedPreferences= getSharedPreferences("BOA",MODE_PRIVATE);
                                        //SharedPreferences.Editor editor4=sharedPreferences.edit();
                                        //editor4.putLong("BOA",Double.doubleToLongBits(boa));
                                        //editor4.apply();
                                        //sharedPreferences= getSharedPreferences("WWE",MODE_PRIVATE);
                                        //SharedPreferences.Editor editor5=sharedPreferences.edit();
                                        //editor5.putLong("WWE",Double.doubleToLongBits(wwe));
                                        //editor5.apply();
                                        //sharedPreferences= getSharedPreferences("GOD",MODE_PRIVATE);
                                        //SharedPreferences.Editor editor6=sharedPreferences.edit();
                                        //editor6.putLong("GOD",Double.doubleToLongBits(god));
                                        //editor6.apply();
                                        startActivity(new Intent(EnterActivity.this, HowmuchSaved.class ));

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText( EnterActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        enterpass.setError("passwords cant be empty");
                    }
                } else if (email.isEmpty()){
                    enteremail.setError("email cant be empty");
                }else {
                    enteremail.setError("please enter a valid email");
                }
            }
        });
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null); // Replace dialog_layout with your custom layout file
        CheckBox checkbox = view.findViewById(R.id.checkBox);
        builder.setView(view);
        builder.setTitle("Confirmation");
        builder.setMessage("Do you want to go back to the sign up window ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (checkbox.isChecked()) {
                    Toast.makeText(EnterActivity.this, "Checkbox is checked and action is performed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EnterActivity.this, SignUpActivity.class));
                } else {
                    Toast.makeText(EnterActivity.this, "Please check the checkbox", Toast.LENGTH_SHORT).show();
                }
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(EnterActivity.this, "No clicked", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

}