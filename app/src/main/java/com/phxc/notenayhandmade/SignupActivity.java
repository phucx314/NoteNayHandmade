package com.phxc.notenayhandmade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    Button btnSignup;
    Button btnLogin;
    EditText edit_repassword_signup, edit_password_signup, edit_email_signup;

    private FirebaseAuth mAuth;
    void anhXaID() {
        btnSignup = findViewById(R.id.btn_signup);
        btnLogin = findViewById(R.id.btn_login);
        edit_email_signup = findViewById(R.id.edit_email_signup);
        edit_password_signup = findViewById(R.id.edit_password_signup);
        edit_repassword_signup = findViewById(R.id.edit_repassword_signup);
    }

    void changeStatusbarColor_black() {
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        anhXaID();
        changeStatusbarColor_black();
        mAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edit_email_signup.getText().toString();
                String password = edit_password_signup.getText().toString();
                String repassword = edit_repassword_signup.getText().toString();
//                Toast.makeText(getApplicationContext(), " "+password, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), " "+repassword, Toast.LENGTH_SHORT).show();
                if (password.equals(repassword)!=true){
                    Toast.makeText(getApplicationContext(), "Those passwords didn’t match. Try again.", Toast.LENGTH_SHORT).show();
                }
                else {
                    createNewUser(email,password);
                    Toast.makeText(getApplicationContext(), "Create new user successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                }

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    private void createNewUser(String newUserEmail, String newUserPass) {
        mAuth.createUserWithEmailAndPassword(newUserEmail,newUserPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("Debug","create new user successful");
                }else {
                    Log.d("Debug","create new user fail");
                }
            }
        });
    }
}