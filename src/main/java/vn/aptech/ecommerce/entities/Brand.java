/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.ecommerce.entities;

/**
 *
 * @author anhnbt
 */
public class Brand extends BaseEntity {

    private String name;

    public Brand() {
        super();
    }

    /**
     *
     * @param name
     */
    public Brand(String name) {
        this();
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
