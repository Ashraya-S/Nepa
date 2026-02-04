package com.example.nepa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Wishlist extends AppCompatActivity {

    private RecyclerView rvWishlist;
    private ProductAdapter adapter;
    private TextView tvEmptyWishlist, tvShoppingBagTab, tvWishlistTab;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        rvWishlist = findViewById(R.id.rvWishlist);
        tvEmptyWishlist = findViewById(R.id.tvEmptyWishlist);
        tvShoppingBagTab = findViewById(R.id.tvShoppingBagTab);
        tvWishlistTab = findViewById(R.id.tvWishlistTab);
        backButton = findViewById(R.id.backButton);

        rvWishlist.setLayoutManager(new LinearLayoutManager(this));

        List<Product> wishlistItems = new ArrayList<>();
        for (int productId : AppData.getInstance().getWishlistIds()) {
            wishlistItems.add(AppData.getInstance().getProductById(productId));
        }

        if (wishlistItems.isEmpty()) {
            tvEmptyWishlist.setVisibility(View.VISIBLE);
            rvWishlist.setVisibility(View.GONE);
        } else {
            tvEmptyWishlist.setVisibility(View.GONE);
            rvWishlist.setVisibility(View.VISIBLE);
        }

        adapter = new ProductAdapter(this, wishlistItems);
        rvWishlist.setAdapter(adapter);

        tvShoppingBagTab.setOnClickListener(v -> {
            startActivity(new Intent(Wishlist.this, CartActivity.class));
        });

        tvWishlistTab.setOnClickListener(v -> {
            // Already on the Wishlist page, do nothing
        });

        backButton.setOnClickListener(v -> finish());
    }
}
