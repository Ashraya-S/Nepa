package com.example.nepa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WomenActivity extends AppCompatActivity {

    private TextView tvWomenTab, tvMenTab, tvKidsTab, tvAboutUsTab;
    private TextView tvWomenAll, tvWomenTops, tvWomenBottoms, tvWomenSareeDresses, tvWomenAccessories;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women);

        tvWomenTab = findViewById(R.id.tvWomenTab);
        tvMenTab = findViewById(R.id.tvMenTab);
        tvKidsTab = findViewById(R.id.tvKidsTab);
        tvAboutUsTab = findViewById(R.id.tvAboutUsTab);

        tvWomenAll = findViewById(R.id.tvWomenAll);
        tvWomenTops = findViewById(R.id.tvWomenTops);
        tvWomenBottoms = findViewById(R.id.tvWomenBottoms);
        tvWomenSareeDresses = findViewById(R.id.tvWomenSareeDresses);
        tvWomenAccessories = findViewById(R.id.tvWomenAccessories);
        bottomNav = findViewById(R.id.bottomNavWomen);

        tvWomenTab.setOnClickListener(v -> {
            // Already on the Women page, do nothing
        });

        tvMenTab.setOnClickListener(v -> {
            startActivity(new Intent(WomenActivity.this, MenActivity.class));
        });

        tvKidsTab.setOnClickListener(v -> {
            startActivity(new Intent(WomenActivity.this, KidsActivity.class));
        });

        tvAboutUsTab.setOnClickListener(v -> {
            startActivity(new Intent(WomenActivity.this, AboutUsActivity.class));
        });

        tvWomenAll.setOnClickListener(v -> openProductList("Women", "All"));
        tvWomenTops.setOnClickListener(v -> openProductList("Women", "Tops"));
        tvWomenBottoms.setOnClickListener(v -> openProductList("Women", "Bottoms"));
        tvWomenSareeDresses.setOnClickListener(v -> openProductList("Women", "Dresses"));
        tvWomenAccessories.setOnClickListener(v -> openProductList("Women", "Accessories"));

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
