/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore;

import vn.aptech.estore.controller.CategoryController;
import vn.aptech.estore.controller.ProductController;
import vn.aptech.estore.controller.ShoppingCartController;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author anhnbt
 */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int userOption;
        try {
            do {
                System.out.println("========================================================="
                        + "\n\t\tChao mung den Shop X!"
                        + "\n=========================================================");
                System.out.println("Vui long chon chuc nang sau do nhan <enter>: ");
                System.out.println("\t1. San pham");
                System.out.println("\t2. Danh muc");
                System.out.println("\t3. Khach hang");
                System.out.println("\t4. Don hang");
                System.out.println("\t5. Dang xuat");
                System.out.println("\t6. Thoat");
                System.out.println("\t7. Mua hang");
                System.out.println("\t8. Dang nhap");
                System.out.print("Nhap lua chon [1-7]: ");

                userOption = Integer.parseInt(scanner.nextLine());

                switch (userOption) {
                    case 1:
                        showProductOption(scanner);
                        break;
                    case 2:
                        showCategoryOption(scanner);
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
                        showShoppingCartOption(scanner);
                        break;
                    case 8:
                        scanner.nextLine();
                        showLogin(scanner);
                        break;
                    default:
                        System.out.println("Lua chon khong ton tai\n\n");
                }
            } while (true);
        } catch (NumberFormatException | SQLException ex) {
            System.err.println("Main: Da co loi xay ra: " + ex.getMessage());
        }
    }

    private static void showProductOption(Scanner scanner) throws SQLException {
        do {
            System.out.println("========================================================="
                    + "\n\t\tSan pham"
                    + "\n=========================================================");
            System.out.println("Vui long chon chuc nang sau do nhan <enter>: ");
            System.out.println("\t1. Danh sach tat ca san pham");
            System.out.println("\t2. Them moi mot san pham");
            System.out.println("\t3. Chinh sua mot san pham");
            System.out.println("\t4. Xoa mot san pham");
            System.out.println("\t5. Quay lai man hinh chinh");
            System.out.print("Nhap lua chon [1-5: ");
            final int choice = Integer.parseInt(scanner.nextLine());
            ProductController productController = new ProductController();
            switch (choice) {
                case 1:
                    productController.show();
                    break;
                case 2:
                    productController.create();
                    break;
                case 3:
//                    productController.edit(scanner);
                    break;
                case 4:
//                    productController.delete(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lua chon khong ton tai.\n\n");
            }
        } while (true);
    }

    private static void showCategoryOption(Scanner scanner) {
        do {
            System.out.println("========================================================="
                    + "\n\t\tDanh muc"
                    + "\n=========================================================");
            System.out.println("Vui long chon chuc nang sau do nhan <enter>: ");
            System.out.println("\t1. Danh sach tat ca danh muc");
            System.out.println("\t2. Them moi mot danh muc");
            System.out.println("\t3. Chinh sua mot danh muc");
            System.out.println("\t4. Xoa mot danh muc");
            System.out.println("\t5. Quay lai man hinh chinh");
            System.out.print("Nhap lua chon [1-5]: ");

            final int choice = Integer.parseInt(scanner.nextLine());

            CategoryController categoryController = new CategoryController();

            switch (choice) {
                case 1:
                    categoryController.show();
                    break;
                case 2:
                    categoryController.create();
                    break;
                case 3:
                    categoryController.edit();
                    break;
                case 4:
                    categoryController.delete();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lua chon khong ton tai.\n\n");
            }
        } while (true);
    }

    private static void showShoppingCartOption(Scanner scanner) throws SQLException {
        do {
            System.out.println("========================================================="
                    + "\n\t\tMua hang"
                    + "\n=========================================================");
            System.out.println("Vui long chon chuc nang sau do nhan <enter>: ");
            System.out.println("\t1. Danh sach tat ca danh muc");
            System.out.println("\t2. Gio hang");
            System.out.println("\t3. Quay lai man hinh chinh");
            System.out.print("Nhap lua chon [1-3]: ");

            final int choice = Integer.parseInt(scanner.nextLine());

            ShoppingCartController shoppingCartController = new ShoppingCartController();

            switch (choice) {
                case 1:
                    shoppingCartController.show();
                    break;
                case 2:
                    shoppingCartController.showYourCart();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Lua chon khong ton tai.\n\n");
            }
        } while (true);
    }

    private static void showLogin(Scanner scanner) {
        System.out.println("Ten dang nhap: ");
        String username = scanner.nextLine();
        System.out.println("Mat khau: ");
        String password = scanner.nextLine();
        if (username.equals("admin") && password.equals("123456")) {
            System.out.println("??ng nh?p th�nh c�ng! Ch�o m?ng " + username);
        } else {
            System.out.println("??ng nh?p th?t b?i. T�n ??ng nh?p ho?c m?t kh?u kh�ng ch�nh x�c!");
        }
    }

}
