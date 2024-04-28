package com.example.savingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.savingapp.databinding.ActivityMonopolBinding;
import com.example.savingapp.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Monopol extends AppCompatActivity {

    private ImageView player;

    String indx;
    private User playerdata;
    ActivityMonopolBinding binding1;

    int sum;
    Double buget=0.0,expnens,earning,intrate,botbug,botexpn,botearn,botloc,botrate,loc;
    private Button rollDiceButton;
    int[] xarry={
            -80,
            -120,
            -152,
            -183,
            -220,
            -250,
            -283,
            -320,
            -350,
            -383,
            -420,
            -420,
            -420,
            -420,
            -420,
            -420,
            -420,
            -420,
            -420,
            -420,
            -420,
            -383,
            -350,
            -320,
            -283,
            -250,
            -220,
            -183,
            -152,
            -120,
            -80,
            -80,
            -80,
            -80,
            -80,
            -80,
            -80,
            -80,
            -80,
            -80,
    };
    int[] yarry={
        510,
        510,
        510,
        510,
        510,
        510,
        510,
        510,
        510,
        510,
        510,
        475,
        440,
        410,
        375,
        340,
        310,
        275,
        240,
        210,
        175,
        175,
        175,
        175,
        175,
        175,
        175,
        175,
        175,
        175,
        175,
        210,
        240,
        275,
        310,
        340,
        375,
        410,
        440,
        475,
    };

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding1 = ActivityMonopolBinding.inflate(getLayoutInflater());
        setContentView(binding1.getRoot());

        indx=sharedPreferences.getString("mail",DOMAIN_VERIFICATION_SERVICE);
        playerdata=readdata(indx);
        sum = Integer.valueOf(String.valueOf(playerdata.getLoc()));
        Button rollDiceButton = findViewById(R.id.rolDiceButton);
        rollDiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement dice rolling logic
                int diceResult = rollDice();
                sum += diceResult;
                if(sum>=40) {
                    sum = sum - 40;
                    playerdata.setBuget(playerdata.getBuget()+playerdata.getEarning());
                }
                player= findViewById(R.id.imageView2);
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) player.getLayoutParams();
                lp.topMargin=yarry[sum];
                lp.leftMargin=xarry[sum];
                player.setLayoutParams(lp);
                if((sum==2) || (sum==17) || (sum==33))
                {
                    playerdata.setBuget(playerdata.getBuget()+10.0);
                    Toast.makeText(Monopol.this, "Bonus", Toast.LENGTH_SHORT).show();
                }
                if((sum==7) || (sum==36))
                {
                    Toast.makeText(Monopol.this, "WOP", Toast.LENGTH_SHORT).show();
                }
                if(sum==12)
                {
                    playerdata.setEarning(playerdata.getEarning()+10.0);
                    Toast.makeText(Monopol.this, "You got promoted", Toast.LENGTH_SHORT).show();
                }





            }
        });
    }

    // Method to simulate rolling a six-sided dice
    private int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

    private User readdata(String email)
    {
        User user = new User();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        //User user = null;

        reference.child(email).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task)
            {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        Toast.makeText(Monopol.this, "task is scuccessful", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot=task.getResult();
                        buget= Double.valueOf((Double) dataSnapshot.child("buget").getValue());
                        user.setBuget(buget);
                        botbug= Double.valueOf((Double) dataSnapshot.child("botbug").getValue());
                        user.setBotbug(botbug);
                        botearn= Double.valueOf((Double) dataSnapshot.child("botearn").getValue());
                        user.setBotearn(botearn);
                        botloc= Double.valueOf((Double) dataSnapshot.child("botloc").getValue());
                        user.setBotloc(botloc);
                        botexpn= Double.valueOf((Double) dataSnapshot.child("botexpn").getValue());
                        user.setBotexpn(botexpn);
                        botrate= Double.valueOf((Double) dataSnapshot.child("botrate").getValue());
                        user.setBotrate(botrate);
                        loc= Double.valueOf((Double) dataSnapshot.child("loc").getValue());
                        user.setLoc(loc);
                        expnens= Double.valueOf((Double) dataSnapshot.child("expens").getValue());
                        user.setExpnens(expnens);
                        intrate= Double.valueOf((Double) dataSnapshot.child("intrate").getValue());
                        user.setIntrate(intrate);
                        earning= Double.valueOf((Double) dataSnapshot.child("earning").getValue());
                        user.setEarning(earning);
                        user.setEmail(email);
                         //user = new User(email,Double.valueOf(buget),expnens,earning,intrate);

                    }
                } else {
                    Toast.makeText(Monopol.this, "task is unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return user;
    }

}