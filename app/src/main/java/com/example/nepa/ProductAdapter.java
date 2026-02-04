package com.example.nepa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product p = products.get(position);
        holder.tvName.setText(p.getName());
        holder.tvPrice.setText("AUD " + p.getPrice());

        int resId = context.getResources()
                .getIdentifier(p.getImageName(), "drawable", context.getPackageName());
        if (resId != 0) holder.ivImage.setImageResource(resId);

        if (AppData.getInstance().isInWishlist(p.getId())) {
            holder.btnWishlist.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            holder.btnWishlist.setImageResource(android.R.drawable.btn_star_big_off);
        }

        holder.btnQuickAdd.setOnClickListener(v -> {
            AppData.getInstance().addToCart(p, 1, "M"); // Default size M
            Toast.makeText(context, "Added to bag", Toast.LENGTH_SHORT).show();
        });

        holder.btnWishlist.setOnClickListener(v -> {
            boolean in = AppData.getInstance().toggleWishlist(p.getId());
            if (in) {
                holder.btnWishlist.setImageResource(android.R.drawable.btn_star_big_on);
                Toast.makeText(context, "Added to favourites", Toast.LENGTH_SHORT).show();
            } else {
                holder.btnWishlist.setImageResource(android.R.drawable.btn_star_big_off);
                Toast.makeText(context, "Removed from favourites", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("product_id", p.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvName, tvPrice;
        ImageButton btnWishlist, btnQuickAdd;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivProductImage);
            tvName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvProductPrice);
            btnWishlist = itemView.findViewById(R.id.btnWishlist);
            btnQuickAdd = itemView.findViewById(R.id.btnQuickAdd);
        }
    }
}
