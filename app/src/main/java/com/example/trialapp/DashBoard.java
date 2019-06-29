package com.example.trialapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashBoard extends AppCompatActivity {
    Button btn1;
    FirebaseAuth auth;
    Button signout;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        final TextView name;
        name=findViewById(R.id.joniu);
        Button button1 = (Button) findViewById(R.id.allactivitybtn);
        Button button2 = (Button) findViewById(R.id.viewvehiclesbtn);
        Button button3 = (Button) findViewById(R.id.addvehiclebtn);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Users");
         FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String uid=user.getUid();
        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String firename=dataSnapshot.child("name").getValue().toString();
                    name.setText("Welcome "+firename+"!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        signout=findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent i =new Intent(DashBoard.this,MainActivity.class);
                startActivity(i);
            }
        });
        if(auth.getCurrentUser()==null){
            Intent i =new Intent(DashBoard.this,MainActivity.class);
            startActivity(i);
        }
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
