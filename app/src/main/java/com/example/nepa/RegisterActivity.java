package com.example.nepa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "NEPA_PREFS";

    private EditText etFirstName, etLastName, etMiddleName, etPhoneNumber, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvAlreadyHaveAccount;
    private ImageButton backButton;
    private CheckBox cbGdpr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);  // make sure file name is activity_register.xml

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etMiddleName = findViewById(R.id.etMiddleName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvAlreadyHaveAccount = findViewById(R.id.tvAlreadyHaveAccount);
        backButton = findViewById(R.id.backButton);
        cbGdpr = findViewById(R.id.cbGdpr);

        btnRegister.setOnClickListener(v -> registerUser());

        tvAlreadyHaveAccount.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });

        backButton.setOnClickListener(v -> finish());
    }

    private void registerUser() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String middleName = etMiddleName.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirm = etConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm)) {
            Toast.makeText(this, "Please fill all mandatory fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirm)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.matches(".*[a-zA-Z]+.*_*")) {
            Toast.makeText(this, "Password must contain at least one letter", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!cbGdpr.isChecked()) {
            Toast.makeText(this, "You must agree to the GDPR & APPs principles to create an account.", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit()
                .putString("user_first_name", firstName)
                .putString("user_last_name", lastName)
                .putString("user_middle_name", middleName)
                .putString("user_phone_number", phoneNumber)
                .putString("user_email", email)
                .putString("user_password", password)
                .apply();

        Toast.makeText(this, "Account created. Please login.", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    private boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
