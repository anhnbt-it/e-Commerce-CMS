package vn.aptech.estore.controller;

import vn.aptech.estore.service.AuthenticationServiceImpl;
import vn.aptech.estore.utilities.InputUtils;
import vn.aptech.estore.service.AuthenticationService;

public class AuthController {

    //viewOrders
    //viewCustomers
    //customerRecord
    //orderRecord
    //logout
    
    private final AuthenticationService authService;

    public AuthController() {
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
}
