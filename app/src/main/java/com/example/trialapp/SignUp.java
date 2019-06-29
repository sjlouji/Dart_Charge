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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

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
         Button button1 = (Button) findViewById(R.id.signupbtn);
         final EditText et1= (EditText) findViewById(R.id.fname);
         final EditText et2= (EditText) findViewById(R.id.phnosignup);
         final EditText et3= (EditText) findViewById(R.id.emailsignup);
         final EditText et4= (EditText) findViewById(R.id.passwordsignup);
        auth = FirebaseAuth.getInstance();
        progressBar=new ProgressBar(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Users");
         button1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 final String name=et1.getText().toString();
                 final String phone=et2.getText().toString();
                 final String email=et3.getText().toString();
                 String password=et4.getText().toString();
                    if(TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                        et1.setError("Username is empty");
                        et2.setError("Phone is empty");
                        et3.setError("Email is empty");
                        et4.setError("Password is empty");
                    }
                    else if(TextUtils.isEmpty(name)){
                        et1.setError("Username is empty");
                    }

                    else if(TextUtils.isEmpty(phone)){
                        et1.setError("Phone is empty");
                    }

                    else if(TextUtils.isEmpty(email)){
                        et1.setError("Email is empty");
                    }

                    else if(TextUtils.isEmpty(password)){
                        et1.setError("Password is empty");
                    }
                    else{
                        auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Toast.makeText(SignUp.this, "" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(SignUp.this, "Authentication failed." + task.getException(),
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            String uid=auth.getUid();
                                            HashMap<String,String> maping=new HashMap<>();
                                            maping.put("name",name);
                                            maping.put("phone",phone);
                                            maping.put("email",email);
                                            databaseReference.child(uid).setValue(maping);
                                                    startActivity(new Intent(SignUp.this, DashBoard.class));
                                                    finish();


                                        }
                                    }
                                });
                    }

             }
         });

    }
}
