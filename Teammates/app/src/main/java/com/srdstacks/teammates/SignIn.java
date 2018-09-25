package com.srdstacks.teammates;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
* Sign In class is responsible for the signning in process of users
*/

public class SignIn extends AppCompatActivity implements View.OnClickListener{

    Button signInBtn;
    EditText emailET, passwordET;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.createOne).setOnClickListener(this);
        signInBtn = findViewById(R.id.buttonSignIn);
        emailET = findViewById(R.id.emailSignIn);
        passwordET = findViewById(R.id.passwordSignIn);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        Toast.makeText(this, "You are already registered" + currentUser, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.createOne :
                startActivity(new Intent(this, CreateAccount.class));
                break;
        }

    }

//    Sign In User using Fireabse and also responsible for Validation
    private void signInUser() {
        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();

        if(email.isEmpty()){
            emailET.setError("Email is required! ");
            emailET.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailET.setError("Enter a valid email ");
            emailET.requestFocus();
            return;
        }

        if(password.isEmpty()){
            passwordET.setError("Password is required! ");
            passwordET.requestFocus();
            return;
        }

        //Sign In using Firebase
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(SignIn.this, Homepage.class));
                    Toast.makeText(SignIn.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignIn.this, "Failed, Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }


    //SigIn Button CLick Listener
    public void signInHandler(View view) {
        signInUser();

    }


}
