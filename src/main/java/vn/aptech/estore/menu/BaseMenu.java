package vn.aptech.estore.menu;

import java.sql.SQLException;

public abstract class BaseMenu {

    public abstract void create() throws SQLException;

    public abstract void show() throws SQLException;

    public abstract void edit() throws SQLException;

    public abstract void delete();

    public void displayTitle(String title) {
        System.out.println("============================================================");
        System.out.printf("\t\t %s\n", title.toUpperCase());
        System.out.println("============================================================");
    }

    public void show(String title) {
        System.out.print(title);
    }

    public void showNewLine(String title) {
        System.out.println(title);
    }

    public abstract void start();

    public abstract void printMenuHeader();

}
