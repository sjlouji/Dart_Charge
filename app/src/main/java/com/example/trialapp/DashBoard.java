package com.example.trialapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashBoard extends AppCompatActivity {
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Button button1 = (Button) findViewById(R.id.allactivitybtn);
        Button button2 = (Button) findViewById(R.id.viewvehiclesbtn);
        Button button3 = (Button) findViewById(R.id.addvehiclebtn);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent Intent1 = new Intent(DashBoard.this, AllActivity.class);
                DashBoard.this.startActivity(Intent1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent Intent2 = new Intent(DashBoard.this, ViewVehicles.class);
                DashBoard.this.startActivity(Intent2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent Intent3 = new Intent(DashBoard.this, OwnerDetails.class);
                DashBoard.this.startActivity(Intent3);
            }
        });
    }

}
