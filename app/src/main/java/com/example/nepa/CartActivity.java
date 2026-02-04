package com.example.nepa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartListener {

    private RecyclerView rvCart;
    private TextView tvTotal, tvEmpty, tvShoppingBagTab, tvWishlistTab;
    private Button btnCheckout;
    private ImageButton backButton;

    private List<CartItem> items;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvCart = findViewById(R.id.rvCart);
        tvTotal = findViewById(R.id.tvTotal);
        tvEmpty = findViewById(R.id.tvEmpty);
        btnCheckout = findViewById(R.id.btnCheckout);
        tvShoppingBagTab = findViewById(R.id.tvShoppingBagTab);
        tvWishlistTab = findViewById(R.id.tvWishlistTab);
        backButton = findViewById(R.id.backButton);

        items = AppData.getInstance().getCartItems();
        adapter = new CartAdapter(this, items, this);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.setAdapter(adapter);

        updateTotalAndEmptyState();

        backButton.setOnClickListener(v -> finish());

        tvShoppingBagTab.setOnClickListener(v -> {
            // Already on the shopping bag page, do nothing
        });

        tvWishlistTab.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences(RegisterActivity.PREFS_NAME, MODE_PRIVATE);
            String userName = prefs.getString("user_first_name", null);
            if (userName != null) {
                startActivity(new Intent(CartActivity.this, Wishlist.class));
            } else {
                startActivity(new Intent(CartActivity.this, LoginActivity.class));
            }
        });

        btnCheckout.setOnClickListener(v -> {
            if (items.isEmpty()) {
                Toast.makeText(this, getString(R.string.cart_empty), Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(new Intent(CartActivity.this, CheckoutActivity.class));
        });
    }

    @Override
    public void onCartUpdated() {
        updateTotalAndEmptyState();
    }

    private void updateTotalAndEmptyState() {
        if (items == null || items.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvCart.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvCart.setVisibility(View.VISIBLE);
        }
        tvTotal.setText(getString(R.string.total, String.valueOf(AppData.getInstance().getCartTotal())));
    }
}
