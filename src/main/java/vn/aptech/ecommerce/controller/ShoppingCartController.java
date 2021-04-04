/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.ecommerce.controller;

import vn.aptech.ecommerce.utilities.InputUtils;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.ecommerce.entities.Category;
import vn.aptech.ecommerce.entities.Product;
import vn.aptech.ecommerce.service.CategoryService;
import vn.aptech.ecommerce.service.ProductService;
import vn.aptech.ecommerce.service.ShoppingCartService;
import vn.aptech.ecommerce.service.CategoryServiceImpl;
import vn.aptech.ecommerce.service.ProductServiceImpl;
import vn.aptech.ecommerce.service.ShoppingCartServiceImpl;

/**
 *
 * @author anhnbt
 */
public class ShoppingCartController extends BaseController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController() {
        this.categoryService = new CategoryServiceImpl();
        this.productService = new ProductServiceImpl();
        this.shoppingCartService = new ShoppingCartServiceImpl();
    }

    @Override
    public void create() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void show() throws SQLException {
        displayTitle("Mua hang");
        System.out.println("Hay chon mot danh muc de tiep tuc");
        try {
            List<Category> categories = categoryService.findAll();
            if (categories.size() < 1) {
                showMessage("Danh sach danh muc trong!");
            } else {
                System.out.println("Co tat ca '" + categories.size() + "' san pham");
                System.out.println("ID\t\tNAME");
                for (Category category : categories) {
                    System.out.printf("%d\t\t%s%n", category.getId(), category.getName());
                }
                Integer categoryId = InputUtils.inputInteger("Nhap ID danh muc de xem san pham: ");
                this.showProductByCategoryId(categoryId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edit() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void showProductByCategoryId(Integer categoryId) {
        Optional<Category> category;
        try {
            category = categoryService.findById(categoryId);
            if (category.isPresent()) {
                displayTitle("Danh muc san pham: " + category.get().getName());
                List<Product> products = productService.findAllByCategoryId(categoryId);
                if (products.size() == 0) {
                    showMessage("Khong co san pham nao trong danh muc nay!");
                } else {
                    for (Product prod : products) {
                        System.out.println(prod.toString());
                    }
                    Integer productId = InputUtils.inputInteger("Nhap ID san pham de them vao gio hang: ");
                    this.addProductToCart(productId);
                }
            } else {
                showMessage("Khong ton tai danh muc nay!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showYourCart() {
        Hashtable<Integer, Product> items = shoppingCartService.getItems();
        if (items.isEmpty()) {
            showMessage("Gio hang cua ban trong!");
        } else {
            Enumeration<Integer> enu = items.keys();
            double total = 0;
            while (enu.hasMoreElements()) {
                int key = enu.nextElement();
                Product product = items.get(key);
                total += product.getUnitPrice() * product.getQuantity();
                System.out.println(product.toString());
            }
            System.out.println("Cart subtotal (" + items.size() + " items): $" + total);
        }
    }

    private void addProductToCart(Integer productId) throws SQLException {
        Optional<Product> product = productService.findById(productId);
        if (product.isPresent()) {
            if (product.get().getQuantity() > 0) {
                shoppingCartService.addItem(product.get());
                showMessage("Them san pham vao gio hang thanh cong!");
            } else {
                showMessage("San pham tam het hang!");
            }
        } else {
            showMessage("Khong ton tai san pham nay!");
        }
    }

}
