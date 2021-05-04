/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.entities;

import java.time.LocalDate;

/**
 *
 * @author anhnb
 */
public class Employee extends Person {
    private Department department;
    private LocalDate hireDate;
    private Double salary;
}
