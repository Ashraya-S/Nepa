package com.example.nepa;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {

    private TextView tvWomenTab, tvMenTab, tvKidsTab, tvAboutUsTab;
    private BottomNavigationView bottomNav;
    private LinearLayout llShopTheLook, llFeatures;
    private VideoView videoView;
    private ImageButton soundToggle;
    private boolean isMuted = true;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tvWomenTab = findViewById(R.id.tvWomenTab);
        tvMenTab = findViewById(R.id.tvMenTab);
        tvKidsTab = findViewById(R.id.tvKidsTab);
        tvAboutUsTab = findViewById(R.id.tvAboutUsTab);
        bottomNav = findViewById(R.id.bottomNavMenu);
        llShopTheLook = findViewById(R.id.llShopTheLook);
        llFeatures = findViewById(R.id.llFeatures);
        videoView = findViewById(R.id.videoViewMenu);
        soundToggle = findViewById(R.id.soundToggleMenu);

        tvWomenTab.setOnClickListener(v -> startActivity(new Intent(this, WomenActivity.class)));
        tvMenTab.setOnClickListener(v -> startActivity(new Intent(this, MenActivity.class)));
        tvKidsTab.setOnClickListener(v -> startActivity(new Intent(this, KidsActivity.class)));
        tvAboutUsTab.setOnClickListener(v -> startActivity(new Intent(this, AboutUsActivity.class)));

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.nav_search) {
                startActivity(new Intent(this, Search.class));
                return true;
            } else if (id == R.id.nav_menu) {
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
        bottomNav.setSelectedItemId(R.id.nav_menu);

        setupVideo();

        populateShopTheLook();
        populateFeatures();
    }

    private void setupVideo() {
        String path = "android.resource://" + getPackageName() + "/raw/" + "home_video";
        videoView.setVideoURI(Uri.parse(path));
        videoView.setOnPreparedListener(mp -> {
            mediaPlayer = mp;
            mp.setLooping(true);
            if (isMuted) {
                mp.setVolume(0, 0);
                soundToggle.setImageResource(R.drawable.ic_volume_off);
            } else {
                mp.setVolume(1, 1);
                soundToggle.setImageResource(R.drawable.ic_volume_on);
            }
            videoView.start();
        });

        soundToggle.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                isMuted = !isMuted;
                if (isMuted) {
                    mediaPlayer.setVolume(0, 0);
                    soundToggle.setImageResource(R.drawable.ic_volume_off);
                } else {
                    mediaPlayer.setVolume(1, 1);
                    soundToggle.setImageResource(R.drawable.ic_volume_on);
                }
            }
        });
    }

    private void populateFeatures() {
        String[] features = {
            getString(R.string.feature_1),
            getString(R.string.feature_2),
            getString(R.string.feature_3)
        };

        LayoutInflater inflater = LayoutInflater.from(this);
        for (String feature : features) {
            View view = inflater.inflate(R.layout.item_feature, llFeatures, false);
            TextView tvFeatureText = view.findViewById(R.id.tvFeatureText);
            tvFeatureText.setText(feature);
            llFeatures.addView(view);
        }
    }

    private void populateShopTheLook() {
        List<Product> recommendations = new ArrayList<>();
        recommendations.add(AppData.getInstance().getProductById(15)); // Daura Suruwal Blue
        recommendations.add(AppData.getInstance().getProductById(41)); // Pink Full Set Lehenga
        recommendations.add(AppData.getInstance().getProductById(1));  // Boy Bhoto
        recommendations.add(AppData.getInstance().getProductById(4));  // Girl Kurta Set
        recommendations.add(AppData.getInstance().getProductById(24)); // Newari Dress (Men)
        recommendations.add(AppData.getInstance().getProductById(42)); // Dhaka Dress (Women)
        recommendations.add(AppData.getInstance().getProductById(43)); // Gunyo Choli
        recommendations.add(AppData.getInstance().getProductById(35)); // Haar Set


        LayoutInflater inflater = LayoutInflater.from(this);
        for (Product product : recommendations) {
            if (product == null) continue;
            View view = inflater.inflate(R.layout.item_menu_recommendation, llShopTheLook, false);

            ImageView ivProductImage = view.findViewById(R.id.ivProductImage);
            TextView tvProductName = view.findViewById(R.id.tvProductName);

            int resId = getResources().getIdentifier(product.getImageName(), "drawable", getPackageName());
            if (resId != 0) {
                ivProductImage.setImageResource(resId);
            }

            tvProductName.setText(product.getName());

            view.setOnClickListener(v -> {
                Intent intent = new Intent(this, ProductDetailActivity.class);
                intent.putExtra("product_id", product.getId());
                startActivity(intent);
            });

            llShopTheLook.addView(view);
        }
    }
}
