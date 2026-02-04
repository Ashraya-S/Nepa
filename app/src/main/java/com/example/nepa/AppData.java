package com.example.nepa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppData {

    private static AppData instance;

    private User loggedInUser;
    private List<Product> products;
    private List<CartItem> cartItems;
    private List<Order> orders;
    private Set<Integer> wishlistIds;

    private AppData() {
        products = new ArrayList<>();
        cartItems = new ArrayList<>();
        orders = new ArrayList<>();
        wishlistIds = new HashSet<>();
        seedProducts();
    }

    public static AppData getInstance() {
        if (instance == null) instance = new AppData();
        return instance;
    }

    private void seedProducts() {

        int id = 1;

        /* ===================== BOYS ===================== */
        products.add(new Product(id++, "Boy Bhoto",
                "Traditional bhoto for boys.",
                45, "boy_bhoto", "Boys", "Tops"));

        products.add(new Product(id++, "Boy Gopala Dress",
                "Traditional Gopala dress for boys.",
                65, "boy_gopala_dress", "Boys", "All"));

        products.add(new Product(id++, "Boy Pasni Dress",
                "Pasni ceremony dress for boys.",
                60, "boy_pasni_dress", "Boys", "All"));

        /* ===================== GIRLS ===================== */
        products.add(new Product(id++, "Girl Kurta Set",
                "Traditional kurta set for girls.",
                70, "girl_kurta_set", "Girls", "All"));

        products.add(new Product(id++, "Girl Kurti Set",
                "Classic kurti set for girls.",
                75, "girl_kurti_set", "Girls", "Tops"));

        products.add(new Product(id++, "Girl Pasni Dress",
                "Pasni ceremony dress for girls.",
                65, "girl_pasni_dress", "Girls", "All"));

        /* ===================== KIDS FOOTWEAR & ACCESSORIES ===================== */
        products.add(new Product(id++, "Boy Designer Jutti",
                "Designer jutti for boys.",
                35, "boy_footwear_designer_jutti", "Kids", "Footwear"));

        products.add(new Product(id++, "Girl Golden Jutti",
                "Golden jutti for girls.",
                35, "girl_footwear_golden_jutti", "Kids", "Footwear"));

        products.add(new Product(id++, "Boy Coat",
                "Traditional boy coat.",
                50, "kid_accessories_boy_coat", "Kids", "Accessories"));

        products.add(new Product(id++, "Boy Topi",
                "Traditional Dhaka topi for boys.",
                25, "kid_accessories_boy_topi", "Kids", "Accessories"));

        products.add(new Product(id++, "Girl Anklet",
                "Traditional anklet for girls.",
                20, "kid_accessories_girl_anklet", "Kids", "Accessories"));

        /* ===================== MEN ===================== */
        products.add(new Product(id++, "Dhaka Topi",
                "Traditional Dhaka topi.",
                35, "men_accessories_dhaka_topi", "Men", "Accessories"));

        products.add(new Product(id++, "Chandra Haar",
                "Traditional Chandra haar necklace.",
                120, "men_chandra_haar", "Men", "Accessories"));

        products.add(new Product(id++, "Daura Suruwal",
                "Classic Daura Suruwal.",
                150, "men_daura_suruwal", "Men", "Tops"));

        products.add(new Product(id++, "Daura Suruwal Blue",
                "Blue variant of Daura Suruwal.",
                155, "men_daura_suruwal_blue", "Men", "Tops"));

        products.add(new Product(id++, "Graduation Sash",
                "Graduation sash / stole.",
                40, "men_graduation_sash", "Men", "Accessories"));

        products.add(new Product(id++, "Kalo Jutti",
                "Traditional black jutti.",
                60, "men_kalo_jutti", "Men", "Shoes"));

        products.add(new Product(id++, "Mojari Shoes",
                "Traditional Mojari shoes.",
                70, "men_mojari_shoes", "Men", "Shoes"));

        products.add(new Product(id++, "Chinos Suruwal",
                "Modern chinos suruwal.",
                80, "men_bottom_chinos_suruwal", "Men", "Bottoms"));

        products.add(new Product(id++, "Loose Fit Suruwal",
                "Comfortable loose fit suruwal.",
                75, "men_bottom_loose_fit_suruwal", "Men", "Bottoms"));

        products.add(new Product(id++, "Straight Fit Suruwal",
                "Classic straight fit suruwal.",
                85, "men_bottom_straight_fit_suruwal", "Men", "Bottoms"));

        products.add(new Product(id++, "National Dress",
                "Nepali national dress.",
                160, "men_national_dress", "Men", "Tops"));

        products.add(new Product(id++, "Nepali Brooch",
                "Traditional Nepali brooch.",
                30, "men_nepali_brooch", "Men", "Accessories"));

        products.add(new Product(id++, "Newari Dress",
                "Traditional Newari dress.",
                155, "men_newari_dress", "Men", "Tops"));

        products.add(new Product(id++, "Rai Dress",
                "Traditional Rai dress.",
                150, "men_rai_dress", "Men", "Tops"));

        products.add(new Product(id++, "Sherpa Dress",
                "Traditional Sherpa dress.",
                165, "men_sherpa_dress", "Men", "Tops"));

        products.add(new Product(id++, "Sunuwar Dress",
                "Traditional Sunuwar dress.",
                155, "men_sunuwar_dress", "Men", "Tops"));

        products.add(new Product(id++, "Tamang Dress",
                "Traditional Tamang dress.",
                150, "men_tamang_dress", "Men", "Tops"));

        /* ===================== WOMEN ===================== */
        products.add(new Product(id++, "Anklet",
                "Traditional anklet.",
                35, "women_accessories_anklet", "Women", "Accessories"));

        products.add(new Product(id++, "Bangle Set",
                "Traditional bangle set.",
                50, "women_accessories_bangle_set", "Women", "Accessories"));

        products.add(new Product(id++, "Golden Jutti",
                "Traditional golden jutti.",
                65, "women_accessories_golden_jutti", "Women", "Accessories"));

        products.add(new Product(id++, "Haar Set",
                "Traditional haar jewellery set.",
                140, "women_accessories_haar_set", "Women", "Accessories"));

        products.add(new Product(id++, "Halfmoon Brooch",
                "Traditional halfmoon brooch.",
                45, "women_accessories_halfmoon_brooch", "Women", "Accessories"));

        products.add(new Product(id++, "Pote",
                "Traditional pote necklace.",
                30, "women_accessories_pote", "Women", "Accessories"));

        products.add(new Product(id++, "Red Boutique Blouse",
                "Elegant red boutique blouse.",
                80, "women_top_red_boutique_blouse", "Women", "Tops"));

        products.add(new Product(id++, "10 Tola Sari",
                "Exquisite 10 tola sari.",
                250, "women_bottom_10_tola_sari", "Women", "Bottoms"));

        products.add(new Product(id++, "Crepe Paisley Lehenga",
                "Beautiful crepe paisley lehenga.",
                220, "women_bottom_crepe_paisley_lehenga", "Women", "Bottoms"));

        products.add(new Product(id++, "Zari Embroidered Pink Lehenga",
                "Zari embroidered pink lehenga.",
                280, "women_bottom_zari_embroidered_pink_lehenga", "Women", "Bottoms"));

        products.add(new Product(id++, "Coral Green Crepe Paisley Lehenga Set",
                "Coral green crepe paisley lehenga set.",
                350, "women_coral_green_crepe_paisley_lehenga_set", "Women", "Dresses"));

        products.add(new Product(id++, "Pink Full Set Lehenga",
                "Complete pink lehenga set.",
                400, "women_full_set_pink_lehenga", "Women", "Dresses"));

        products.add(new Product(id++, "Dhaka Dress",
                "Traditional Dhaka dress.",
                160, "women_dhaka", "Women", "Dresses"));

        products.add(new Product(id++, "Gunyo Choli",
                "Traditional Gunyo Choli set.",
                140, "women_gunyo_choli", "Women", "Tops"));

        products.add(new Product(id++, "Kurtha Set",
                "Traditional kurtha set.",
                150, "women_kurtha_set", "Women", "Tops"));

        products.add(new Product(id++, "Newari Dress",
                "Traditional Newari dress.",
                155, "women_newari_dress", "Women", "Dresses"));

        products.add(new Product(id++, "Rai Dress",
                "Traditional Rai dress.",
                150, "women_rai_dress", "Women", "Dresses"));

        products.add(new Product(id++, "Sari",
                "Traditional Nepali sari.",
                170, "women_sari", "Women", "Dresses"));

        products.add(new Product(id++, "Tamang Dress",
                "Traditional Tamang dress.",
                155, "women_tamang_dress", "Women", "Dresses"));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public List<Product> searchProducts(String query) {
        List<Product> result = new ArrayList<>();
        if (query == null || query.isEmpty()) {
            return result;
        }
        String lowerCaseQuery = query.toLowerCase();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(lowerCaseQuery) ||
                p.getGender().toLowerCase().contains(lowerCaseQuery) ||
                p.getSubCategory().toLowerCase().contains(lowerCaseQuery)) {
                result.add(p);
            }
        }
        return result;
    }


    public List<Product> getProducts(String gender, String subCategory) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            boolean matchesGender = p.getGender().equalsIgnoreCase(gender);
            if (gender.equalsIgnoreCase("Kids")) {
                matchesGender = p.getGender().equalsIgnoreCase("Boys") || p.getGender().equalsIgnoreCase("Girls") || p.getGender().equalsIgnoreCase("Kids");
            }

            boolean matchesSub = p.getSubCategory().equalsIgnoreCase(subCategory) || subCategory.equalsIgnoreCase("All");

            if (matchesGender && matchesSub) {
                result.add(p);
            }
        }
        return result;
    }

    // single product lookup (used by ProductDetailActivity)
    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }


    // cart
    public List<CartItem> getCartItems() { return cartItems; }

    public void addToCart(Product product, int qty, String size) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == product.getId() && item.getSize().equals(size)) {
                item.setQuantity(item.getQuantity() + qty);
                return;
            }
        }
        cartItems.add(new CartItem(product, qty, size));
    }

    public double getCartTotal() {
        double t = 0;
        for (CartItem ci : cartItems) t += ci.getTotalPrice();
        return t;
    }

    public void clearCart() { cartItems.clear(); }

    // orders
    public List<Order> getOrders() { return orders; }
    public void addOrder(Order order) { orders.add(order); }
    public Order getOrderById(String orderId) {
        for (Order o : orders) {
            if (o.getId().equals(orderId)) {
                return o;
            }
        }
        return null;
    }

    // wishlist
    public Set<Integer> getWishlistIds() { return wishlistIds; }

    public boolean toggleWishlist(int productId) {
        if (wishlistIds.contains(productId)) {
            wishlistIds.remove(productId);
            return false;
        } else {
            wishlistIds.add(productId);
            return true;
        }
    }

    public boolean isInWishlist(int productId) {
        return wishlistIds.contains(productId);
    }

    // user
    public User getLoggedInUser() { return loggedInUser; }
    public void setLoggedInUser(User user) { this.loggedInUser = user; }
}
