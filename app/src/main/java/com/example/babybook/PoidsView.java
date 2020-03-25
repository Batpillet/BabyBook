package com.example.babybook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class PoidsView extends AppCompatActivity {

    private EditText poids, mois;
    private Button ajout_poids;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private Baby baby;
    private Poids p;
    private FirebaseFirestore fStore;
    private String userID;
    private GraphView graph;
    private DataPoint[] dataPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poids_view);
        setTitle("Poids de votre enfant");

        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#A3536B")));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Baby");

        baby = new Baby();
        poids = findViewById(R.id.poids);
        mois = findViewById(R.id.mois);
        ajout_poids = findViewById(R.id.ajout_poids);
        graph = findViewById(R.id.graph);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        userID = currentUser.getUid();

        ajout_poids.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               double po = Double.valueOf(String.valueOf(poids.getText()));
               double mo = Double.valueOf(String.valueOf(mois.getText()));

               baby.getxAxis().add(po);
               baby.getyAxis().add(mo);

               userID = mAuth.getCurrentUser().getUid();

               FirebaseDatabase.getInstance().getReference().child("Baby").child(userID).child("Poids").setValue(baby);
               showMessage("Ajout", "succes");

               dataPoints = new DataPoint[baby.getyAxis().size()];
               Log.i("PoidsView", "value : " + baby.getyAxis().size());

               for (int i = 0; i < baby.getxAxis().size(); i++) {
                   dataPoints[i] = new DataPoint((Double) baby.getxAxis().get(i), (Double) baby.getyAxis().get(i));
               }

               LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);

               graph.getGridLabelRenderer().setHorizontalAxisTitle("Mois");
               graph.getGridLabelRenderer().setVerticalAxisTitle("Poids");
               graph.addSeries(series);
           }
        });
    }

    //Menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            //retour Dashboard
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}