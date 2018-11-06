package com.example.nick.slotprizes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseReference myUserData;
    private FirebaseAuth myAuth;

    private FirebaseUser myCurrentUser;

    private TextView name, user, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.profile_name);
        user = findViewById(R.id.profile_username);
        number = findViewById(R.id.number_rank);



        myCurrentUser = myAuth.getCurrentUser();

        String current_uid = myCurrentUser.getUid();

        myUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
        myUserData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String names = dataSnapshot.child("name").getValue().toString();
                String usern = dataSnapshot.child("username").getValue().toString();
                Integer ranks = dataSnapshot.child("ranking").getValue(Integer.class);



                name.setText(names);
                user.setText(usern);
                number.setText(ranks.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }
}