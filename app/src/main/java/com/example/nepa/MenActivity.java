package com.example.nepa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenActivity extends AppCompatActivity {

    private TextView tvWomenTab, tvMenTab, tvKidsTab, tvAboutUsTab;
    private TextView tvMenAll, tvMenTops, tvMenBottoms, tvMenShoes, tvMenAccessories;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men);

        tvWomenTab = findViewById(R.id.tvWomenTab);
        tvMenTab = findViewById(R.id.tvMenTab);
        tvKidsTab = findViewById(R.id.tvKidsTab);
        tvAboutUsTab = findViewById(R.id.tvAboutUsTab);

        tvMenAll = findViewById(R.id.tvMenAll);
        tvMenTops = findViewById(R.id.tvMenTops);
        tvMenBottoms = findViewById(R.id.tvMenBottoms);
        tvMenShoes = findViewById(R.id.tvMenShoes);
        tvMenAccessories = findViewById(R.id.tvMenAccessories);
        bottomNav = findViewById(R.id.bottomNavMen);

        tvWomenTab.setOnClickListener(v -> {
            startActivity(new Intent(MenActivity.this, WomenActivity.class));
        });

        tvMenTab.setOnClickListener(v -> {
            // Already on the Men page, do nothing
        });

        tvKidsTab.setOnClickListener(v -> {
            startActivity(new Intent(MenActivity.this, KidsActivity.class));
        });

        tvAboutUsTab.setOnClickListener(v -> {
            startActivity(new Intent(MenActivity.this, AboutUsActivity.class));
        });

        tvMenAll.setOnClickListener(v -> openProductList("Men", "All"));
        tvMenTops.setOnClickListener(v -> openProductList("Men", "Tops"));
        tvMenBottoms.setOnClickListener(v -> openProductList("Men", "Bottoms"));
        tvMenShoes.setOnClickListener(v -> openProductList("Men", "Shoes"));
        tvMenAccessories.setOnClickListener(v -> openProductList("Men", "Accessories"));

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
