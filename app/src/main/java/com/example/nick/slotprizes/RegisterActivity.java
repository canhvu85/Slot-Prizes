package com.example.nick.slotprizes;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.AppCompatEditText;
        import android.support.v7.widget.Toolbar;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import com.airbnb.lottie.LottieAnimationView;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import com.google.firebase.database.ServerValue;
        import com.google.firebase.database.ValueEventListener;

        import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private AppCompatEditText mDisplayName;
    private AppCompatEditText mEmail;
    private AppCompatEditText mPassword;
    private AppCompatEditText mUsername;
    private Button mCreatebtn;

    private Toolbar myToolbar;
    private ProgressDialog myProgress;

    private DatabaseReference myDatabase;
    private DatabaseReference myDatabase2;

    private com.google.firebase.auth.FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //myToolbar = (Toolbar)findViewById(R.id.register_toolbar);
        //setSupportActionBar(myToolbar);
        //getSupportActionBar().setTitle("Create Account");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myProgress = new ProgressDialog( this );



        mAuth = FirebaseAuth.getInstance();

        mDisplayName = (AppCompatEditText) findViewById(R.id.reg_name);
        mEmail = (AppCompatEditText) findViewById(R.id.reg_email);
        mPassword = (AppCompatEditText) findViewById(R.id.reg_password);
        mUsername = (AppCompatEditText)findViewById(R.id.username_edit);

        mCreatebtn = (Button) findViewById(R.id.reg_button);


        mCreatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String display_name = mDisplayName.getText().toString();
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                final String username = mUsername.getText().toString();

                Query usernameQ = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("username").equalTo(username);

                usernameQ.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getChildrenCount() > 0)
                        {
                            Toast.makeText(RegisterActivity.this, "Choose a different username", Toast.LENGTH_SHORT).show();
                        }else{
                            if(!TextUtils.isEmpty(display_name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(username)) {



                                myProgress.setTitle("Registering User");
                                myProgress.setMessage("Please, wait while we connect to our servers");
                                myProgress.setCanceledOnTouchOutside(false);
                                myProgress.show();

                                register_user(display_name, email, password, username);
                            }


                        }




                    }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });












                        }








        });


    }

    private void register_user(final String display_name, String email, String password, final String username) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){


                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = current_user.getUid();

                    myDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                    final HashMap<String, Object> userMap = new HashMap<>();
                    userMap.put("name", display_name);
                    userMap.put("username", username );
                    //userMap.put("status", "I Love FastChat");
                    myDatabase.push().setValue(userMap);

                    //PUSH USERNAME







                    //final HashMap<String, Integer> userMap2 = new HashMap<>();
                    userMap.put("background", "default");

                    userMap.put("coins", 250);
                    userMap.put("highscore", 0);
                    userMap.put("ranking", 0);
                    userMap.put("plays", 7);
                    userMap.put("diamonds", 10);
                    userMap.put("keys", 0);
                    userMap.put("level",1);
                    userMap.put("levelxp",0);
                    userMap.put("timestamp", ServerValue.TIMESTAMP);







                    myDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            myDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {

                                        myProgress.dismiss();
                                        mCreatebtn.setVisibility(View.GONE);
                                        LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.animation_view6);
                                        //lottieAnimationView.setImageAssetsFolder("images/");

                                        //lottieAnimationView.setAnimation("animslot.json");
                                        lottieAnimationView.loop(false);
                                        lottieAnimationView.playAnimation();

                                        Handler h = new Handler();
                                        h.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(mainIntent);
                                                finish();

                                            }
                                        },1000);


                                    }
                                }
                            });
                        }
                    });










                }else{

                    myProgress.hide();
                    Toast.makeText(RegisterActivity.this, "You got some error and cannot sign in, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}