package com.srdstacks.teammates;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener{

    EditText emailET, passwordET, confirmPasswordET;
    ProgressBar pBarSignUp;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        emailET = findViewById(R.id.emailSignUp);
        passwordET = findViewById(R.id.passwordSignUp);
        confirmPasswordET = findViewById(R.id.passwordSignUp2);

        pBarSignUp = findViewById(R.id.pBarSignUp);


        findViewById(R.id.buttonSignUp).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    private void registerUser() {
        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
        String password2 = confirmPasswordET.getText().toString().trim();

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
        if(password.length()<4){
            passwordET.setError("Password should be greater than 4 char! ");
            passwordET.requestFocus();
            return;
        }
        if(!password.equals(password2)){
            confirmPasswordET.setError("Password should match! ");
            confirmPasswordET.requestFocus();
            return;
        }

        pBarSignUp.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                pBarSignUp.setVisibility(View.GONE);
                if(task.isSuccessful()){
//                    startActivity(new Intent(CreateAccount.this, University.class));
                    Toast.makeText(getApplicationContext(), "Sucessfully Registered", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Server Error, Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.emailSignUp:
                registerUser();
                break;
        }

    }
}
