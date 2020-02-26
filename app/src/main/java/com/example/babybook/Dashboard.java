package com.example.babybook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;


public class Dashboard extends AppCompatActivity {
    Button btnLogout;
    FirebaseAuth mFirebaseAuth;
    String name, lastName;
    TextView NAME, LASTNAME;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnLogout = findViewById(R.id.logout);

        NAME=(TextView)findViewById(R.id.nom_baby);
        //LASTNAME = (TextView)findViewById(R.id.lastName);

        final BabyManager db = new BabyManager(this);
        db.open();

       Cursor c = db.getBaby();
        if (c.moveToFirst()) {
            NAME.setText(c.getString(1));
        }
       c.close();
       db.close();



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(Dashboard.this, Login.class);
                startActivity(intToMain);
            }
        });
        /*if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                String name = profile.getDisplayName();
                TextView.setText(name);
            }

            LinearLayout my_root = (LinearLayout) findViewById(R.id.root);

            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            TextView textView = new TextView(this);

            TextView.setText(name);

            layout.addView(textView);
            my_root.addView(layout);*/
        }
    }



