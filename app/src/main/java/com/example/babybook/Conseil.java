package com.example.babybook;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

public class Conseil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conseil);
        setTitle("Conseils pour votre enfant");

        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#A3536B")));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
