package com.example.nepa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orders;

    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        holder.tvOrderId.setText("Order ID: " + order.getId());
        holder.tvOrderDate.setText("Date: " + sdf.format(order.getDate()));
        holder.tvOrderTotal.setText("Total: AUD " + String.format(Locale.getDefault(), "%.2f", order.getTotal()));
        holder.tvPaymentMethod.setText("Payment: " + order.getPaymentMethod());

        holder.llOrderItems.removeAllViews();
        for (CartItem item : order.getItems()) {
            TextView itemView = new TextView(context);
            itemView.setTextColor(ContextCompat.getColor(context, R.color.nepa_white));
            String itemText = String.format(Locale.getDefault(), "%d x %s (Size: %s) @ AUD %.2f",
                    item.getQuantity(),
                    item.getProduct().getName(),
                    item.getSize(),
                    item.getProduct().getPrice());
            itemView.setText(itemText);
            holder.llOrderItems.addView(itemView);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, OrderDetailActivity.class);
            intent.putExtra("order_id", order.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvOrderDate, tvOrderTotal, tvPaymentMethod;
        LinearLayout llOrderItems;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
            tvOrderTotal = itemView.findViewById(R.id.tvOrderTotal);
            tvPaymentMethod = itemView.findViewById(R.id.tvPaymentMethod);
            llOrderItems = itemView.findViewById(R.id.llOrderItems);
        }
    }
}
