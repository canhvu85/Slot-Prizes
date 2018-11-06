package com.example.nick.slotprizes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private Toolbar myToolbar;

    private TextView pass;

    private TextInputLayout myEmail2;
    private TextInputLayout myPass;

    private Button login_btn;

    private ProgressDialog myProgressLogin;

    private FirebaseAuth myAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myAuth = FirebaseAuth.getInstance();

        //myToolbar = (Toolbar) findViewById(R.id.login_toolbar);
        //setSupportActionBar(myToolbar);


        //getSupportActionBar().setTitle("Login");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myProgressLogin = new ProgressDialog(this);

        myEmail2 = (TextInputLayout) findViewById(R.id.layemail);
        myPass = (TextInputLayout) findViewById(R.id.laypass);
        login_btn = (Button) findViewById(R.id.log_button);

        pass = findViewById(R.id.forgot_pass);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = myEmail2.getEditText().getText().toString();
                String password = myPass.getEditText().getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

                    myProgressLogin.setTitle("Logging In");
                    myProgressLogin.setMessage("Please Wait");
                    myProgressLogin.setCanceledOnTouchOutside(false);
                    myProgressLogin.show();


                    loginUser(email, password);

                }
            }
        });
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent n = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(n);


            }
        });
    }



    private void loginUser(String email, String password){

        myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    myProgressLogin.dismiss();
                    login_btn.setVisibility(View.GONE);

                    LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.animation_view5);
                    //lottieAnimationView.setImageAssetsFolder("images/");

                    //lottieAnimationView.setAnimation("animslot.json");
                    lottieAnimationView.loop(false);
                    lottieAnimationView.playAnimation();

                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent main = new Intent( LoginActivity.this, MainActivity.class);
                            main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(main);
                            finish();
                        }
                    },1000);



                }else{

                    myProgressLogin.hide();
                    Toast.makeText(LoginActivity.this, "Email or Password is wrong", Toast.LENGTH_LONG).show();

                }
            }
        });



    }



}


