package com.example.babybook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class CroissanceView extends AppCompatActivity {

    private EditText taille, temps;
    private Button ajout;
    private GraphView graph;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private FirebaseFirestore fStore;
    private String userID;
    private Baby baby;
    private DataPoint[] dataPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_croissance_view);
        setTitle("Croissance de votre enfant");

        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#A3536B")));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        baby = new Baby();
        taille = findViewById(R.id.taille);
        temps = findViewById(R.id.temps);
        ajout = findViewById(R.id.ajout);
        graph = findViewById(R.id.graph);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Baby");

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Baby");
        userID = currentUser.getUid();

        ajout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             double ta = Double.valueOf(String.valueOf(taille.getText()));
             double te = Double.valueOf(String.valueOf(temps.getText()));

             baby.getxAxis().add(ta);
             baby.getyAxis().add(te);

             userID = mAuth.getCurrentUser().getUid();

             FirebaseDatabase.getInstance().getReference().child("Baby").child(userID).child("Taille").setValue(baby);

             showMessage("Ajout", "Succes");

             dataPoints = new DataPoint[baby.getyAxis().size()];
             Log.i("PoidsView", "value : " + baby.getyAxis().size());

             for (int i = 0; i < baby.getxAxis().size(); i++) {
                 dataPoints[i] = new DataPoint((Double) baby.getxAxis().get(i), (Double) baby.getyAxis().get(i));
             }

             LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);

             GridLabelRenderer gridLabel = graph.getGridLabelRenderer();

             graph.getGridLabelRenderer().setHorizontalAxisTitle("Temps");
             graph.getGridLabelRenderer().setVerticalAxisTitle("Taille");
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