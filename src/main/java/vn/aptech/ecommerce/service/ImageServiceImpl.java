/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.ecommerce.service;


public class ImageServiceImpl implements ImageService {

    @Override
    public boolean save(String fileName, Integer productId) {
        String sql = "INSERT INTO tbl_images (productId, thumbnail_url) VALUES (?, ?)";
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
