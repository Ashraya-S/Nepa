package com.example.nepa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CheckoutActivity extends AppCompatActivity {

    private EditText etFullName, etAddress, etPhone, etEmailCheckout;
    private Button btnPlaceOrder;
    private ImageButton backButton;
    private Spinner spinnerPayment;
    private CheckBox cbGdpr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        etFullName = findViewById(R.id.etFullName);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);
        etEmailCheckout = findViewById(R.id.etEmailCheckout);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        backButton = findViewById(R.id.backButton);
        spinnerPayment = findViewById(R.id.spinnerPayment);
        cbGdpr = findViewById(R.id.cbGdpr);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.payment_methods, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPayment.setAdapter(adapter);

        spinnerPayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.nepa_grey_text));
                } else {
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.nepa_white));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        SharedPreferences prefs = getSharedPreferences(RegisterActivity.PREFS_NAME, MODE_PRIVATE);
        String firstName = prefs.getString("user_first_name", null);
        if (firstName != null) {
            etFullName.setText(firstName + " " + prefs.getString("user_last_name", ""));
            etAddress.setText(prefs.getString("user_street_address", ""));
            etPhone.setText(prefs.getString("user_phone_number", ""));
            etEmailCheckout.setText(prefs.getString("user_email", ""));
        }

        btnPlaceOrder.setOnClickListener(v -> placeOrder());
        backButton.setOnClickListener(v -> finish());
    }

    private void placeOrder() {
        String name = etFullName.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String email = etEmailCheckout.getText().toString().trim();

        if (spinnerPayment.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
            return;
        }
        String paymentMethod = spinnerPayment.getSelectedItem().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) ||
                TextUtils.isEmpty(phone) || TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!cbGdpr.isChecked()) {
            Toast.makeText(this, "You must agree to the GDPR & APPs principles to place an order.", Toast.LENGTH_SHORT).show();
            return;
        }

        List<CartItem> cartItems = AppData.getInstance().getCartItems();
        double total = AppData.getInstance().getCartTotal();

        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        String orderId = UUID.randomUUID().toString().substring(0, 8);
        Order order = new Order(orderId, new Date(), total, cartItems, paymentMethod);
        AppData.getInstance().addOrder(order);
        AppData.getInstance().clearCart();

        Toast.makeText(this, "Order placed successfully with " + paymentMethod, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(CheckoutActivity.this, OrderHistoryActivity.class);
        startActivity(intent);
        finish();
    }
}
