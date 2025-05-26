package com.example.campusfest;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class event_details extends AppCompatActivity {

    private TextView textDescription;
    private boolean isExpanded = false;
    private HorizontalScrollView horizontalScrollView;
    private Handler handler = new Handler();
    private int scrollX = 0;
    private int scrollSpeed = 10; // Adjust speed

    Button l1; // Location button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_details);

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowInsetsController insetsController = window.getInsetsController();
            if (insetsController != null) {
                insetsController.setSystemBarsAppearance(
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                );
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        TextView ReadMore = findViewById(R.id.btnReadMore);
        textDescription = findViewById(R.id.tvDescription);
        horizontalScrollView = findViewById(R.id.imageslider);
        l1 = findViewById(R.id.btnLocation1); // Location button

        //  FIXED: Google Maps intent
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogleMaps("DG Vaishnav College", "geo:13.0661,80.2101?q=DG Vaishnav College");
            }
        });
        // Initialize Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_events) {  // When Event icon is clicked
                Intent intent = new Intent(this, my_event.class);
                startActivity(intent);
                return true;
            }

            else if (id == R.id.nav_home) {  // When Event icon is clicked
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
                return true;
            }

            return false;
        });

        ReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    textDescription.setMaxLines(3);
                    ReadMore.setText("Read More");
                } else {
                    textDescription.setMaxLines(Integer.MAX_VALUE);
                    ReadMore.setText("Read Less");
                }
                isExpanded = !isExpanded;
            }
        });

        if (horizontalScrollView != null && horizontalScrollView.getChildCount() > 0) {
            autoScroll();
        }

        Button btnRegister = findViewById(R.id.Register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String formUrl = "https://docs.google.com/forms/d/1GS2Ip6rHr3yC8de33YrLUdZSVE8xUAwJ1nKpT1BfmJg/viewform";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(formUrl));
                startActivity(intent);
            }
        });
    }

    private void autoScroll() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (horizontalScrollView != null && horizontalScrollView.getChildAt(0) != null) {
                    int maxScroll = horizontalScrollView.getChildAt(0).getWidth() - horizontalScrollView.getWidth();
                    if (scrollX >= maxScroll) {
                        scrollX = 0;
                    } else {
                        scrollX += scrollSpeed;
                    }
                    horizontalScrollView.smoothScrollTo(scrollX, 0);
                    handler.postDelayed(this, 50);
                }
            }
        }, 50);
    }

    //  FIXED: Google Maps Intent with Proper Geo Location
    private void openGoogleMaps(String placeName, String geoUri) {
        Uri gmmIntentUri = Uri.parse(geoUri);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps"); // Open in Google Maps app
        startActivity(mapIntent);
    }
}
