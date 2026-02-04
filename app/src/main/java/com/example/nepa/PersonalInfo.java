package com.example.nepa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalInfo extends AppCompatActivity {

    private TextView tvFirstName, tvLastName, tvMiddleName, tvPhoneNumber, tvEmail;
    private Button btnEditFirstName, btnEditLastName, btnEditMiddleName, btnEditPhoneNumber, btnEditEmail, btnAddChangePhoto, btnRemovePhoto;
    private ImageButton backButton;
    private CircleImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvMiddleName = findViewById(R.id.tvMiddleName);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvEmail = findViewById(R.id.tvEmail);

        btnEditFirstName = findViewById(R.id.btnEditFirstName);
        btnEditLastName = findViewById(R.id.btnEditLastName);
        btnEditMiddleName = findViewById(R.id.btnEditMiddleName);
        btnEditPhoneNumber = findViewById(R.id.btnEditPhoneNumber);
        btnEditEmail = findViewById(R.id.btnEditEmail);
        btnAddChangePhoto = findViewById(R.id.btnAddChangePhoto);
        btnRemovePhoto = findViewById(R.id.btnRemovePhoto);
        backButton = findViewById(R.id.backButton);
        profileImage = findViewById(R.id.profile_image);

        loadUserInfo();

        btnEditFirstName.setOnClickListener(v -> openEditInfoActivity("First Name", tvFirstName.getText().toString()));
        btnEditLastName.setOnClickListener(v -> openEditInfoActivity("Last Name", tvLastName.getText().toString()));
        btnEditMiddleName.setOnClickListener(v -> openEditInfoActivity("Middle Name", tvMiddleName.getText().toString()));
        btnEditPhoneNumber.setOnClickListener(v -> openEditInfoActivity("Phone Number", tvPhoneNumber.getText().toString()));
        btnEditEmail.setOnClickListener(v -> openEditInfoActivity("Email", tvEmail.getText().toString()));

        btnAddChangePhoto.setOnClickListener(v -> {
            Toast.makeText(PersonalInfo.this, "Coming Soon", Toast.LENGTH_SHORT).show();
        });

        btnRemovePhoto.setOnClickListener(v -> {
            profileImage.setImageResource(R.drawable.ic_account_circle);
            Toast.makeText(PersonalInfo.this, "Profile photo removed", Toast.LENGTH_SHORT).show();
        });

        backButton.setOnClickListener(v -> finish());
    }

    private void loadUserInfo() {
        SharedPreferences prefs = getSharedPreferences(RegisterActivity.PREFS_NAME, MODE_PRIVATE);
        tvFirstName.setText(prefs.getString("user_first_name", ""));
        tvLastName.setText(prefs.getString("user_last_name", ""));
        tvMiddleName.setText(prefs.getString("user_middle_name", ""));
        tvPhoneNumber.setText(prefs.getString("user_phone_number", ""));
        tvEmail.setText(prefs.getString("user_email", ""));
    }

    private void openEditInfoActivity(String field, String value) {
        Intent intent = new Intent(this, EditInfoActivity.class);
        intent.putExtra("field", field);
        intent.putExtra("value", value);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserInfo();
    }
}
