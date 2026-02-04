package com.example.nepa;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private String imageName;   // name in drawable
    private String gender;      // Men, Women, Boys, Girls, Kids
    private String subCategory; // Tops, Bottoms, Shoes, Accessories, etc.

    public Product(int id, String name, String description,
                   double price, String imageName,
                   String gender, String subCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageName = imageName;
        this.gender = gender;
        this.subCategory = subCategory;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getImageName() { return imageName; }
    public String getGender() { return gender; }
    public String getSubCategory() { return subCategory; }
}
