package com.example.campusfest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    EditText loginEmail, loginPassword;
    Button loginBtn;
    TextView signupRedirect;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);
        signupRedirect = findViewById(R.id.signupRedirect);

        loginBtn.setOnClickListener(view -> {
            String email = loginEmail.getText().toString().trim();
            String password = loginPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(login.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                String username = databaseHelper.getUsername(email, password); // Fetch username from DB

                if (username != null) {
                    // Save username in SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("USERNAME", username); // Store username
                    editor.apply(); // Apply changes

                    Toast.makeText(login.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                    // Redirect to Home Page
                    startActivity(new Intent(login.this, Home.class));
                    finish();
                } else {
                    Toast.makeText(login.this, "Invalid Email or Password!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signupRedirect.setOnClickListener(view -> startActivity(new Intent(login.this, Signin.class)));
    }
}
