package com.example.trialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
FirebaseAuth auth;
DatabaseReference databaseReference;
FirebaseDatabase firebaseDatabase;
EditText email;
EditText password;
ProgressDialog dialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn2 = (Button) findViewById(R.id.newuserbtn);
        Button btn1 = (Button) findViewById(R.id.loginbtn);
        email=findViewById(R.id.emailid);
        password=findViewById(R.id.pswd);
        dialog=new ProgressDialog(this);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent1 = new Intent(MainActivity.this, SignUp.class);
                MainActivity.this.startActivity(Intent1);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage("Logining In");
                dialog.show();
                String fireemail=email.getText().toString();
                String firepass=password.getText().toString();
                auth=FirebaseAuth.getInstance();

                auth.signInWithEmailAndPassword(fireemail,firepass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
dialog.dismiss();
                                    if (!task.isSuccessful()) {

                                        Toast.makeText(MainActivity.this, "No", Toast.LENGTH_LONG).show();

                                } else {
                                        Toast.makeText(MainActivity.this, "Yes", Toast.LENGTH_LONG).show();

                                    }
                            }
                        });

            }
        });
    }


}
