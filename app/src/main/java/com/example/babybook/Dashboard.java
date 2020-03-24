package com.example.babybook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
=======
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
>>>>>>> a52cbe9bd4e7201b3a5386bf886694fc8d24d344


public class Dashboard extends AppCompatActivity {
    private static final String TAG = "Dashboard";
    Button btnLogout;
    FirebaseAuth mFirebaseAuth;
    String name, lastName;
    TextView NAME, LASTNAME;
<<<<<<< HEAD
    ImageView conseil;
    String userID;

    private ListView mListView;
    //private FirebaseAuth mAuth;
    private static FirebaseUser currentUser;

    FirebaseDatabase database;
    private DatabaseReference mDatabase;
=======
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

>>>>>>> a52cbe9bd4e7201b3a5386bf886694fc8d24d344

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if (!checkLoggedIn()) {
            startActivity(new Intent(Dashboard.this, Login.class));
            finish();
            return;
        }
        btnLogout = findViewById(R.id.logout);
        //mAuth = FirebaseAuth.getInstance();
        NAME = findViewById(R.id.prenom_baby);
        LASTNAME = findViewById(R.id.nom_baby);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Baby");
        userID = currentUser.getUid();

        NAME.setTextSize(30);
        LASTNAME.setTextSize(30);

        //mListView = (ListView)findViewById(R.id.listview);

       /* FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    NAME.setText(firebaseUser.name_baby);
                }

            }
        };*/
        /*if (currentUser != null) {
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //String name = dataSnapshot.child("Baby").child(userID).getValue().toString();
                    //String lastName = dataSnapshot.child("Baby").child(userID).child("lastName_baby").getValue().toString();

                    //Toast.makeText(getApplicationContext(), name , Toast.LENGTH_SHORT).show();
                    //showData(dataSnapshot);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }*/

        mDatabase.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               Baby baby = dataSnapshot.getValue(Baby.class);
               //Log.d(TAG, "Name : " + baby.getName_baby() + "LastName : " + baby.getLastName_baby());

                Toast.makeText(getApplicationContext(), "Bienvenue " + baby.getName_baby() + " " + baby.getLastName_baby() + " !" , Toast.LENGTH_SHORT).show();
                NAME.setText(baby.getName_baby());
                LASTNAME.setText(baby.getLastName_baby());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value", databaseError.toException());
            }
        });

       /* if (currentUser != null) {
            String name = currentUser.getDisplayName();
            NAME.setText(name);
            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = currentUser.getUid();
        }*/

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
<<<<<<< HEAD

        conseil = (ImageView)findViewById(R.id.conseil);
=======
        /*if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                String name = profile.getDisplayName();
                TextView.setText(name);
            }

            LinearLayout my_root = (LinearLayout) findViewById(R.id.root);
>>>>>>> a52cbe9bd4e7201b3a5386bf886694fc8d24d344

        conseil.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

<<<<<<< HEAD
                Intent myIntent=new Intent(Dashboard.this, Conseil.class);
                startActivity(myIntent);
            }
        });
    }

    private void showData(DataSnapshot dataSnapshot){
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Baby baby = new Baby();
            baby.setName_baby(ds.child(userID).getValue(Baby.class).getName_baby());
            baby.setLastName_baby(ds.child(userID).getValue(Baby.class).getLastName_baby());

            Log.d(TAG, "showData: name: " + baby.getName_baby());
            Log.d(TAG, "showData: lastName: " + baby.getLastName_baby());

            Toast.makeText(getApplicationContext(), baby.getName_baby() + "\n" + baby.getLastName_baby(), Toast.LENGTH_SHORT).show();

           ArrayList<String> array = new ArrayList<>();
           array.add(baby.getName_baby());
           array.add(baby.getLastName_baby());
           ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
           mListView.setAdapter(adapter);
        }
    }

    /*ValueEventListener changeListener = new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            String change = dataSnapshot.child(
                    currentUser.getUid()).child("message")
                    .getValue(String.class);

            NAME.setText(change);
        }
    };*/

    @Override
    protected void onStart() {
        super.onStart();
        //FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private boolean checkLoggedIn() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return false;
        } else {
            return true;
        }
    }


   /* private void updateUI(FirebaseUser user) {
        hideProgressBar();
        if (user != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.emailPasswordButtons).setVisibility(View.GONE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.GONE);
            findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);

            if (user.isEmailVerified()) {
                findViewById(R.id.verifyEmailButton).setVisibility(View.GONE);
            } else {
                findViewById(R.id.verifyEmailButton).setVisibility(View.VISIBLE);
            }
        } else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.emailPasswordButtons).setVisibility(View.VISIBLE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.VISIBLE);
            findViewById(R.id.signedInButtons).setVisibility(View.GONE);
        }
    }*/


}

=======
            TextView.setText(name);

            layout.addView(textView);
            my_root.addView(layout);*/
        }
    }

>>>>>>> a52cbe9bd4e7201b3a5386bf886694fc8d24d344


