package vn.aptech.estore;

import vn.aptech.estore.menu.MainMenu;

import java.text.DateFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @author anhnbt
 */
public class EStoreApplication {

    public static void main(String[] args) {
        String language;
        String country;
        if (args.length == 2) {
            language = args[0];
            country = args[1];
        } else {
            language = "en";
            country = "US";
        }
        Locale currentLocale = new Locale(language, country);
        ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu(scanner, messages);
        mainMenu.start();
    }
}
