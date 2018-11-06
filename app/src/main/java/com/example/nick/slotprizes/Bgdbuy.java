package com.example.nick.slotprizes;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.gigamole.infinitecycleviewpager.OnInfiniteCyclePageTransformListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Bgdbuy extends AppCompatActivity {

    List<Integer> imgs = new ArrayList<>();
    private FirebaseAuth myAuth;
    private DatabaseReference myUserData;
    private FirebaseUser myCurrentUser;
    private TextView cooins;
    private String abc;

    private ImageView img_buy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bgdbuy);
        myAuth = FirebaseAuth.getInstance();

        initData();

        cooins = findViewById(R.id.coinsbgd);
        Typeface square = Typeface.createFromAsset(getAssets(), "fonts/Square.ttf");
        cooins.setTypeface(square);

        img_buy = findViewById(R.id.buybgd);






        final HorizontalInfiniteCycleViewPager hz = findViewById(R.id.horizontal);
        BgdAdapter adapter1 = new BgdAdapter(imgs,getBaseContext());
        hz.setAdapter(adapter1);

        img_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (hz.getRealItem() == 1) {
                    Log.v(abc, "real1");


                            enterBase();


                            //Integer coinss = dataSnapshot.getValue(Integer.class);


                            myUserData.runTransaction(new Transaction.Handler() {

                                @Override
                                public Transaction.Result doTransaction(MutableData mutableData) {

                                    Integer coinss = mutableData.getValue(Integer.class);

                                    if (coinss == null) {
                                        //Toast.makeText(Bgdbuy.this,"Not Enough Coins", Toast.LENGTH_SHORT).show();
                                        return Transaction.success(mutableData);
                                    } else if (coinss >= 7500) {


                                        long value;

                                        value = mutableData.getValue(Long.class);

                                        value = value - 7500;
                                        mutableData.setValue(value);


                                        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                        String uid = current_user.getUid();

                                        myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("background");
                                        myUserData.setValue("purple");

                                        //give bgd firebase
                                        return Transaction.success(mutableData);
                                    }
                                    return Transaction.success(mutableData);
                                }
                                @Override
                                public void onComplete(DatabaseError databaseError, boolean b,
                                                       DataSnapshot dataSnapshot) {

                                    //Log.d(TAG, "postTransaction:onComplete:" + databaseError);

                                }
                            });


                        }



                else if(hz.getRealItem()==2)
                {
                    Log.v(abc, "real2");
                    Log.v(abc, "real1");


                    enterBase();


                    //Integer coinss = dataSnapshot.getValue(Integer.class);


                    myUserData.runTransaction(new Transaction.Handler() {

                        @Override
                        public Transaction.Result doTransaction(MutableData mutableData) {

                            Integer coinss = mutableData.getValue(Integer.class);

                            if (coinss == null) {
                                Toast.makeText(Bgdbuy.this,"Not Enough Coins", Toast.LENGTH_SHORT).show();
                                return Transaction.success(mutableData);
                            } else if (coinss >= 7500) {


                                long value;

                                value = mutableData.getValue(Long.class);

                                value = value - 7500;
                                mutableData.setValue(value);

                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = current_user.getUid();

                                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("background");
                                myUserData.setValue("bluegradient");

                                //give bgd firebase
                                return Transaction.success(mutableData);
                            }
                            return Transaction.success(mutableData);
                        }
                        @Override
                        public void onComplete(DatabaseError databaseError, boolean b,
                                               DataSnapshot dataSnapshot) {

                            //Log.d(TAG, "postTransaction:onComplete:" + databaseError);

                        }
                    });

                }
                else if(hz.getRealItem()==3)
                {
                    Log.v(abc, "real3");
                    Log.v(abc, "real1");


                    enterBase();


                    //Integer coinss = dataSnapshot.getValue(Integer.class);


                    myUserData.runTransaction(new Transaction.Handler() {

                        @Override
                        public Transaction.Result doTransaction(MutableData mutableData) {

                            Integer coinss = mutableData.getValue(Integer.class);

                            if (coinss == null) {
                                return Transaction.success(mutableData);
                            } else if (coinss >= 7500) {


                                long value;

                                value = mutableData.getValue(Long.class);

                                value = value - 7500;
                                mutableData.setValue(value);
                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = current_user.getUid();

                                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("background");
                                myUserData.setValue("yellowred");

                                //give bgd firebase
                                return Transaction.success(mutableData);
                            }
                            return Transaction.success(mutableData);
                        }
                        @Override
                        public void onComplete(DatabaseError databaseError, boolean b,
                                               DataSnapshot dataSnapshot) {

                            //Log.d(TAG, "postTransaction:onComplete:" + databaseError);

                        }
                    });

                }

                else if(hz.getRealItem()==4)
                {
                    Log.v(abc, "real4");
                    Log.v(abc, "real1");


                    enterBase();


                    //Integer coinss = dataSnapshot.getValue(Integer.class);


                    myUserData.runTransaction(new Transaction.Handler() {

                        @Override
                        public Transaction.Result doTransaction(MutableData mutableData) {

                            Integer coinss = mutableData.getValue(Integer.class);

                            if (coinss == null) {
                                return Transaction.success(mutableData);
                            } else if (coinss >= 7500) {


                                long value;

                                value = mutableData.getValue(Long.class);

                                value = value - 7500;
                                mutableData.setValue(value);
                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = current_user.getUid();

                                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("background");
                                myUserData.setValue("brazil");

                                //give bgd firebase
                                return Transaction.success(mutableData);
                            }
                            return Transaction.success(mutableData);
                        }
                        @Override
                        public void onComplete(DatabaseError databaseError, boolean b,
                                               DataSnapshot dataSnapshot) {

                            //Log.d(TAG, "postTransaction:onComplete:" + databaseError);

                        }
                    });

                }
                else if(hz.getRealItem()==0)
                {
                    Log.v(abc, "real5");
                    Log.v(abc, "real1");


                    enterBase();


                    //Integer coinss = dataSnapshot.getValue(Integer.class);


                    myUserData.runTransaction(new Transaction.Handler() {

                        @Override
                        public Transaction.Result doTransaction(MutableData mutableData) {

                            Integer coinss = mutableData.getValue(Integer.class);

                            if (coinss == null) {
                                return Transaction.success(mutableData);
                            } else if (coinss >= 7500) {


                                long value;

                                value = mutableData.getValue(Long.class);

                                value = value - 7500;
                                mutableData.setValue(value);
                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = current_user.getUid();

                                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("background");
                                myUserData.setValue("cyan");

                                //give bgd firebase
                                return Transaction.success(mutableData);
                            }
                            return Transaction.success(mutableData);
                        }
                        @Override
                        public void onComplete(DatabaseError databaseError, boolean b,
                                               DataSnapshot dataSnapshot) {

                            //Log.d(TAG, "postTransaction:onComplete:" + databaseError);

                        }
                    });

                }
                else {}



            }
        });











        //hz.getRealItem();
        Log.v(abc, "realitem" + hz.getRealItem());
;






        myCurrentUser = myAuth.getCurrentUser();

        String current_uid = myCurrentUser.getUid();

        myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
        myUserData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Integer coinss = dataSnapshot.child("coins").getValue(Integer.class);
                cooins.setText(coinss.toString());

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
















    }

    private void initData() {

        imgs.add(R.drawable.bgd1);
        imgs.add(R.drawable.bgd2);
        imgs.add(R.drawable.bgd3);
        imgs.add(R.drawable.bgd4);
        imgs.add(R.drawable.bgd5);





    }

    private void enterBase(){
        myCurrentUser = myAuth.getCurrentUser();

        String current_uid = myCurrentUser.getUid();

        //FirebaseUser myCurrentUser = FirebaseAuth.getInstance().getCurrentUser();


        myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid).child("coins");

            }
        }


