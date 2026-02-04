package com.example.nepa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class KidsActivity extends AppCompatActivity {

    private TextView tvWomenTab, tvMenTab, tvKidsTab, tvAboutUsTab;
    private TextView tvKidsBoys, tvKidsGirls, tvKidsFootwear, tvKidsAccessories;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids);

        tvWomenTab = findViewById(R.id.tvWomenTab);
        tvMenTab = findViewById(R.id.tvMenTab);
        tvKidsTab = findViewById(R.id.tvKidsTab);
        tvAboutUsTab = findViewById(R.id.tvAboutUsTab);

        tvKidsBoys = findViewById(R.id.tvKidsBoys);
        tvKidsGirls = findViewById(R.id.tvKidsGirls);
        tvKidsFootwear = findViewById(R.id.tvKidsFootwear);
        tvKidsAccessories = findViewById(R.id.tvKidsAccessories);
        bottomNav = findViewById(R.id.bottomNavKids);

        tvWomenTab.setOnClickListener(v -> {
            startActivity(new Intent(KidsActivity.this, WomenActivity.class));
        });

        tvMenTab.setOnClickListener(v -> {
            startActivity(new Intent(KidsActivity.this, MenActivity.class));
        });

        tvKidsTab.setOnClickListener(v -> {
            // Already on the Kids page, do nothing
        });

        tvAboutUsTab.setOnClickListener(v -> {
            startActivity(new Intent(KidsActivity.this, AboutUsActivity.class));
        });

        tvKidsBoys.setOnClickListener(v -> openProductList("Boys", "All"));
        tvKidsGirls.setOnClickListener(v -> openProductList("Girls", "All"));
        tvKidsFootwear.setOnClickListener(v -> openProductList("Kids", "Footwear"));
        tvKidsAccessories.setOnClickListener(v -> openProductList("Kids", "Accessories"));

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

    private void openProductList(String gender, String subCategory) {
        Intent i = new Intent(this, ProductListActivity.class);
        i.putExtra("gender", gender);
        i.putExtra("subcategory", subCategory);
        startActivity(i);
    }
}
