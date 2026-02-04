package com.example.nepa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutUsActivity extends AppCompatActivity {

    private TextView tvWomenTab, tvMenTab, tvKidsTab, tvAboutUsTab;
    private BottomNavigationView bottomNav;
    private Button btnTeams;
    private LinearLayout llTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        tvWomenTab = findViewById(R.id.tvWomenTab);
        tvMenTab = findViewById(R.id.tvMenTab);
        tvKidsTab = findViewById(R.id.tvKidsTab);
        tvAboutUsTab = findViewById(R.id.tvAboutUsTab);
        bottomNav = findViewById(R.id.bottomNav);
        btnTeams = findViewById(R.id.btnTeams);
        llTeams = findViewById(R.id.llTeams);

        tvWomenTab.setOnClickListener(v -> {
            startActivity(new Intent(AboutUsActivity.this, WomenActivity.class));
        });

        tvMenTab.setOnClickListener(v -> {
            startActivity(new Intent(AboutUsActivity.this, MenActivity.class));
        });

        tvKidsTab.setOnClickListener(v -> {
            startActivity(new Intent(AboutUsActivity.this, KidsActivity.class));
        });

        tvAboutUsTab.setOnClickListener(v -> {
            // Already on the About Us page, do nothing
        });

        btnTeams.setOnClickListener(v -> {
            if (llTeams.getVisibility() == View.VISIBLE) {
                llTeams.setVisibility(View.GONE);
            } else {
                llTeams.setVisibility(View.VISIBLE);
            }
        });

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.nav_search) {
                startActivity(new Intent(this, Search.class));
                return true;
            } else if (id == R.id.nav_menu) {
                startActivity(new Intent(this, Menu.class));
                return true;
            } else if (id == R.id.nav_cart) {
                startActivity(new Intent(this, CartActivity.class));
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });
    }
}
