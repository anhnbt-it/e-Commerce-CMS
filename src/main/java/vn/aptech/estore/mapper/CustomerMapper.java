/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import vn.aptech.estore.entities.Address;
import vn.aptech.estore.entities.Customer;

/**
 *
 * @author anhnbt
 */
public final class CustomerMapper {
    public Customer mapRow(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setEmail(rs.getString("email"));
        customer.setPhone(rs.getString("phone"));
        customer.setDateOfBirth(rs.getDate("date_of_birth"));
        Address address = new Address();
        customer.setAddress(address);
        customer.setCreatedBy(rs.getInt("created_by"));
        customer.setModifiedBy(rs.getInt("modified_by"));
        customer.setCreatedDate(rs.getTimestamp("created_date"));
        customer.setModifiedDate(rs.getTimestamp("modified_date"));
        return customer;
    }
}
