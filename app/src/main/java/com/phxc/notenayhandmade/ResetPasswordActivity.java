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

public class ResetPasswordActivity extends AppCompatActivity {

    Button back;
    Button resetpassword;
    EditText edit_email;
    private FirebaseAuth mAuth;
    // đổi màu status bar trên android (đen)
    void changeStatusbarColor_black() {
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }

    // ánh xạ ID
    void anhXaID() {

        back = findViewById(R.id.btn_back);
        resetpassword = findViewById(R.id.btn_resetpassword);
        edit_email = findViewById(R.id.edit_email_reset);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        changeStatusbarColor_black();
        anhXaID();
        mAuth = FirebaseAuth.getInstance();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
            }
        });
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edit_email.getText().toString();
                resetPassword(email);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    //Reset password
    private void resetPassword(String email) {


        if (containsSpecialCharacters(email)) {
            Toast.makeText(getApplicationContext(), "Email cannot contain special characters", Toast.LENGTH_SHORT).show();
        } else {
            try {
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.d("Debug","reset password successful");
                            Toast.makeText(getApplicationContext(), "Reset password link has been sent to you", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                            finish();
                        }else {
                            Log.d("Debug","reset password fail");
                            Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Please enter email or password", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private boolean containsSpecialCharacters(String input) {
        // Define the pattern for allowed characters (letters, digits, and whitespace)
        String allowedCharacters = "a-zA-Z0-9@._\\s";

        // Check if the input contains any characters other than the allowed ones
        return !input.matches("[" + allowedCharacters + "]+");
    }
}