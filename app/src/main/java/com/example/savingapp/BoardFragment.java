package com.example.savingapp;
import java.lang.Thread;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.savingapp.databinding.ActivityMonopolBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Random;

import static android.content.Context.DOMAIN_VERIFICATION_SERVICE;
import static androidx.browser.customtabs.CustomTabsClient.getPackageName;
import static com.example.savingapp.R.id.imageViewrobot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private ImageView player;
    private ImageView botplayer;


    String indx,houses;
    private User playerdata;
    ActivityMonopolBinding binding1;

    int sum,botsum;
    DatabaseReference gamedata;
    Double buget=0.0,expnens,earning,intrate,botbug,botexpn,botearn,botloc,botrate,loc,debt,botdebt;
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
    int[] housearry;

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
    private String mParam2;

    public BoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BoardFragment newInstance(String param1, String param2) {
        BoardFragment fragment = new BoardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding1 = ActivityMonopolBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_board, container, false);

        sharedPreferences = getActivity().getSharedPreferences("mail", Context.MODE_PRIVATE);
        indx = sharedPreferences.getString("mail", "");
        indx=encodeEmail(indx);
        read_Data(indx, new UserCallback() {
            @Override
            public void onCallback(User user) {
                if (user != null) {
                    playerdata = user;
                } else {
                    showToast("something failed");
                }
            }
        });
        housearry=stringToIntArray(playerdata.getHouses());
        int t=0;
        for(t=0;t<housearry.length;t++)
        {
            if(housearry[t]==1 || housearry[t]==2)
            {
                String image = "imageView";
                String imageViewIdString = image + t;
                int imageViewId = getResources().getIdentifier(imageViewIdString, "id", requireActivity().getPackageName());
                ImageView imageViewhouse = view.findViewById(imageViewId);
                imageViewhouse.setVisibility(View.VISIBLE);
            }
        }
        gamedata = FirebaseDatabase.getInstance().getReference("Users");
        sum = Integer.valueOf(String.valueOf(playerdata.getLoc()));
        botsum = Integer.valueOf(String.valueOf(playerdata.getBotloc()));
        Button buyhouse = view.findViewById(R.id.button5);
        buyhouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(housearry[sum]==0) {
                    String image = "imageView";
                    String imageViewIdString = image + sum;
                    int imageViewId = getResources().getIdentifier(imageViewIdString, "id", requireActivity().getPackageName());
                    ImageView imageViewhouse = view.findViewById(imageViewId);
                    if(playerdata.getBuget()>=200.0) {
                        showToast("you have enough money to buy this house you just lost 200 coins");
                        playerdata.setBuget(playerdata.getBuget()-200.0);
                        housearry[sum]=1;
                        imageViewhouse.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Double d =200.0-playerdata.getBuget();
                        playerdata.setBuget(0.0);
                        playerdata.setDebt(playerdata.getDebt()+d);
                        Double exp = d/10;
                        playerdata.setExpnens(playerdata.getExpnens()+exp);
                        showToast("you dont have enough money, you have taken a loan of" + d + " which means each turn you will pay a extra " + exp + "every turn until all the money is returned");
                        housearry[sum]=1;
                        imageViewhouse.setVisibility(View.VISIBLE);
                    }

                }
                if(housearry[sum]==3)
                {
                    showToast("there is no house in here, what are you doing ?");
                }
                if(housearry[sum]==2 || housearry[sum]==1)
                {
                    showToast("this house is already taken, try another one");
                }
            }
        });

        Button saveback = view.findViewById(R.id.button6);
        saveback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerdata.setHouses(intArrayToString(housearry));
                changeData(playerdata.getBuget(),playerdata.getDebt(),playerdata.getBotdebt(),playerdata.getEarning(),playerdata.getIntrate(),playerdata.getExpnens(),playerdata.getBotbug(),playerdata.getBotloc(),playerdata.getBotearn(),playerdata.getBotexpn(),playerdata.getLoc(),playerdata.getBotrate(),playerdata.getHouses());
                moveToAnotherActivity();
            }
        });

        Button rollDiceButton = view.findViewById(R.id.rolDiceButton);
        rollDiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int diceResult = rollDice();
                sum += diceResult;
                if (sum>=40)
                {
                    sum = sum - 40;
                }
                player= view.findViewById(R.id.imageViewicon);
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) player.getLayoutParams();
                lp.topMargin=yarry[sum];
                lp.leftMargin=xarry[sum];
                player.setLayoutParams(lp);
                if ((sum==2) || (sum==17) || (sum==33))
                {
                    playerdata.setBuget(playerdata.getBuget()+30.0);
                    showToast("Bonus");

                }
                if ((sum==7) || (sum==36))
                {
                    showToast("?");
                }
                if(sum==12)
                {
                    playerdata.setEarning(playerdata.getEarning()+10.0);
                    showToast("You got promoted");
                }
                if(housearry[sum]==2)
                {
                    showToast("this land is owned by someone else you must pay");
                    playerdata.setBuget(playerdata.getBuget()-2);
                    playerdata.setBotbug(playerdata.getBotbug()+2);
                }
                playerdata.setBuget(playerdata.getBuget()+playerdata.getEarning());
                if(playerdata.getDebt()>0) {
                    playerdata.setBuget(playerdata.getBuget()-playerdata.getExpnens());
                    playerdata.setDebt(playerdata.getDebt() - playerdata.getExpnens());
                    playerdata.setDebt(playerdata.getDebt()*(1+playerdata.getIntrate()));
                    playerdata.setExpnens(playerdata.getDebt()/10);
                }
                else
                    showToast("you have no debt, great job");
                playerdata.setLoc((double) sum);

                int botmove = rollDice();
                botsum += botmove;
                if (botsum>=40)
                {
                    botsum = botsum - 40;
                }
                botplayer= view.findViewById(imageViewrobot);
                LinearLayout.LayoutParams botlp = (LinearLayout.LayoutParams) botplayer.getLayoutParams();
                botlp.topMargin=yarry[botsum]-10;
                botlp.leftMargin=xarry[botsum]-2;
                botplayer.setLayoutParams(botlp);
                if ((botsum==2) || (botsum==17) || (botsum==33))
                {
                    playerdata.setBotbug(playerdata.getBotbug()+30.0);
                    showToast("bot Bonus");

                }
                if ((botsum == 7) || (botsum == 36)) {
                    showToast("?");
                } else if (botsum == 12) {
                    playerdata.setBotearn(playerdata.getBotearn() + 10.0);
                    showToast("bot got promoted");
                } else if (housearry[botsum] == 1) {
                    showToast("this land is owned by you the bot must pay");
                    playerdata.setBuget(playerdata.getBuget() + 2);
                    playerdata.setBotbug(playerdata.getBotbug() - 2);

                }

                playerdata.setBotbug(playerdata.getBotbug() + playerdata.getBotearn());

                if (playerdata.getBotdebt() > 0) {
                    playerdata.setBotbug(playerdata.getBotbug() - playerdata.getBotexpn());
                    playerdata.setBotdebt(playerdata.getBotdebt() - playerdata.getBotexpn());
                    playerdata.setBotdebt(playerdata.getBotdebt() * (1 + playerdata.getBotrate()));
                    playerdata.setBotexpn(playerdata.getBotdebt()/10);
                } else {
                    showToast("the bot has no debt, do you have any ?");
                }
                playerdata.setBotloc((double) botsum);
                Random random = new Random();
                int cube = random.nextInt(11);
                if(cube%2==1)
                {
                    if(housearry[botsum]==0) {
                        String image = "imageView";
                        String imageViewIdString = image + botsum;
                        int imageViewId = getResources().getIdentifier(imageViewIdString, "id", requireActivity().getPackageName());
                        ImageView imageViewhouse = view.findViewById(imageViewId);
                        if(playerdata.getBotbug()>=200.0) {
                            showToast("the bot just bought a house");
                            playerdata.setBotbug(playerdata.getBotbug()-200.0);
                            housearry[botsum]=2;
                            imageViewhouse.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Double t =200.0-playerdata.getBotbug();
                            playerdata.setBotbug(0.0);
                            playerdata.setBotdebt(playerdata.getBotdebt()+t);
                            Double txp = t/10;
                            playerdata.setBotexpn(playerdata.getBotexpn()+txp);
                            showToast("the bot doseant have enough money, it has taken a loan of" + t + " which means each turn it will pay a extra " + txp + "every turn until all the money is returned");
                            housearry[botsum]=2;
                            imageViewhouse.setVisibility(View.VISIBLE);
                        }

                    }
                    if(housearry[botsum]==3)
                    {
                        showToast("there is no house in here, is the bot broken ?");
                    }
                    if(housearry[botsum]==2 || housearry[botsum]==1)
                    {
                        showToast("this house is already taken, the bot will try another");
                    }
                }
                if(((playerdata.getBuget()+playerdata.getEarning()*10)-8*playerdata.getDebt() * (1 + playerdata.getIntrate()))<=1)
                {
                    showToast("you cant pay your debt, YOU LOSE");
                    buget=10000.0;
                    double expns = 12.0;
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
                    changeData(buget,expns,earn,intrate,botexpn,botbug,botearn,botrate,debt,botdebt,loc,botloc,houses);
                    Intent intent = new Intent(getActivity(), EnterActivity.class);
                    startActivity(intent);
                }
                if(((playerdata.getBotbug()+playerdata.getBotearn()*10)-8*playerdata.getBotdebt() * (1 + playerdata.getBotrate()))<=1)
                {
                    showToast("the bot cant pay his debts, YOU WON!!!!");
                    buget=10000.0;
                    double expns = 12.0;
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
                    changeData(buget,expns,earn,intrate,botexpn,botbug,botearn,botrate,debt,botdebt,loc,botloc,houses);
                    Intent intent = new Intent(getActivity(), EnterActivity.class);
                    startActivity(intent);
                }


            }
        });

        // Inflate the layout for this fragment
        return view;
    }
    private int rollDice() {
        Random random = new Random();
        int ce = random.nextInt(7);
        return ce;
    }
    private void read_Data(String email, UserCallback callback) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        Query query = reference.orderByChild("email").equalTo(email).limitToFirst(1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("onDataChange! ");
                if (dataSnapshot.exists()) {
                    System.out.println("snapshot exists! ");
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);
                        callback.onCallback(user);
                        return;
                    }
                }
                callback.onCallback(null);
                //System.out.println("temp Success! ");
                //User user = dataSnapshot.getValue(User.class);
                //callback.onCallback(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("On cancelled! ");
                callback.onCallback(null); // Pass null in case of error
            }
            //@Override
           //public void onCancelled(@NonNull DatabaseError error) {
               //System.out.println("On cancelled! ");
               // callback.onCallback(null); // Pass null in case of error

        });


    }

    public interface UserCallback {
        void onCallback(User user);
    }
    private void showToast(String message)
    {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void updateChildren(String path, Double value) {
        // Update children of the email node
        gamedata.child(path).setValue(value);
    }
    private void updateString(String path, String house){
        gamedata.child(path).setValue(house);
    }
    private void moveToAnotherActivity() {
        Intent intent = new Intent(getActivity(), EnterActivity.class);
        startActivity(intent);

    }
    private void changeData(Double buget, Double expnens, Double earning, Double intrate,Double botexpn,Double botbug,Double botearn,Double botrate,Double debt,Double botdebt,Double loc,Double botloc,String houses) {
        String user= "Users";
        String key = user + indx;
        updateChildren(key +"/buget", buget);
        updateChildren(key +"/debt",debt);
        updateChildren(key +"/botdebt",botdebt);
        updateChildren(key +"/earning",earning);
        updateChildren(key +"/intrate",intrate);
        updateChildren(key +"/expnens",expnens);
        updateChildren(key +"/botbug",botbug);
        updateChildren(key +"/botloc",botloc);
        updateChildren(key +"/botearn",botearn);
        updateChildren(key +"/botexpn",botexpn);
        updateChildren(key +"/loc",loc);
        updateChildren(key +"/botrate",botrate);
        updateString(key +"/houses",houses);
        moveToAnotherActivity();
    }
    public static int[] stringToIntArray(String str) {
        int[] housearry = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            housearry[i] = (int) str.charAt(i);
        }
        return housearry;
    }
    public static String intArrayToString(int[] intArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intArray.length; i++) {
            sb.append((char) intArray[i]);
        }
        return sb.toString();
    }
    private String encodeEmail(String email) {
        return email.replace(".", "_dot_")
                .replace("@", "_at_");
    }


}
