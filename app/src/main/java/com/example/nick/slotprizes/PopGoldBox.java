package com.example.nick.slotprizes;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Random;

import static java.lang.System.*;

public class PopGoldBox extends AppCompatActivity {

    private TextView my;
    private Button btn;

    private FirebaseAuth myAuth;
    private DatabaseReference myUserData;
    private FirebaseUser myCurrentUser;
    private TextView mycoinss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_gold_box);

        my = findViewById(R.id.gold_box_view);
        btn = findViewById(R.id.buttonnn);
        mycoinss = findViewById(R.id.mycoins);
        myAuth = FirebaseAuth.getInstance();

       // btn.setVisibility(View.INVISIBLE);,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,



        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {

                                       myCurrentUser = myAuth.getCurrentUser();

                                       String current_uid = myCurrentUser.getUid();

                                       //FirebaseUser myCurrentUser = FirebaseAuth.getInstance().getCurrentUser();


                                       myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid).child("coins");


                                       //Integer coinss = dataSnapshot.getValue(Integer.class);


                                       myUserData.runTransaction(new Transaction.Handler() {

                                           @Override
                                           public Transaction.Result doTransaction(MutableData mutableData) {

                                               Integer coinss = mutableData.getValue(Integer.class);

                                               if (coinss == null) {
                                                   return Transaction.success(mutableData);
                                               } else if (coinss >= 25000) {


                                                   long value;

                                                   value = mutableData.getValue(Long.class);

                                                   value = value - 25000;
                                                   mutableData.setValue(value);

                                                   // Random 80percent of 2 and 20 of 4 int randMy = Math.random() < 0.8 ? 2 : 4;



                                                   Random r = new Random();
                                                   int game = r.nextInt(100);

                                                   if (game < 40){  //40%
                                                       Intent nnnnn = new Intent(PopGoldBox.this,GoldBoxZero.class);
                                                       startActivity(nnnnn);

                                                       FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                                       String uid = current_user.getUid();

                                                       myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("plays");


                                                       myUserData.runTransaction(new Transaction.Handler() {
                                                           @Override
                                                           public Transaction.Result doTransaction(MutableData mutableData) {
                                                               long value = 0;

                                                               value = mutableData.getValue(Long.class);

                                                               value = value + 25;
                                                               mutableData.setValue(value);
                                                               ;
                                                               return Transaction.success(mutableData);
                                                           }


                                                           @Override
                                                           public void onComplete(DatabaseError databaseError, boolean b,
                                                                                  DataSnapshot dataSnapshot) {

                                                           }
                                                       });
                                                   }
                                                   else if (game < 70){ // 30%
                                                       // do something
                                                       Intent nnn = new Intent(PopGoldBox.this,GoldBoxFirst.class);
                                                       startActivity(nnn);

                                                       FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                                       String uid = current_user.getUid();

                                                       myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("keys");


                                                       myUserData.runTransaction(new Transaction.Handler() {
                                                           @Override
                                                           public Transaction.Result doTransaction(MutableData mutableData) {
                                                               long value = 0;

                                                               value = mutableData.getValue(Long.class);

                                                               value = value + 1;
                                                               mutableData.setValue(value);
                                                               ;
                                                               return Transaction.success(mutableData);
                                                           }


                                                           @Override
                                                           public void onComplete(DatabaseError databaseError, boolean b,
                                                                                  DataSnapshot dataSnapshot) {

                                                           }
                                                       });
                                                   }
                                                   else if (game < 90){ // 20%
                                                       // do something
                                                       Intent n = new Intent(PopGoldBox.this,GoldBoxSecond.class);
                                                       startActivity(n);

                                                       FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                                       String uid = current_user.getUid();

                                                       myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("keys");


                                                       myUserData.runTransaction(new Transaction.Handler() {
                                                           @Override
                                                           public Transaction.Result doTransaction(MutableData mutableData) {
                                                               long value = 0;

                                                               value = mutableData.getValue(Long.class);

                                                               value = value + 2;
                                                               mutableData.setValue(value);
                                                               ;
                                                               return Transaction.success(mutableData);
                                                           }


                                                           @Override
                                                           public void onComplete(DatabaseError databaseError, boolean b,
                                                                                  DataSnapshot dataSnapshot) {

                                                           }
                                                       });
                                                   }
                                                   else if (game < 100){

                                                       Intent nn = new Intent(PopGoldBox.this,GoldBoxThird.class);
                                                       startActivity(nn);// 10%

                                                       // adcionar mauth a essa classe

                                                       FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                                       String uid = current_user.getUid();

                                                       myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("diamonds");


                                                       myUserData.runTransaction(new Transaction.Handler() {
                                                           @Override
                                                           public Transaction.Result doTransaction(MutableData mutableData) {
                                                               long value = 0;

                                                               value = mutableData.getValue(Long.class);

                                                               value = value + 15;
                                                               mutableData.setValue(value);
                                                               ;
                                                               return Transaction.success(mutableData);
                                                           }


                                                           @Override
                                                           public void onComplete(DatabaseError databaseError, boolean b,
                                                                                  DataSnapshot dataSnapshot) {

                                                           }
                                                       });
                                                       // do something
                                                   }
                                                   else {

                                                       Intent nn = new Intent(PopGoldBox.this,GoldBoxThird.class);
                                                       startActivity(nn);
                                                   }









                                                   return Transaction.success(mutableData);


                                               } else if(coinss < 25000){
                                                   my.setText("Not enough money");
                                                   Handler h = new Handler();
                                                   h.postDelayed(new Runnable() {
                                                       @Override
                                                       public void run() {
                                                           my.setText("Gold Mystery Box");



                                                       }
                                                   }, 2000);

                                                   return Transaction.success(mutableData);





                                               }else{

                                                   return Transaction.success(mutableData);
                                               }

                                           }


                                           @Override
                                           public void onComplete(DatabaseError databaseError, boolean b,
                                                                  DataSnapshot dataSnapshot) {

                                           }
                                       });


                                   }


                                   //return Transaction.success(mutableData);


                               });











            //mycoinss.setText(coinss.toString());








       // });








        Typeface bebas = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue.otf");
        my.setTypeface(bebas);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.4));







    }

    @Override
    public void onBackPressed() {

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        FirebaseUser currentUser = myAuth.getCurrentUser();



        if (currentUser == null) {

            Toast.makeText(PopGoldBox.this, "Something went wrong",
                    Toast.LENGTH_LONG).show();
        }else {

            myCurrentUser = myAuth.getCurrentUser();
        }




    }
}
