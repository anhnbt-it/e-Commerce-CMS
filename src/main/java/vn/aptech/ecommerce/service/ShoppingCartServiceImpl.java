/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.ecommerce.service;

import java.util.Enumeration;
import java.util.Hashtable;

import vn.aptech.ecommerce.entities.Product;


public class ShoppingCartServiceImpl implements ShoppingCartService {
    double total;
    int numberOfItems;
    private final Hashtable<Integer, Product> items;
    
    public ShoppingCartServiceImpl() {
        items = new Hashtable<>();
    }
    
    @Override
    public Hashtable<Integer, Product> getItems() {
        return items;
    }

    @Override
    public void addItem(Product product) {
        Integer key = product.getId();
        if (items.containsKey(key)) {
            // Neu ton tai san pham thi cap nhat lai so luong
            this.updateQuantity(key, product);
            System.out.println("Da cap nhat so luong gio hang!");
        } else {
            // Them san pham vao gio hang
            items.put(key, product);
            System.out.println("Them san pham vao gio hang thanh cong!");
        }
    }
    
    public void updateQuantity(Integer key, Product prod) {
        Product product = items.get(key);
        product.setQuantity(product.getQuantity() + prod.getQuantity());
        items.put(key, product);
    }

    @Override
    public int getNumberOfItems() {
        numberOfItems = 0;

        Enumeration<Integer> enu = items.keys();
        while (enu.hasMoreElements()) {
            int key = enu.nextElement();
            Product product = items.get(key);
            total += product.getUnitPrice() * product.getQuantity();
            numberOfItems += product.getQuantity();
        }

        return numberOfItems;
    }

    @Override
    public double getSubtotal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getTotal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        this.items.clear();
        this.total = 0;
        this.numberOfItems = 0;
    }
    
}
