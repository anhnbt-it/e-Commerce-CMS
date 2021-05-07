package vn.aptech.estore.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @author anhnbt
 */
public class MainMenu extends BaseMenu {
    private static final Logger LOGGER = LogManager.getLogger(MainMenu.class);

    private static final int USER_OPTION_FIND_ALL = 1;
    private static final int USER_OPTION_FIND_ONE = 2;
    private static final int USER_OPTION_INSERT = 3;
    private static final int USER_OPTION_UPDATE = 4;
    private static final int USER_OPTION_DELETE = 4;
    private static final int USER_OPTION_EXIT = 0;

    private final Scanner scanner;
    private final ResourceBundle messages;

    public MainMenu(Scanner scanner, ResourceBundle messages) {
        this.scanner = scanner;
        this.messages = messages;
    }

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
        int choice;
        try {
            do {
                this.printMenuHeader();
                show(MessageFormat.format(messages.getString("menu.choice"), 1, 7));
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case USER_OPTION_FIND_ALL:
                        ProductMenu productController = new ProductMenu(scanner, messages);
                        productController.start();
                        break;
                    case USER_OPTION_FIND_ONE:
                        CategoryMenu categoryController = new CategoryMenu();
                        categoryController.start();
                        break;
                    case USER_OPTION_INSERT:
                        show("Customers");
                        break;
                    case USER_OPTION_UPDATE:
                        show("Orders");
                        break;
                    case 5:
                        show("Sign out");
                        break;
                    case USER_OPTION_EXIT:
                        show(messages.getString("text.exits"));
                        String choiceString = scanner.nextLine();
                        if ("y".equalsIgnoreCase(choiceString)) {
                            show(messages.getString("text.bye"));
                            System.exit(0);
                        }
                        break;
                    case 7:
                        ShoppingCartMenu shoppingCartController = new ShoppingCartMenu();
                        shoppingCartController.start();
                        break;
                    case 8:
                        AuthMenu authController = new AuthMenu();
                        authController.start();
                        break;
                    default:
                        show(MessageFormat.format(messages.getString("text.invalidChoice"), choice) + "\n\n");
                }
            } while (true);
        } catch (Exception e) {
            LOGGER.error("MainMenu.start exception: ", e);
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
