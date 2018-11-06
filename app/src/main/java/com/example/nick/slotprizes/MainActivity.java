package com.example.nick.slotprizes;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.nick.slotprizes.BroadcastReceiver.AlarmReceiver;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private TextView msg, welcome, coins, welcome2, diamonds, or, thex, keyss, level;
    private ImageView img1, img2, img3, img4, img5, boxes, gold, premium, green, achiev, buyy, secondbuy;
    private Wheel wheel1, wheel2, wheel3, wheel4, wheel5;
    private Button btn2, logout_btn;
    private ImageView btn, shop_btn;
    private boolean isStarted = false;
    private Random rand;

    private ImageView prof;

    private TextView countdown;

    //added by nick

    private ImageView greena;



    private Integer playssm;

    private Boolean checkStatus;
    private Boolean bought = false;

    private TextView plays;
    private Boolean b = false;

    private LottieAnimationView lottieAnimationView;

    private FirebaseAuth myAuth;
    private DatabaseReference myUserData, myUserData2, myUserData3;
    private FirebaseUser myCurrentUser;

    private int i = 0;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //new BroadcastSampleActivity();
        /**
         if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
         else {
         Toast.makeText(MainActivity.this,"Welcome", Toast.LENGTH_SHORT).show();
         setContentView(R.layout.activity_main);
         }
         */
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img5 = findViewById(R.id.img);
        img4 = findViewById(R.id.img4);
        btn = findViewById(R.id.btn22);
        logout_btn = findViewById(R.id.logout);
        msg = (TextView) findViewById(R.id.msg);
        welcome = findViewById(R.id.welcome2);
        coins = findViewById(R.id.mycoins);
        achiev = findViewById(R.id.ach1);
        //lottieAnimationView = (LottieAnimationView) findViewById(R.id.animshop);
        shop_btn = findViewById(R.id.shshshs);
        plays = findViewById(R.id.plays);

        thex = findViewById(R.id.thex);
        keyss = findViewById(R.id.keys_value);



        //countdown = findViewById(R.id.count);

        welcome2 = findViewById(R.id.welcome3);
        diamonds = findViewById(R.id.mycoins2);

        buyy = findViewById(R.id.btnbuy);
        secondbuy = findViewById(R.id.secondb);
        or = findViewById(R.id.or);

        prof = findViewById(R.id.profile_icon);

        greena = findViewById(R.id.green);


        gold = findViewById(R.id.gold);
        premium = findViewById(R.id.premium);
        green = findViewById(R.id.green);
        myUserData = FirebaseDatabase.getInstance().getReference().child("Users");
        myUserData.keepSynced(true);
        myUserData2 = FirebaseDatabase.getInstance().getReference().child("Users");
        myUserData2.keepSynced(true);

        registerAlarm();
        btn.setVisibility(View.VISIBLE);

        //CountDown Timer

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        Query top10query = ref.orderByChild("ranking").limitToLast(10);
        top10query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    //Score score = postSnapshot.getValue(Score.class);
                    HashMap<String, Integer> map = (HashMap<String, Integer>) postSnapshot.getValue();
                    //Integer ranks = postSnapshot.getValue(Integer.class);
                    Log.d("test"," values is " + map);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });








        //Plays

        Typeface square = Typeface.createFromAsset(getAssets(), "fonts/Square.ttf");
        plays.setTypeface(square);

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(n);
            }
        });



        //Button

        secondbuy.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                myCurrentUser = myAuth.getCurrentUser();

                String current_uid = myCurrentUser.getUid();

                //FirebaseUser myCurrentUser = FirebaseAuth.getInstance().getCurrentUser();


                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid).child("diamonds");


                //Integer coinss = dataSnapshot.getValue(Integer.class);


                myUserData.runTransaction(new Transaction.Handler() {

                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {

                        Integer diamonds = mutableData.getValue(Integer.class);

                        if (diamonds == null) {
                            return Transaction.success(mutableData);
                        } else if (diamonds >= 15) {


                            long value;

                            value = mutableData.getValue(Long.class);

                            value = value - 15;
                            mutableData.setValue(value);

                            FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = current_user.getUid();


                            myUserData2 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("plays");


                            myUserData2.runTransaction(new Transaction.Handler() {
                                @Override
                                public Transaction.Result doTransaction(MutableData mutableData2) {
                                    long value = 0;

                                    bought = true;

                                    value = mutableData2.getValue(Long.class);

                                    value = value + 50;
                                    mutableData2.setValue(value);

                                    return Transaction.success(mutableData2);
                                }


                                @Override
                                public void onComplete(DatabaseError databaseError, boolean b,
                                                       DataSnapshot dataSnapshot) {

                                }
                            });










                            return Transaction.success(mutableData);


                        }
                        else{

                            return Transaction.success(mutableData);

                        }

                        //return Transaction.success(mutableData);





            }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

                    }
                });
            }
        });

        gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gold = new Intent(MainActivity.this, PopGoldBox.class);
                startActivity(gold);
            }
        });






        Typeface bebas = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue.otf");
        welcome.setTypeface(bebas);
        welcome2.setTypeface(bebas);
        thex.setTypeface(bebas);
        keyss.setTypeface(bebas);



        myAuth = FirebaseAuth.getInstance();

        greena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintLayout  linearLayout = (ConstraintLayout) findViewById(R.id.activity_main);
                linearLayout.setBackgroundResource(R.drawable.bglegendary);
                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("background");
                myUserData.setValue("gold");



                //userMap.put("status", "I Love FastChat");


            }
        });

        achiev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                //String uid = current_user.getUid();

                //myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("timestamp");



                //myUserData.setValue(ServerValue.TIMESTAMP);

                Intent n = new Intent(MainActivity.this, BackgroundShop.class);
                startActivity(n);

            }
        });


        shop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent n = new Intent(MainActivity.this, Shop.class);
                        startActivity(n);
                    }
                }, 10);

            }
        });


        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                sendToStart();
            }
        });

        //try {


        if (!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {


            FirebaseUser currentUser = myAuth.getCurrentUser();


            if (currentUser == null) {

                sendToStart();
            } else {

                myCurrentUser = myAuth.getCurrentUser();


                //String current_uid = myCurrentUser.getUid();





            //myCurrentUser = myAuth.getCurrentUser();




            String current_uid = myCurrentUser.getUid();

            myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
            myUserData.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    Integer playsm = dataSnapshot.child("plays").getValue(Integer.class);


                    plays.setText(playsm.toString());


                    if (playsm <= 0) {


                        btn.setVisibility(View.GONE);
                        buyy.setVisibility(View.VISIBLE);
                        secondbuy.setVisibility(View.VISIBLE);
                        or.setVisibility(View.VISIBLE);
                    } else if(playsm > 0 && bought){//btn.setVisibility(View.VISIBLE);}

                        buyy.setVisibility(View.GONE);
                        secondbuy.setVisibility(View.GONE);
                        or.setVisibility(View.GONE);
                        btn.setVisibility(View.VISIBLE);
                        bought = false;


                    }else{}


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });



            btn.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {


                    //isStarted=false;


                    myCurrentUser = myAuth.getCurrentUser();

                    String current_uid = myCurrentUser.getUid();

                    //FirebaseUser myCurrentUser = FirebaseAuth.getInstance().getCurrentUser();


                    myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid).child("plays");


                    //Integer coinss = dataSnapshot.getValue(Integer.class);


                    myUserData.runTransaction(new Transaction.Handler() {

                        @Override
                        public Transaction.Result doTransaction(MutableData mutableData) {

                            Integer playss = mutableData.getValue(Integer.class);


                            long value;

                            value = mutableData.getValue(Long.class);

                            value = value - 1;
                            mutableData.setValue(value);

                            //spin();
                            //isStarted = true;


                            return Transaction.success(mutableData);


                        }


                        @Override
                        public void onComplete(DatabaseError databaseError, boolean b,
                                               DataSnapshot dataSnapshot) {

                            //Log.d(TAG, "postTransaction:onComplete:" + databaseError);

                        }

                    });

                    rand = new Random();
                    btn.setVisibility(View.GONE);


                    //PLAYS


                    int rr = rand.nextInt(3000) + 1000; // Time it takes to spin

                    //Create the Wheels

                    wheel1 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img1.setImageResource(img);
                                    i++;
                                }
                            });
                        }
                    }, 250, randomLong(0, 1000));

                    wheel1.start();

                    wheel2 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img2.setImageResource(img);
                                    i++;
                                }
                            });
                        }
                    }, 200, randomLong(0, 1000));

                    wheel2.start();

                    wheel3 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img3.setImageResource(img);
                                    i++;
                                }
                            });
                        }
                    }, 150, randomLong(0, 1000));

                    wheel3.start();
                    wheel4 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img4.setImageResource(img);
                                    i++;
                                }
                            });
                        }
                    }, 100, randomLong(0, 1000));

                    wheel4.start();
                    wheel5 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img5.setImageResource(img);
                                    i++;
                                }
                            });
                        }
                    }, 100, randomLong(0, 1000));

                    wheel5.start();

                    //btn.setText("Stop");


                    msg.setText("");


                    Handler hh = new Handler();


                    hh.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            wheel1.stopWheel();
                            wheel2.stopWheel();
                            wheel3.stopWheel();
                            wheel4.stopWheel();
                            wheel5.stopWheel();


                            if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex ==
                                    wheel3.currentIndex && wheel3.currentIndex == wheel4.currentIndex && wheel4.currentIndex == wheel5.currentIndex) {
                                //msg.setText("You WON the Max Price = 50000 coins and 5 diamonds");
                                final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.slot);

                                int maxV = 50;
                                float log1 = (float) (Math.log(maxV - 30) / Math.log(maxV));
                                mp.setVolume(1 - log1, 1 - log1);
                                mp.start();

                                Handler h = new Handler();
                                h.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        final LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.anim3);
                                        lottieAnimationView.setImageAssetsFolder("images/");

                                        lottieAnimationView.setVisibility(View.VISIBLE);

                                        lottieAnimationView.setAnimation("5row.json");
                                        lottieAnimationView.loop(false);
                                        lottieAnimationView.playAnimation();

                                        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                lottieAnimationView.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                }, 500);

                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = current_user.getUid();

                                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("coins");


                                myUserData.runTransaction(new Transaction.Handler() {
                                    @Override
                                    public Transaction.Result doTransaction(MutableData mutableData) {
                                        long value = 0;

                                        value = mutableData.getValue(Long.class);

                                        value = value + 50000;
                                        mutableData.setValue(value);

                                        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                        String uid = current_user.getUid();

                                        myUserData2 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("diamonds");


                                        myUserData2.runTransaction(new Transaction.Handler() {
                                            @Override
                                            public Transaction.Result doTransaction(MutableData mutableData) {
                                                long value = 0;

                                                value = mutableData.getValue(Long.class);

                                                value = value + 100;
                                                mutableData.setValue(value);

                                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                                String uid = current_user.getUid();

                                                myUserData2 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("ranking");


                                                myUserData2.runTransaction(new Transaction.Handler() {
                                                    @Override
                                                    public Transaction.Result doTransaction(MutableData mutableData) {
                                                        long value = 0;

                                                        value = mutableData.getValue(Long.class);

                                                        //500 trophies!

                                                        value = value + 500;
                                                        mutableData.setValue(value);
                                                        ;
                                                        return Transaction.success(mutableData);
                                                    }


                                                    @Override
                                                    public void onComplete(DatabaseError databaseError, boolean b,
                                                                           DataSnapshot dataSnapshot) {

                                                    }
                                                });

                                                ;
                                                return Transaction.success(mutableData);
                                            }


                                            @Override
                                            public void onComplete(DatabaseError databaseError, boolean b,
                                                                   DataSnapshot dataSnapshot) {

                                            }
                                        });

                                        return Transaction.success(mutableData);
                                    }


                                    @Override
                                    public void onComplete(DatabaseError databaseError, boolean b,
                                                           DataSnapshot dataSnapshot) {

                                    }
                                });




                                myUserData.runTransaction(new Transaction.Handler() {
                                    @Override
                                    public Transaction.Result doTransaction(MutableData mutableData) {
                                        long value = 0;

                                        value = mutableData.getValue(Long.class);

                                        value = value + 10000;
                                        mutableData.setValue(value);
                                        ;
                                        return Transaction.success(mutableData);
                                    }


                                    @Override
                                    public void onComplete(DatabaseError databaseError, boolean b,
                                                           DataSnapshot dataSnapshot) {

                                    }
                                });
                            } else if ((wheel1.currentIndex == wheel2.currentIndex) && (wheel2.currentIndex == wheel3.currentIndex) && (wheel3.currentIndex == wheel4.currentIndex)) {
                                //msg.setText("4 in a Row = 5000 coins");
                                final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.slot);

                                int maxV = 50;
                                float log1 = (float) (Math.log(maxV - 30) / Math.log(maxV));
                                mp.setVolume(1 - log1, 1 - log1);
                                mp.start();

                                Handler h = new Handler();
                                h.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        final LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.anim3);
                                        lottieAnimationView.setImageAssetsFolder("images/");

                                        lottieAnimationView.setVisibility(View.VISIBLE);

                                        lottieAnimationView.setAnimation("44row.json");
                                        lottieAnimationView.loop(false);
                                        lottieAnimationView.playAnimation();

                                        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                lottieAnimationView.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                }, 500);


                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = current_user.getUid();

                                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("coins");


                                myUserData.runTransaction(new Transaction.Handler() {
                                    @Override
                                    public Transaction.Result doTransaction(MutableData mutableData) {
                                        long value = 0;

                                        value = mutableData.getValue(Long.class);

                                        value = value + 5000;
                                        mutableData.setValue(value);
                                        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                        String uid = current_user.getUid();

                                        myUserData2 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("diamonds");


                                        myUserData2.runTransaction(new Transaction.Handler() {
                                            @Override
                                            public Transaction.Result doTransaction(MutableData mutableData) {
                                                long value = 0;

                                                value = mutableData.getValue(Long.class);

                                                value = value + 5;
                                                mutableData.setValue(value);
                                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                                String uid = current_user.getUid();

                                                myUserData2 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("ranking");


                                                myUserData2.runTransaction(new Transaction.Handler() {
                                                    @Override
                                                    public Transaction.Result doTransaction(MutableData mutableData) {
                                                        long value = 0;

                                                        value = mutableData.getValue(Long.class);

                                                        //300 trophies!

                                                        value = value + 300;
                                                        mutableData.setValue(value);
                                                        ;
                                                        return Transaction.success(mutableData);
                                                    }


                                                    @Override
                                                    public void onComplete(DatabaseError databaseError, boolean b,
                                                                           DataSnapshot dataSnapshot) {

                                                    }
                                                });
                                                ;
                                                return Transaction.success(mutableData);
                                            }


                                            @Override
                                            public void onComplete(DatabaseError databaseError, boolean b,
                                                                   DataSnapshot dataSnapshot) {

                                            }
                                        });
                                        return Transaction.success(mutableData);
                                    }


                                    @Override
                                    public void onComplete(DatabaseError databaseError, boolean b,
                                                           DataSnapshot dataSnapshot) {

                                    }
                                });


                            } else if (wheel2.currentIndex == wheel3.currentIndex && wheel3.currentIndex == wheel4.currentIndex && wheel4.currentIndex == wheel5.currentIndex) {
                                //msg.setText("4 in a Row = 5000 coins");
                                final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.slot);

                                int maxV = 50;
                                float log1 = (float) (Math.log(maxV - 30) / Math.log(maxV));
                                mp.setVolume(1 - log1, 1 - log1);
                                mp.start();

                                Handler h = new Handler();
                                h.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        final LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.anim3);
                                        lottieAnimationView.setImageAssetsFolder("images/");

                                        lottieAnimationView.setVisibility(View.VISIBLE);

                                        lottieAnimationView.setAnimation("44row.json");
                                        lottieAnimationView.loop(false);
                                        lottieAnimationView.playAnimation();

                                        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                lottieAnimationView.setVisibility(View.GONE);
                                            }
                                        });

                                    }
                                }, 500);


                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = current_user.getUid();

                                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("coins");


                                myUserData.runTransaction(new Transaction.Handler() {
                                    @Override
                                    public Transaction.Result doTransaction(MutableData mutableData) {
                                        long value = 0;

                                        value = mutableData.getValue(Long.class);

                                        value = value + 5000;
                                        mutableData.setValue(value);

                                        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                        String uid = current_user.getUid();

                                        myUserData2 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("diamonds");


                                        myUserData2.runTransaction(new Transaction.Handler() {
                                            @Override
                                            public Transaction.Result doTransaction(MutableData mutableData) {
                                                long value = 0;

                                                value = mutableData.getValue(Long.class);

                                                value = value + 5;
                                                mutableData.setValue(value);

                                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                                String uid = current_user.getUid();

                                                myUserData2 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("ranking");


                                                myUserData2.runTransaction(new Transaction.Handler() {
                                                    @Override
                                                    public Transaction.Result doTransaction(MutableData mutableData) {
                                                        long value = 0;

                                                        value = mutableData.getValue(Long.class);

                                                        //300 trophies!

                                                        value = value + 300;
                                                        mutableData.setValue(value);
                                                        ;
                                                        return Transaction.success(mutableData);
                                                    }


                                                    @Override
                                                    public void onComplete(DatabaseError databaseError, boolean b,
                                                                           DataSnapshot dataSnapshot) {

                                                    }
                                                });
                                                ;
                                                return Transaction.success(mutableData);
                                            }


                                            @Override
                                            public void onComplete(DatabaseError databaseError, boolean b,
                                                                   DataSnapshot dataSnapshot) {

                                            }
                                        });
                                        ;
                                        return Transaction.success(mutableData);
                                    }


                                    @Override
                                    public void onComplete(DatabaseError databaseError, boolean b,
                                                           DataSnapshot dataSnapshot) {

                                    }
                                });


                            } else if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex ==
                                    wheel3.currentIndex) {
                                //msg.setText("3 in a Row = 3000 coins");


                                final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.slot);

                                int maxV = 50;
                                float log1 = (float) (Math.log(maxV - 30) / Math.log(maxV));
                                mp.setVolume(1 - log1, 1 - log1);
                                mp.start();


                                Handler h = new Handler();
                                h.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        final LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.anim3);
                                        lottieAnimationView.setImageAssetsFolder("images/");

                                        lottieAnimationView.setVisibility(View.VISIBLE);

                                        lottieAnimationView.setAnimation("win3.json");
                                        lottieAnimationView.loop(false);
                                        lottieAnimationView.playAnimation();

                                        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                lottieAnimationView.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                }, 500);


                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = current_user.getUid();

                                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("coins");


                                myUserData.runTransaction(new Transaction.Handler() {
                                    @Override
                                    public Transaction.Result doTransaction(MutableData mutableData) {
                                        long value = 0;

                                        value = mutableData.getValue(Long.class);

                                        value = value + 3000;
                                        mutableData.setValue(value);

                                        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                        String uid = current_user.getUid();

                                        myUserData2 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("ranking");


                                        myUserData2.runTransaction(new Transaction.Handler() {
                                            @Override
                                            public Transaction.Result doTransaction(MutableData mutableData) {
                                                long value = 0;

                                                value = mutableData.getValue(Long.class);

                                                //500 trophies!

                                                value = value + 100;
                                                mutableData.setValue(value);
                                                ;
                                                return Transaction.success(mutableData);
                                            }


                                            @Override
                                            public void onComplete(DatabaseError databaseError, boolean b,
                                                                   DataSnapshot dataSnapshot) {

                                            }
                                        });
                                        ;
                                        return Transaction.success(mutableData);
                                    }


                                    @Override
                                    public void onComplete(DatabaseError databaseError, boolean b,
                                                           DataSnapshot dataSnapshot) {

                                    }
                                });


                            } else if (wheel3.currentIndex == wheel4.currentIndex && wheel4.currentIndex ==
                                    wheel5.currentIndex) {
                                // msg.setText("3 in a Row = 3000 coins");
                                final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.slot);

                                int maxV = 50;
                                float log1 = (float) (Math.log(maxV - 30) / Math.log(maxV));
                                mp.setVolume(1 - log1, 1 - log1);
                                mp.start();

                                Handler h = new Handler();
                                h.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        final LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.anim3);
                                        lottieAnimationView.setImageAssetsFolder("images/");

                                        lottieAnimationView.setVisibility(View.VISIBLE);

                                        lottieAnimationView.setAnimation("win3.json");
                                        lottieAnimationView.loop(false);
                                        lottieAnimationView.playAnimation();

                                        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                lottieAnimationView.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                }, 500);


                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = current_user.getUid();

                                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("coins");


                                myUserData.runTransaction(new Transaction.Handler() {
                                    @Override
                                    public Transaction.Result doTransaction(MutableData mutableData) {
                                        long value = 0;

                                        value = mutableData.getValue(Long.class);

                                        value = value + 3000;
                                        mutableData.setValue(value);

                                        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                        String uid = current_user.getUid();

                                        myUserData2 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("ranking");


                                        myUserData2.runTransaction(new Transaction.Handler() {
                                            @Override
                                            public Transaction.Result doTransaction(MutableData mutableData) {
                                                long value = 0;

                                                value = mutableData.getValue(Long.class);

                                                //500 trophies!

                                                value = value + 100;
                                                mutableData.setValue(value);
                                                ;
                                                return Transaction.success(mutableData);
                                            }


                                            @Override
                                            public void onComplete(DatabaseError databaseError, boolean b,
                                                                   DataSnapshot dataSnapshot) {

                                            }
                                        });
                                        ;
                                        return Transaction.success(mutableData);
                                    }


                                    @Override
                                    public void onComplete(DatabaseError databaseError, boolean b,
                                                           DataSnapshot dataSnapshot) {

                                    }
                                });


                            } else if (wheel2.currentIndex == wheel3.currentIndex && wheel3.currentIndex == wheel4.currentIndex) {
                                //msg.setText("3 in a Row = 3000 coins");
                                final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.slot);

                                int maxV = 50;
                                float log1 = (float) (Math.log(maxV - 30) / Math.log(maxV));
                                mp.setVolume(1 - log1, 1 - log1);
                                mp.start();

                                Handler h = new Handler();
                                h.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        final LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.anim3);
                                        lottieAnimationView.setImageAssetsFolder("images/");

                                        lottieAnimationView.setVisibility(View.VISIBLE);

                                        lottieAnimationView.setAnimation("win3.json");
                                        lottieAnimationView.loop(false);
                                        lottieAnimationView.playAnimation();

                                        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                lottieAnimationView.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                }, 500);


                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = current_user.getUid();

                                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("coins");


                                myUserData.runTransaction(new Transaction.Handler() {
                                    @Override
                                    public Transaction.Result doTransaction(MutableData mutableData) {
                                        long value = 0;

                                        value = mutableData.getValue(Long.class);

                                        value = value + 3000;
                                        mutableData.setValue(value);

                                        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                        String uid = current_user.getUid();

                                        myUserData2 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("ranking");


                                        myUserData2.runTransaction(new Transaction.Handler() {
                                            @Override
                                            public Transaction.Result doTransaction(MutableData mutableData) {
                                                long value = 0;

                                                value = mutableData.getValue(Long.class);

                                                //500 trophies!

                                                value = value + 100;
                                                mutableData.setValue(value);
                                                ;
                                                return Transaction.success(mutableData);
                                            }


                                            @Override
                                            public void onComplete(DatabaseError databaseError, boolean b,
                                                                   DataSnapshot dataSnapshot) {

                                            }
                                        });
                                        ;
                                        return Transaction.success(mutableData);
                                    }


                                    @Override
                                    public void onComplete(DatabaseError databaseError, boolean b,
                                                           DataSnapshot dataSnapshot) {

                                    }
                                });


                            }
                            //no clue
                            else if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == wheel4.currentIndex && wheel4.currentIndex == wheel5.currentIndex) {
                                //msg.setText("4 of the Same Kind = 3000 coins");

                                final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.slot);

                                int maxV = 50;
                                float log1 = (float) (Math.log(maxV - 30) / Math.log(maxV));
                                mp.setVolume(1 - log1, 1 - log1);
                                mp.start();

                                Handler h = new Handler();
                                h.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        final LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.anim3);
                                        lottieAnimationView.setImageAssetsFolder("images/");

                                        //trocar anim dps ajustar

                                        lottieAnimationView.setVisibility(View.VISIBLE);

                                        lottieAnimationView.setAnimation("44row.json");
                                        lottieAnimationView.loop(false);
                                        lottieAnimationView.playAnimation();

                                        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                lottieAnimationView.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                }, 500);


                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = current_user.getUid();

                                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("coins");


                                myUserData.runTransaction(new Transaction.Handler() {
                                    @Override
                                    public Transaction.Result doTransaction(MutableData mutableData) {
                                        long value = 0;

                                        value = mutableData.getValue(Long.class);

                                        value = value + 3000;
                                        mutableData.setValue(value);
                                        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                        String uid = current_user.getUid();

                                        myUserData2 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("diamonds");


                                        myUserData2.runTransaction(new Transaction.Handler() {
                                            @Override
                                            public Transaction.Result doTransaction(MutableData mutableData) {
                                                long value = 0;

                                                value = mutableData.getValue(Long.class);

                                                value = value + 5;
                                                mutableData.setValue(value);
                                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                                String uid = current_user.getUid();

                                                myUserData2 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("ranking");


                                                myUserData2.runTransaction(new Transaction.Handler() {
                                                    @Override
                                                    public Transaction.Result doTransaction(MutableData mutableData) {
                                                        long value = 0;

                                                        value = mutableData.getValue(Long.class);

                                                        //500 trophies!

                                                        value = value + 300;
                                                        mutableData.setValue(value);
                                                        ;
                                                        return Transaction.success(mutableData);
                                                    }


                                                    @Override
                                                    public void onComplete(DatabaseError databaseError, boolean b,
                                                                           DataSnapshot dataSnapshot) {

                                                    }
                                                });
                                                ;
                                                return Transaction.success(mutableData);
                                            }


                                            @Override
                                            public void onComplete(DatabaseError databaseError, boolean b,
                                                                   DataSnapshot dataSnapshot) {

                                            }
                                        });
                                        return Transaction.success(mutableData);
                                    }


                                    @Override
                                    public void onComplete(DatabaseError databaseError, boolean b,
                                                           DataSnapshot dataSnapshot) {

                                    }
                                });


                            } else if (wheel1.currentIndex == wheel2.currentIndex || wheel2.currentIndex ==
                                    wheel3.currentIndex || wheel3.currentIndex == wheel4.currentIndex || wheel4.currentIndex == wheel5.currentIndex) {
                                //msg.setText("You WON the Minor Price = 350 coins");


                                //msg.setText("contniueee");


                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = current_user.getUid();

                                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("coins");


                                myUserData.runTransaction(new Transaction.Handler() {
                                    @Override
                                    public Transaction.Result doTransaction(MutableData mutableData) {
                                        long value = 0;

                                        value = mutableData.getValue(Long.class);

                                        value = value + 350;
                                        mutableData.setValue(value);

                                        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                        String uid = current_user.getUid();

                                        myUserData2 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("ranking");


                                        myUserData2.runTransaction(new Transaction.Handler() {
                                            @Override
                                            public Transaction.Result doTransaction(MutableData mutableData) {
                                                long value = 0;

                                                value = mutableData.getValue(Long.class);

                                                //500 trophies!

                                                value = value + 10;
                                                mutableData.setValue(value);
                                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                                String uid = current_user.getUid();

                                                //Level Up

                                                myUserData3 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("ranking");


                                                myUserData3.runTransaction(new Transaction.Handler() {
                                                    @Override
                                                    public Transaction.Result doTransaction(MutableData mutableData) {
                                                        long value = 0;

                                                        value = mutableData.getValue(Long.class);

                                                        //500 trophies!

                                                        value = value + 100;
                                                        mutableData.setValue(value);
                                                        ;
                                                        return Transaction.success(mutableData);
                                                    }


                                                    @Override
                                                    public void onComplete(DatabaseError databaseError, boolean b,
                                                                           DataSnapshot dataSnapshot) {

                                                    }
                                                });
                                                ;
                                                ;
                                                return Transaction.success(mutableData);
                                            }


                                            @Override
                                            public void onComplete(DatabaseError databaseError, boolean b,
                                                                   DataSnapshot dataSnapshot) {

                                            }
                                        });
                                        ;
                                        return Transaction.success(mutableData);
                                    }


                                    @Override
                                    public void onComplete(DatabaseError databaseError, boolean b,
                                                           DataSnapshot dataSnapshot) {

                                    }


                                });

                                Handler h = new Handler();
                                h.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        final LottieAnimationView lottieAnimationViewww = (LottieAnimationView) findViewById(R.id.anim3);
                                        lottieAnimationViewww.setImageAssetsFolder("images/");

                                        lottieAnimationViewww.setVisibility(View.VISIBLE);

                                        lottieAnimationViewww.setAnimation("win2.json");
                                        lottieAnimationViewww.loop(false);
                                        lottieAnimationViewww.playAnimation();

                                        lottieAnimationViewww.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                lottieAnimationViewww.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                }, 500);


                            } else {



                                Random r = new Random();
                                int game = r.nextInt(100);//msg.setText("You lose");

                                if(game < 30)
                                {

                                    Handler h = new Handler();
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            final LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.anim3);
                                            lottieAnimationView.setImageAssetsFolder("images/");

                                            //trocar anim dps ajustar

                                            lottieAnimationView.setVisibility(View.VISIBLE);

                                            lottieAnimationView.setAnimation("sil.json");
                                            lottieAnimationView.loop(false);
                                            lottieAnimationView.playAnimation();

                                            lottieAnimationView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent n = new Intent(MainActivity.this, Silverone.class);
                                                    startActivity(n);
                                                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                                    String uid = current_user.getUid();

                                                    myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("plays");


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
                                                    lottieAnimationView.setVisibility(View.GONE);

                                                }
                                            });
                                        }
                                    }, 500);




                                }




                                myCurrentUser = myAuth.getCurrentUser();

                                String current_uid = myCurrentUser.getUid();

                                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
                                myUserData.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {


                                        Integer playsm = dataSnapshot.child("plays").getValue(Integer.class);


                                        plays.setText(playsm.toString());


                                        if (playsm <= 0) {


                                            btn.setVisibility(View.GONE);
                                            buyy.setVisibility(View.VISIBLE);
                                            secondbuy.setVisibility(View.VISIBLE);
                                            or.setVisibility(View.VISIBLE);
                                        } else {//btn.setVisibility(View.VISIBLE);}
                                        }


                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }

                                });
                            }




                            btn.setVisibility(View.VISIBLE);




                            //return;

                            //btn.setText("Start");
                            // isStarted = false;


                        }
                    }, rr);


                    //isStarted = true;
                }//onclick fecha

            });


    }
        }
    }




            public void onStart() {
        super.onStart();

        /**

                ConstraintLayout ct = findViewById(R.id.activity_main);

                SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                int bg = sharedPref.getInt("acitivity_main", R.drawable.bglegendary); // the second parameter will be fallback if the preference is not found
                ct.setBackgroundResource(bg);

         */

        if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {


            FirebaseUser currentUser = myAuth.getCurrentUser();


            if (currentUser == null) {

                sendToStart();
            } else {

                myCurrentUser = myAuth.getCurrentUser();

                String current_uid = myCurrentUser.getUid();

                myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
                myUserData.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String name = dataSnapshot.child("name").getValue().toString();
                        Integer coinss = dataSnapshot.child("coins").getValue(Integer.class);
                        Integer playss = dataSnapshot.child("plays").getValue(Integer.class);
                        Integer diamondss = dataSnapshot.child("diamonds").getValue(Integer.class);
                        Integer key = dataSnapshot.child("keys").getValue(Integer.class);

                        //Integer levelnumberr = dataSnapshot.child("level").getValue(Integer.class);


                        String background = dataSnapshot.child("background").getValue().toString();
                        if(background.equals("default"))
                        {

                            ConstraintLayout  linearLayout = (ConstraintLayout) findViewById(R.id.level);
                            linearLayout.setBackgroundResource(R.drawable.bgpremium);



                        }else if(background.equals("gold"))
                        {

                            ConstraintLayout  linearLayout = (ConstraintLayout) findViewById(R.id.level);
                            linearLayout.setBackgroundResource(R.drawable.bglegendary);



                        }
                        else if(background.equals("purple"))
                        {

                            ConstraintLayout  linearLayout = (ConstraintLayout) findViewById(R.id.level);
                            linearLayout.setBackgroundResource(R.drawable.bgd11);



                        }
                        else if(background.equals("bluegradient"))
                        {

                            ConstraintLayout  linearLayout = (ConstraintLayout) findViewById(R.id.level);
                            linearLayout.setBackgroundResource(R.drawable.bgd22);



                        }
                        else if(background.equals("yellowred"))
                        {

                            ConstraintLayout  linearLayout = (ConstraintLayout) findViewById(R.id.level);
                            linearLayout.setBackgroundResource(R.drawable.bgd33);



                        }
                        else if(background.equals("brazil"))
                        {

                            ConstraintLayout  linearLayout = (ConstraintLayout) findViewById(R.id.level);
                            linearLayout.setBackgroundResource(R.drawable.bgd44);



                        }
                        else if(background.equals("cyan"))
                        {

                            ConstraintLayout  linearLayout = (ConstraintLayout) findViewById(R.id.level);
                            linearLayout.setBackgroundResource(R.drawable.bgd55);



                        }


                        plays.setText(playss.toString());


                        welcome.setText(name);
                        coins.setText(coinss.toString());
                        diamonds.setText(diamondss.toString());
                        keyss.setText(key.toString());

                        //levelnumber.setText(levelnumberr.toString());


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        }
    }

    private void sendToStart() {

        Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(startIntent);



        finish();

    }

    //Daily Notification



    private void registerAlarm(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,14);
        calendar.set(Calendar.MINUTE,19);
        calendar.set(Calendar.SECOND,30);

        Intent n = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,n,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager)this.getSystemService(this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);





    }

    //CONNECTIVITY
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You are not connected to the internet. Connect to continue.");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                System.exit(0);
            }
        });

        return builder;
    }

    public void spin() {
        /**

        myCurrentUser = myAuth.getCurrentUser();

        String current_uid = myCurrentUser.getUid();

        //FirebaseUser myCurrentUser = FirebaseAuth.getInstance().getCurrentUser();


        myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid).child("plays");*/






    }

    public  void  checkBalancePlays(){







        }








}


