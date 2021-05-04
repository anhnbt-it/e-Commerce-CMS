/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.menu;

import vn.aptech.estore.utilities.InputUtils;

import java.sql.SQLException;

/**
 * @author anhnbt
 */
public class MainMenu extends BaseMenu {

    public static final int USER_OPTION_FIND_ALL = 1;
    public static final int USER_OPTION_FIND_ONE = 2;
    public static final int USER_OPTION_INSERT = 3;
    public static final int USER_OPTION_UPDATE = 4;
    public static final int USER_OPTION_DELETE = 4;
    public static final int USER_OPTION_EXIT = 0;

    @Override
    public void create() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void show() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        int userOption;
        try {
            do {
                this.printMenuHeader();
                userOption = InputUtils.inputInteger("Nhap lua chon [1-7]: ");

                switch (userOption) {
                    case 1:
                        ProductMenu productController = new ProductMenu();
                        productController.start();
                        break;
                    case 2:
                        CategoryMenu categoryController = new CategoryMenu();
                        categoryController.start();
                        break;
                    case 3:
                        System.out.println("Customers");
                        break;
                    case 4:
                        System.out.println("Orders");
                        break;
                    case 5:
                        System.out.println("Sign out");
                        break;
                    case 6:
                        System.out.println("Thoat khoi ung dung...");
                        System.exit(0);
                    case 7:
                        ShoppingCartMenu shoppingCartController = new ShoppingCartMenu();
                        shoppingCartController.start();
                        break;
                    case 8:
                        AuthMenu authController = new AuthMenu();
                        authController.start();
                        break;
                    default:
                        System.out.println("Lua chon khong ton tai\n\n");
                }
            } while (true);
        } catch (NumberFormatException ex) {
            System.err.println("Main: Da co loi xay ra: " + ex.getMessage());
        }
    }

    @Override
    public void printMenuHeader() {
        this.displayTitle("Welcome");
        System.out.println("Vui long chon chuc nang sau do nhan <enter>: ");
        System.out.println("\t1. San pham");
        System.out.println("\t2. Danh muc");
        System.out.println("\t3. Khach hang");
        System.out.println("\t4. Don hang");
        System.out.println("\t5. Dang xuat");
        System.out.println("\t6. Thoat");
        System.out.println("\t7. Mua hang");
        System.out.println("\t8. Dang nhap");
    }

}
