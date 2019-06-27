package com.example.trialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog dialog;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final Button button1 = (Button) findViewById(R.id.signupbtn);
        final EditText et1= (EditText) findViewById(R.id.fname);
        final EditText et2= (EditText) findViewById(R.id.phnosignup);
        final EditText et3= (EditText) findViewById(R.id.emailsignup);
        final EditText et4= (EditText) findViewById(R.id.passwordsignup);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String email = et3.getText().toString().trim();
                        String password = et4.getText().toString().trim();

                        if (TextUtils.isEmpty(email)) {
                            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (TextUtils.isEmpty(password)) {
                            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (password.length() < 6) {
                            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        progressBar.setVisibility(View.VISIBLE);
                        //create user
                        auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Toast.makeText(SignUp.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        // If sign in fails, display a message to the user. If sign in succeeds
                                        // the auth state listener will be notified and logic to handle the
                                        // signed in user can be handled in the listener.
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(SignUp.this, "Authentication failed." + task.getException(),
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            startActivity(new Intent(SignUp.this, MainActivity.class));
                                            finish();
                                        }
                                    }
                                });

                    }
                });
            }

           /* @Override
            protected void onResume() {
                super.onResume();
                progressBar.setVisibility(View.GONE);
            }*/
});}}
