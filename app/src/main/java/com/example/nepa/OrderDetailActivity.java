package com.example.nepa;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView tvOrderId;
    private RecyclerView rvOrderItems;
    private CartAdapter adapter;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        tvOrderId = findViewById(R.id.tvOrderId);
        rvOrderItems = findViewById(R.id.rvOrderItems);
        backButton = findViewById(R.id.backButton);

        String orderId = getIntent().getStringExtra("order_id");
        tvOrderId.setText("Order ID: " + orderId);

        Order order = AppData.getInstance().getOrderById(orderId);

        if (order != null) {
            rvOrderItems.setLayoutManager(new LinearLayoutManager(this));
            adapter = new CartAdapter(this, order.getItems(), null);
            rvOrderItems.setAdapter(adapter);
        }

        backButton.setOnClickListener(v -> finish());
    }
}
