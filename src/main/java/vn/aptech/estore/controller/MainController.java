/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.controller;

import java.sql.SQLException;

/**
 *
 * @author anhnbt
 */
public class MainController extends BaseController {

    public static final int USER_OPTION_FINDALL = 1;
    public static final int USER_OPTION_FINDONE = 2;
    public static final int USER_OPTION_INSERT = 3;
    public static final int USER_OPTION_UPDATE = 4;
    public static final int USER_OPTION_DELETE = 4;
    public static final int USER_OPTION_EXIT = 0;

    public static final String MAIN_MENU_FOR_LOGGED_IN_USER = "1. ??ng k�\n"
            + "2. ??ng xu?t\n"
            + "3. Danh m?c\n"
            + "4. S?n ph?m\n"
            + "5. ??n h�ng\n"
            + "6. Tho�t\n";
    public static final String MAIN_MENU_FOR_LOGGED_IN_ADMIN = "1. ??ng k�\n"
            + "2. ??ng xu?t\n"
            + "3. Qu?n l� s?n ph?m\n"
            + "4. Qu?n l� nh�n vi�n\n"
            + "5. Qu?n l� kh�ch h�ng\n"
            + "6. Qu?n l� kho"
            + "6. Tho�t\n";

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printMenuHeader() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
