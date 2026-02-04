package com.example.nepa;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView ivDetailImage;
    private TextView tvDetailName, tvDetailPrice, tvDetailDescription, tvQuantity;
    private RadioGroup rgSize;
    private Button btnMinus, btnPlus, btnAddToCart, btnAddToWishlist;
    private ImageButton backButton;

    private Product product;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ivDetailImage = findViewById(R.id.ivDetailImage);
        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        tvQuantity = findViewById(R.id.tvQuantity);
        rgSize = findViewById(R.id.rgSize);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnAddToWishlist = findViewById(R.id.btnAddToWishlist);
        backButton = findViewById(R.id.backButton);

        int productId = getIntent().getIntExtra("product_id", -1);
        if (productId != -1) {
            product = AppData.getInstance().getProductById(productId);
            if (product != null) {
                int resId = getResources().getIdentifier(product.getImageName(), "drawable", getPackageName());
                if (resId != 0) {
                    ivDetailImage.setImageResource(resId);
                }
                tvDetailName.setText(product.getName());
                tvDetailPrice.setText("AUD " + product.getPrice());
                tvDetailDescription.setText(product.getDescription());
            }
        }

        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });

        btnPlus.setOnClickListener(v -> {
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
        });

        btnAddToCart.setOnClickListener(v -> {
            if (product != null) {
                int selectedId = rgSize.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(this, "Please select a size", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton selectedRadioButton = findViewById(selectedId);
                String size = selectedRadioButton.getText().toString();

                AppData.getInstance().addToCart(product, quantity, size);
                Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        btnAddToWishlist.setOnClickListener(v -> {
            if (product != null) {
                boolean inWishlist = AppData.getInstance().toggleWishlist(product.getId());
                if (inWishlist) {
                    Toast.makeText(this, "Added to wishlist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Removed from wishlist", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(v -> finish());
    }
}
