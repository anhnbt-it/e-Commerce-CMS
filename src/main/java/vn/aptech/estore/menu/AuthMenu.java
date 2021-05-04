package vn.aptech.estore.menu;

import vn.aptech.estore.service.AuthenticationServiceImpl;
import vn.aptech.estore.utilities.InputUtils;
import vn.aptech.estore.service.AuthenticationService;

import java.sql.SQLException;

public class AuthMenu extends BaseMenu {

    //viewOrders
    //viewCustomers
    //customerRecord
    //orderRecord
    //logout
    
    private final AuthenticationService authService;

    public AuthMenu() {
        this.authService = new AuthenticationServiceImpl();
    }
    
    public void login() throws Exception {
        String username = InputUtils.inputString("Username: ");
        String password = InputUtils.inputString("Password: ");
        if (authService.isAuthenticated(username, password)) {
            // show Menu
        } else {
            throw new Exception("Sai tai khoan hoac mat khau");
        }
        
    }
    
    public void register() {
        
    }
    
    public void logout() {
        
    }
    
    public void forgottenPassword() {
        
    }

    @Override
    public void create() throws SQLException {

    }

    @Override
    public void show() throws SQLException {

    }

    @Override
    public void edit() throws SQLException {

    }

    @Override
    public void delete() {

    }

    @Override
    public void start() {

    }

    @Override
    public void printMenuHeader() {
        this.displayTitle("Dang nhap");
    }
}
