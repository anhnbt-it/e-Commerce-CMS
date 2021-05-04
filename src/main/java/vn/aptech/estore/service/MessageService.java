package vn.aptech.estore.service;

/**
 * Created by IntelliJ IDEA.
 * User: anhnb
 * Date: 5/4/2021
 * Time: 9:39 AM
 */
public interface MessageService {

    void show(String msg);

    default void showError(String msg, Throwable ex) {
        System.out.println(msg + " hit error: " + ex.getMessage());
    }
}
