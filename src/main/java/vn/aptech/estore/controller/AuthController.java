package vn.aptech.estore.controller;

import vn.aptech.estore.service.AuthService;
import vn.aptech.estore.service.AuthServiceImpl;
import vn.aptech.estore.utilities.InputUtils;

public class AuthController {

    //viewOrders
    //viewCustomers
    //customerRecord
    //orderRecord
    //logout
    
    private final AuthService authService;

    public AuthController() {
        this.authService = new AuthServiceImpl();
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
}
