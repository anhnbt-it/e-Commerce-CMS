/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author anhnbt
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier extends BaseEntity {
    String companyName;
    String thumbnailUrl;
    String vat;
    String street;
    String city;
    String postalCode;
    String country;
    String contactNumber;
}
