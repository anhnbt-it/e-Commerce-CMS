/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.service;

import java.util.Hashtable;
import vn.aptech.estore.entities.Product;

/**
 *
 * @author anhnbt
 */
public interface ShoppingCartService {
    
    public void addItem(Product product);
    
    public Hashtable<Integer, Product> getItems();
    
    public int getNumberOfItems();
    
    public double getSubtotal();
    
    public double getTotal();
    
    public void clear();
    
}
