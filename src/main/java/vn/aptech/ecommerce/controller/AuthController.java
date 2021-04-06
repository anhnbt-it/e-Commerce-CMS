package vn.aptech.ecommerce.controller;

import vn.aptech.ecommerce.service.AuthService;
import vn.aptech.ecommerce.service.AuthServiceImpl;
import vn.aptech.ecommerce.utilities.InputUtils;

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
    
    public void login() {
        String username = InputUtils.inputString("Username: ");
        String password = InputUtils.inputString("Password");
        if (authService.isAuthenticated(username, password)) {
            //is isAuthenticated
        } else {
            //login fail
        }
        
    }
    
    public void register() {
        
    }
    
    public void logout() {
        
    }
    
    public void forgottenPassword() {
        
    }
}
