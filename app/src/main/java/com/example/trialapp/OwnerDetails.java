package com.example.trialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OwnerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_details);
        Button button1 = (Button) findViewById(R.id.nextbtn);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent Intent1 = new Intent(OwnerDetails.this, VehicleDetails.class);
                OwnerDetails.this.startActivity(Intent1);
            }
        });
    }
}
