/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 *
 * @author anhnbt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person extends BaseEntity {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phone;
    protected Integer age;
    protected Date dateOfBirth;
    protected String photo;
    protected String username;
    protected String password;
    protected Gender gender;
    protected Address address;
}
