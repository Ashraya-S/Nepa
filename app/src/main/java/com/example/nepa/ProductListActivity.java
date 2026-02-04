package com.example.nepa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private TextView tvTitle;
    private RecyclerView rvProducts;
    private BottomNavigationView bottomNav;
    private ImageButton backButton;

    private String gender;
    private String subCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        gender = getIntent().getStringExtra("gender");
        subCategory = getIntent().getStringExtra("subcategory");
        if (gender == null) gender = "Men";
        if (subCategory == null) subCategory = "All";

        tvTitle = findViewById(R.id.tvProductListTitle);
        rvProducts = findViewById(R.id.rvProductList);
        bottomNav = findViewById(R.id.bottomNavProducts);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> finish());

        tvTitle.setText((gender + " - " + subCategory).toUpperCase());

        List<Product> list = AppData.getInstance().getProducts(gender, subCategory);
        ProductAdapter adapter = new ProductAdapter(this, list);
        rvProducts.setLayoutManager(new GridLayoutManager(this, 2));
        rvProducts.setAdapter(adapter);

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
