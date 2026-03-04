package com.example.shopping;

import java.util.*;

public class shopping {

  
    class Product {
        String id;
        String name;
        double price; 

        Product(String id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Product)) return false;
            Product p = (Product) o;
            return Objects.equals(this.id, p.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return name + " (" + id + ") - ₹" + price;
        }
    }

    
    class CartItem {
        Product product;
        int quantity; 

        CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        double lineTotal() {
            return product.price * quantity;
        }

        @Override
        public String toString() {
            return product.name + " x " + quantity + " = ₹" + String.format("%.2f", lineTotal());
        }
    }

    
    
    private final List<CartItem> items = new ArrayList<>();
    
    private final Map<Product, Integer> qtyByProduct = new HashMap<>();

    

    
    public void addToCart(Product p, int qty) {
        if (qty <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }

        
        int newQty = qtyByProduct.getOrDefault(p, 0) + qty;
        qtyByProduct.put(p, newQty);

        
        CartItem existing = findItem(p.id);
        if (existing != null) {
            existing.quantity = newQty; 
        } else {
            items.add(new CartItem(p, newQty));
        }

        System.out.println("Added: " + p.name + " x " + qty + " (total in cart: " + newQty + ")");
    }

    
    public void updateQuantity(String productId, int newQty) {
        CartItem item = findItem(productId);
        if (item == null) {
            System.out.println("Product not found in cart: " + productId);
            return;
        }

        if (newQty <= 0) {
            
            qtyByProduct.remove(item.product);
            items.remove(item);
            System.out.println("Removed: " + item.product.name);
        } else {
            item.quantity = newQty;
            qtyByProduct.put(item.product, newQty);
            System.out.println("Updated: " + item.product.name + " -> quantity " + newQty);
        }
    }

    
    public double getTotal() {
        double total = 0.0;
        for (CartItem ci : items) {
            total += ci.lineTotal();
        }
        return total;
    }

    
    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("\nCart is empty.");
            return;
        }

        System.out.println("\n--- YOUR CART ---");
        for (CartItem ci : items) {
            System.out.println("- " + ci);
        }
        System.out.println("Total: ₹" + String.format("%.2f", getTotal()));
    }

    // ----------------- Helpers -----------------

    /** Find a CartItem by product id (linear scan of ArrayList). */
    private CartItem findItem(String productId) {
        for (CartItem ci : items) {
            if (ci.product.id.equals(productId)) {
                return ci;
            }
        }
        return null;
    }

    
    public static void main(String[] args) {
        shopping app = new shopping();

        
        Product p1 = app.new Product("P101", "Wireless Mouse", 599.00);
        Product p2 = app.new Product("P102", "Mechanical Keyboard", 2499.00);
        Product p3 = app.new Product("P103", "USB-C Cable", 199.00);

        
        app.addToCart(p1, 1);  
        app.addToCart(p2, 1);  
        app.addToCart(p3, 2);  
        app.addToCart(p1, 2);  

        
        app.showCart();

       
        app.updateQuantity("P103", 1); 
        app.updateQuantity("P102", 0); 

        
        app.showCart();

        
        double total = app.getTotal();
        System.out.println("\nPayable total: ₹" + String.format("%.2f", total));
    }
}