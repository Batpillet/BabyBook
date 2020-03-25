package com.example.babybook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CroissanceView extends AppCompatActivity {


    EditText taille, temps;
    Button ajout;
    Context context;

    TextView see_taille,see_temps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_croissance_view);
        setTitle("Croissance de votre enfant");


        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#A3536B")));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        taille = (EditText) findViewById(R.id.taille);
        temps = (EditText) findViewById(R.id.temps);
        ajout = (Button) findViewById(R.id.ajout);

        see_taille = (TextView) findViewById(R.id.see_taille);
        see_temps = (TextView) findViewById(R.id.see_temps);

        ajout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Croissance croissance2 = new Croissance(1, 1);


                 double tailleDouble = Double.valueOf(taille.getText().toString());
                 int tempsInt = Integer.parseInt(temps.getText().toString());

                 Croissance croissance = new Croissance(tailleDouble, tempsInt);
                 CroissanceDAO croissanceDAO = new CroissanceDAO(context);
                 croissanceDAO.open();
                 croissanceDAO.createCroissance(croissance);
                 croissanceDAO.createCroissance(croissance2);

                 taille.setText("0");
                 temps.setText("0");

                 Intent intent = new Intent(CroissanceView.this, CroissanceView.class);
                 startActivity(intent);

             }
         });


        CroissanceDAO croissanceDAO = new CroissanceDAO(getApplicationContext());
        //List<Croissance> myCroissance = croissanceDAO.getAllCroissance();
        //int i = myCroissance.size() - 1;
        // if (i >= 0) {
        //see_taille.setText(myCroissance.get(i).getTaille());
        //see_temps.setText(myCroissance.get(i).getTemps());


        //}
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

}



