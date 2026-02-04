package com.example.nepa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister, btnLogout;
    private ImageButton backButton;
    private LinearLayout loggedInLayout, loggedOutLayout;
    private TextView tvLoggedInAs, tvPersonalInfo, tvLoginSecurity, tvMyWishlist, tvOrderHistory, tvAddress;
    private CircleImageView ivProfilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogout = findViewById(R.id.btnLogout);
        backButton = findViewById(R.id.backButton);
        loggedInLayout = findViewById(R.id.loggedInLayout);
        loggedOutLayout = findViewById(R.id.loggedOutLayout);
        tvLoggedInAs = findViewById(R.id.tvLoggedInAs);
        tvPersonalInfo = findViewById(R.id.tvPersonalInfo);
        tvLoginSecurity = findViewById(R.id.tvLoginSecurity);
        tvMyWishlist = findViewById(R.id.tvMyWishlist);
        tvOrderHistory = findViewById(R.id.tvOrderHistory);
        tvAddress = findViewById(R.id.tvAddress);
        ivProfilePic = findViewById(R.id.ivProfilePic);

        SharedPreferences prefs = getSharedPreferences(RegisterActivity.PREFS_NAME, MODE_PRIVATE);
        String userName = prefs.getString("user_first_name", null);

        if (userName != null) {
            loggedInLayout.setVisibility(View.VISIBLE);
            loggedOutLayout.setVisibility(View.GONE);
            tvLoggedInAs.setText("Welcome, " + userName);
        } else {
            loggedInLayout.setVisibility(View.GONE);
            loggedOutLayout.setVisibility(View.VISIBLE);
        }

        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        });

        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, RegisterActivity.class));
        });

        btnLogout.setOnClickListener(v -> {
            prefs.edit().clear().apply();
            recreate();
        });

        backButton.setOnClickListener(v -> finish());

        tvPersonalInfo.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, PersonalInfo.class));
        });

        tvLoginSecurity.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, Security.class));
        });

        tvMyWishlist.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, Wishlist.class));
        });

        tvOrderHistory.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, OrderHistoryActivity.class));
        });

        tvAddress.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, Address.class));
        });
    }
}
