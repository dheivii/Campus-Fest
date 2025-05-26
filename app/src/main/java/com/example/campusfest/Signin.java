package com.example.campusfest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Signin extends AppCompatActivity {
    EditText signupName, signupEmail, signupPassword;
    Button signupBtn;
    TextView loginRedirect;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        databaseHelper = new DatabaseHelper(this);

        signupName = findViewById(R.id.signupName);
        signupEmail = findViewById(R.id.signupEmail);
        signupPassword = findViewById(R.id.signupPassword);
        signupBtn = findViewById(R.id.signupBtn);
        loginRedirect = findViewById(R.id.loginRedirect);

        signupBtn.setOnClickListener(view -> {
            String name = signupName.getText().toString().trim();
            String email = signupEmail.getText().toString().trim();
            String password = signupPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(Signin.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean inserted = databaseHelper.insertUser(name, email, password);
                if (inserted) {
                    Toast.makeText(Signin.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Signin.this, login.class));
                    finish();
                } else {
                    Toast.makeText(Signin.this, "User already exists!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginRedirect.setOnClickListener(view -> startActivity(new Intent(Signin.this, login.class)));
    }
}
