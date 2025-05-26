package com.example.campusfest;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // to reflect background in the status bar
        setContentView(R.layout.activity_main);


        ImageView imageView = findViewById(R.id.logo);

        // Hide the text initially

        // Scale down the image
        ObjectAnimator scaleDown = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 0.6f);
        scaleDown.setDuration(1000); // 1 second
        scaleDown.start();

        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 0.6f);
        scaleDownY.setDuration(1000);
        scaleDownY.start();


        // Delay of 3 seconds (3000 milliseconds)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, intro.class);
                startActivity(intent);
                finish();  // Close the current activity if needed
            }
        }, 3000);
    }
}