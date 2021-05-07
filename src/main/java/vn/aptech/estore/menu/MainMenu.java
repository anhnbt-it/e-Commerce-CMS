package vn.aptech.estore.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
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
                show(messages.getString("message.choice.enter"));
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
                        showNewLine("Customers");
                        break;
                    case USER_OPTION_UPDATE:
                        showNewLine("Orders");
                        break;
                    case 5:
                        showNewLine("Sign out");
                        break;
                    case USER_OPTION_EXIT:
                        show(messages.getString("message.confirm.exit"));
                        String choiceString = scanner.nextLine();
                        if ("y".equalsIgnoreCase(choiceString)) {
                            showNewLine(messages.getString("message.bye"));
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
                        show(messages.getString("message.choice.invalid") + "\n\n");
                }
            } while (true);
        } catch (Exception e) {
            LOGGER.error("MainMenu.start exception: ", e);
        }
    }

    @Override
    public void printMenuHeader() {
        displayTitle(messages.getString("greetings"));
        showNewLine(messages.getString("message.choice.title"));
        showNewLine("\t1. " + messages.getString("menu.products"));
        showNewLine("\t2. " + messages.getString("menu.categories"));
        showNewLine("\t3. " + messages.getString("menu.customers"));
        showNewLine("\t4. " + messages.getString("menu.orders"));
        showNewLine("\t5. " + messages.getString("menu.signOut"));
        showNewLine("\t6. " + messages.getString("menu.quit"));
        showNewLine("\t7. " + messages.getString("menu.shoppingCart"));
        showNewLine("\t8. " + messages.getString("menu.signIn"));
    }

}
