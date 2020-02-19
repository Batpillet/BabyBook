package com.example.splashscreenwithlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    private Button buttonConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonConnect = (Button) findViewById(R.id.buttonConnect);
        buttonConnect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openDashboard();
            }
        });
    }
    private void openDashboard(){
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }
}
