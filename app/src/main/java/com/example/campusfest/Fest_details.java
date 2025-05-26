package com.example.campusfest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fest_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fest_details);

        // Register Buttons
        Button r1 = findViewById(R.id.btnRegister1);
        Button r2 = findViewById(R.id.btnRegister2);
        Button r3 = findViewById(R.id.btnRegister3);

        // Location Buttons
        Button l1 = findViewById(R.id.btnLocation1);
        Button l2 = findViewById(R.id.btnLocation2);
        Button l3 = findViewById(R.id.btnLocation3);

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

        // Open Google Form for Registration
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForm("https://docs.google.com/forms/d/1GS2Ip6rHr3yC8de33YrLUdZSVE8xUAwJ1nKpT1BfmJg/viewform");
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForm("https://docs.google.com/forms/d/1GS2Ip6rHr3yC8de33YrLUdZSVE8xUAwJ1nKpT1BfmJg/viewform");
            }
        });

        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForm("https://docs.google.com/forms/d/1GS2Ip6rHr3yC8de33YrLUdZSVE8xUAwJ1nKpT1BfmJg/viewform");
            }
        });

        // Open Google Maps with Exact Location
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogleMaps("DG Vaishnav College", "https://www.google.com/maps?q=DG+Vaishnav+College");
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogleMaps("Sri Sairam Engineering College", "https://www.google.com/maps?q=Sai Leo Nagar,West Tambaram Poonthandalam, Village, Chennai, Tamil Nadu 602109");
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogleMaps("VELTECH University", "https://www.google.com/maps?q=Vel Tech Rangarajan Dr.Sagunthala R&D Institute of Science and Technology, No.42, Avadi-Vel Tech Road Vel Nagar, Avadi, Chennai, Tamil Nadu 600062");
            }
        });
    }


    private void openForm(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private void openGoogleMaps(String placeName, String mapsUrl) {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapsUrl));
        mapIntent.setPackage("com.google.android.apps.maps"); // Open in Google Maps if installed
        startActivity(mapIntent);
    }
}
