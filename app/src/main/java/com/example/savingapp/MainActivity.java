package com.example.savingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sigh = findViewById(R.id.sighup);
        sigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mysigh = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(mysigh);
            }

        });

        Button enter1 = findViewById(R.id.enter);
        enter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myentr = new Intent(getApplicationContext(), EnterActivity.class);
                startActivity(myentr);
            }

        });;


    }

}
