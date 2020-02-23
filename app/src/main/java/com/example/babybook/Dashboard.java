package com.example.babybook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.babybook.R;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    Button btnLogout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
   // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnLogout = findViewById(R.id.logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(Dashboard.this, Login.class);
                startActivity(intToMain);
            }
        });
      /* if (user != null) {
           for (UserInfo profile : user.getProviderData()) {
               String name = profile.getDisplayName();
               TextView.setText(name);
           }

           LinearLayout my_root = (LinearLayout) findViewById(R.id.root);

            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            TextView textView = new TextView(this);

            layout.addView(textView);
            my_root.addView(layout);*/
       }
        }


