package com.example.nepa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Address extends AppCompatActivity {

    private EditText etStreetAddress, etCity, etState, etPostCode;
    private Button btnSaveChanges;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        etStreetAddress = findViewById(R.id.etStreetAddress);
        etCity = findViewById(R.id.etCity);
        etState = findViewById(R.id.etState);
        etPostCode = findViewById(R.id.etPostCode);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        backButton = findViewById(R.id.backButton);

        SharedPreferences prefs = getSharedPreferences(RegisterActivity.PREFS_NAME, MODE_PRIVATE);
        etStreetAddress.setText(prefs.getString("user_street_address", ""));
        etCity.setText(prefs.getString("user_city", ""));
        etState.setText(prefs.getString("user_state", ""));
        etPostCode.setText(prefs.getString("user_post_code", ""));

        btnSaveChanges.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("user_street_address", etStreetAddress.getText().toString());
            editor.putString("user_city", etCity.getText().toString());
            editor.putString("user_state", etState.getText().toString());
            editor.putString("user_post_code", etPostCode.getText().toString());
            editor.apply();

            Toast.makeText(Address.this, "Address saved", Toast.LENGTH_SHORT).show();
            finish();
        });

        backButton.setOnClickListener(v -> finish());
    }
}
