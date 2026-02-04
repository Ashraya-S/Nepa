package com.example.nepa;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    private AutoCompleteTextView actvSearch;
    private RecyclerView rvSearchResults;
    private ProductAdapter adapter;
    private BottomNavigationView bottomNav;
    private ImageButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        actvSearch = findViewById(R.id.actvSearch);
        rvSearchResults = findViewById(R.id.rvSearchResults);
        bottomNav = findViewById(R.id.bottomNavSearch);
        btnSearch = findViewById(R.id.btnSearch);

        // Prepare the list of product names for suggestions
        List<String> productNames = new ArrayList<>();
        for (Product p : AppData.getInstance().getAllProducts()) {
            productNames.add(p.getName());
        }

        ArrayAdapter<String> suggestionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, productNames);
        actvSearch.setAdapter(suggestionAdapter);
        actvSearch.setThreshold(1);

        adapter = new ProductAdapter(this, new ArrayList<>());
        rvSearchResults.setLayoutManager(new GridLayoutManager(this, 2));
        rvSearchResults.setAdapter(adapter);

        actvSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch();
                return true;
            }
            return false;
        });

        btnSearch.setOnClickListener(v -> performSearch());

        actvSearch.setOnItemClickListener((parent, view, position, id) -> {
            String selection = (String) parent.getItemAtPosition(position);
            actvSearch.setText(selection);
            performSearch();
        });

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (itemId == R.id.nav_search) {
                return true;
            } else if (itemId == R.id.nav_menu) {
                startActivity(new Intent(this, Menu.class));
                return true;
            } else if (itemId == R.id.nav_cart) {
                startActivity(new Intent(this, CartActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });
        bottomNav.setSelectedItemId(R.id.nav_search);
    }

    private void performSearch() {
        String query = actvSearch.getText().toString();
        List<Product> results = AppData.getInstance().searchProducts(query);
        adapter.setProducts(results);
    }
}
