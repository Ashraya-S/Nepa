package com.example.nepa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditInfoActivity extends AppCompatActivity {

    private TextView tvEditTitle;
    private EditText etEditValue;
    private Button btnSave;
    private ImageButton backButton;

    private String fieldToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        tvEditTitle = findViewById(R.id.tvEditTitle);
        etEditValue = findViewById(R.id.etEditValue);
        btnSave = findViewById(R.id.btnSave);
        backButton = findViewById(R.id.backButton);

        fieldToEdit = getIntent().getStringExtra("field");
        String currentValue = getIntent().getStringExtra("value");

        tvEditTitle.setText("Edit " + fieldToEdit);
        etEditValue.setText(currentValue);

        btnSave.setOnClickListener(v -> {
            String newValue = etEditValue.getText().toString();
            SharedPreferences prefs = getSharedPreferences(RegisterActivity.PREFS_NAME, MODE_PRIVATE);
            prefs.edit().putString("user_" + fieldToEdit.toLowerCase().replace(" ", "_"), newValue).apply();
            Toast.makeText(EditInfoActivity.this, fieldToEdit + " updated", Toast.LENGTH_SHORT).show();
            finish();
        });

        backButton.setOnClickListener(v -> finish());
    }
}
