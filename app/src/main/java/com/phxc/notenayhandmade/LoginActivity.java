package com.phxc.notenayhandmade;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.phxc.notenayhandmade.Models.Note;

public class LoginActivity extends AppCompatActivity {

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
//        ImageView btnGoogle;
    private FirebaseAuth mAuth;
    EditText edit_emaillogin, edit_passwordlogin;
    Button btn_login;
    LinearLayoutCompat btnGoogle;
    Button btnSignup;
    Button btnLogin;
    TextView forgotpassword;

    // đổi màu status bar trên android (đen)
    void changeStatusbarColor_black() {
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }

    // ánh xạ ID
    void anhXaID() {

        btnGoogle = findViewById(R.id.btn_google);
        edit_emaillogin = findViewById(R.id.edit_emaillogin);
        edit_passwordlogin = findViewById(R.id.edit_passwordlogin);
        btn_login = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);
        forgotpassword = findViewById(R.id.tv_forgotpassword);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        changeStatusbarColor_black();
        anhXaID();
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edit_emaillogin.getText().toString();
                String password = edit_passwordlogin.getText().toString();
                try {
                    login(email, password);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Please enter email or password", Toast.LENGTH_SHORT).show();

                }



            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    private void login(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("Debug","login successful");
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("emaillogin", email);
                    setResult(Activity.RESULT_OK, intent);
                } else {
                    Log.d("Debug","login fail");
                    Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void signIn() {
        startActivityForResult(googleSignInClient.getSignInIntent(), 1000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            Toast.makeText(getApplicationContext(), "Logged in with G-account", Toast.LENGTH_SHORT).show();
            try {
                task.getResult(ApiException.class);
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Error signing in to your G-account", Toast.LENGTH_SHORT).show();
            }
            navToSecondActivity();
        }
    }

    void navToSecondActivity() {
        finish();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}