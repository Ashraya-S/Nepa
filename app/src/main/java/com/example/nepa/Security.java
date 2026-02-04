package com.example.nepa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Security extends AppCompatActivity {

    private EditText etOldPassword, etNewPassword, etConfirmNewPassword;
    private Button btnChangePassword;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        etOldPassword = findViewById(R.id.etOldPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmNewPassword = findViewById(R.id.etConfirmNewPassword);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        backButton = findViewById(R.id.backButton);

        btnChangePassword.setOnClickListener(v -> changePassword());
        backButton.setOnClickListener(v -> finish());
    }

    private void changePassword() {
        String oldPassword = etOldPassword.getText().toString();
        String newPassword = etNewPassword.getText().toString();
        String confirmNewPassword = etConfirmNewPassword.getText().toString();

        if (TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmNewPassword)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = getSharedPreferences(RegisterActivity.PREFS_NAME, MODE_PRIVATE);
        String savedPassword = prefs.getString("user_password", null);

        if (savedPassword == null || !savedPassword.equals(oldPassword)) {
            Toast.makeText(this, "Incorrect old password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            Toast.makeText(this, "New passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (newPassword.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.matches(".*[a-zA-Z]+.*_*")) {
            Toast.makeText(this, "Password must contain at least one letter", Toast.LENGTH_SHORT).show();
            return;
        }

        prefs.edit().putString("user_password", newPassword).apply();

        Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
