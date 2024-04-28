package com.example.savingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HowmuchSaved extends AppCompatActivity {

    private Button boardButton,stockButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howmuch_saved);
        stockButton = findViewById(R.id.stockButton);
        boardButton = findViewById(R.id.boardButton);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainerView2,new BoardFragment()).commit();

        stockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new StockFragment());
            }
        });

        boardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new BoardFragment());
            }
        });
    }
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, fragment)
                .commit();
    }
}