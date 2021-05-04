/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.menu;

import vn.aptech.estore.utilities.InputUtils;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import vn.aptech.estore.entities.Category;
import vn.aptech.estore.entities.Product;
import vn.aptech.estore.service.CategoryService;
import vn.aptech.estore.service.ProductService;
import vn.aptech.estore.service.ShoppingCartService;
import vn.aptech.estore.service.CategoryServiceImpl;
import vn.aptech.estore.service.ProductServiceImpl;
import vn.aptech.estore.service.ShoppingCartServiceImpl;

/**
 * @author anhnbt
 */
public class ShoppingCartMenu extends BaseMenu {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartMenu() {
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
                show("Danh sach danh muc trong!");
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
            Logger.getLogger(CategoryMenu.class.getName()).log(Level.SEVERE, null, ex);
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

    @Override
    public void start() {
        do {
            this.printMenuHeader();
            int userOption = InputUtils.inputInteger("Nhap lua chon [1-3]: ");

            switch (userOption) {
                case 1:
                    this.show("Nhap lua chon [1-3]: ");
                    break;
                case 2:
                    this.showYourCart();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Lua chon khong ton tai.\n\n");
            }
        } while (true);
    }

    @Override
    public void printMenuHeader() {
        displayTitle("Mua hang");
        System.out.println("Vui long chon chuc nang sau do nhan <enter>: ");
        System.out.println("\t1. Danh sach tat ca danh muc");
        System.out.println("\t2. Gio hang");
        System.out.println("\t3. Quay lai man hinh chinh");
    }

    public void showProductByCategoryId(Integer categoryId) {
        Optional<Category> category;
        try {
            category = categoryService.findById(categoryId);
            if (category.isPresent()) {
                displayTitle("Danh muc san pham: " + category.get().getName());
                List<Product> products = productService.findAllByCategoryId(categoryId);
                if (products.size() == 0) {
                    show("Khong co san pham nao trong danh muc nay!");
                } else {
                    for (Product prod : products) {
                        System.out.println(prod.toString());
                    }
                    Integer productId = InputUtils.inputInteger("Nhap ID san pham de them vao gio hang: ");
                    this.addProductToCart(productId);
                }
            } else {
                show("Khong ton tai danh muc nay!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showYourCart() {
        Hashtable<Integer, Product> items = shoppingCartService.getItems();
        if (items.isEmpty()) {
            show("Gio hang cua ban trong!");
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
                show("Them san pham vao gio hang thanh cong!");
            } else {
                show("San pham tam het hang!");
            }
        } else {
            show("Khong ton tai san pham nay!");
        }
    }

}
