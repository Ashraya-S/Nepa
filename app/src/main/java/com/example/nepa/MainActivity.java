package com.example.nepa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView1, videoView2;
    private ImageButton soundToggle1, soundToggle2;
    private boolean isMuted1 = true;
    private boolean isMuted2 = true;
    private MediaPlayer mediaPlayer1, mediaPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView1 = findViewById(R.id.videoView1);
        videoView2 = findViewById(R.id.videoView2);
        soundToggle1 = findViewById(R.id.soundToggle1);
        soundToggle2 = findViewById(R.id.soundToggle2);

        setupVideo(videoView1, "home_page_feature_dresses", 1);
        setupVideo(videoView2, "nepal_beauty", 2);

        soundToggle1.setOnClickListener(v -> toggleSound(1));
        soundToggle2.setOnClickListener(v -> toggleSound(2));

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
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
        bottomNav.setSelectedItemId(R.id.nav_home);
    }

    private void setupVideo(VideoView videoView, String videoName, final int videoNumber) {
        String path = "android.resource://" + getPackageName() + "/raw/" + videoName;
        videoView.setVideoURI(Uri.parse(path));
        videoView.setOnPreparedListener(mp -> {
            if (videoNumber == 1) {
                mediaPlayer1 = mp;
            } else {
                mediaPlayer2 = mp;
            }
            mp.setLooping(true);
            if (isMuted1 && videoNumber == 1) {
                mp.setVolume(0, 0);
            } else if (isMuted2 && videoNumber == 2) {
                mp.setVolume(0, 0);
            }
            videoView.start();
        });
    }

    private void toggleSound(int videoNumber) {
        if (videoNumber == 1) {
            isMuted1 = !isMuted1;
            if (isMuted1) {
                if (mediaPlayer1 != null) mediaPlayer1.setVolume(0, 0);
                soundToggle1.setImageResource(R.drawable.ic_volume_off);
            } else {
                if (mediaPlayer1 != null) mediaPlayer1.setVolume(1, 1);
                soundToggle1.setImageResource(R.drawable.ic_volume_on);
            }
        } else {
            isMuted2 = !isMuted2;
            if (isMuted2) {
                if (mediaPlayer2 != null) mediaPlayer2.setVolume(0, 0);
                soundToggle2.setImageResource(R.drawable.ic_volume_off);
            } else {
                if (mediaPlayer2 != null) mediaPlayer2.setVolume(1, 1);
                soundToggle2.setImageResource(R.drawable.ic_volume_on);
            }
        }
    }
}
