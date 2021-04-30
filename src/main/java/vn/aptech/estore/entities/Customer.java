package vn.aptech.estore.entities;

import java.sql.Date;

public class Customer extends Account {

    private String firstName;
    private String lastName;
    private String phone;
    private Integer age;
    private Date dateOfBirth;
    private Address address;

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    public void validate() {
        if (this.getFirstName().length() == 0) {
            this.addFieldError("firstName", "First name is required.");
        }

        if (this.getEmail().length() == 0) {
            this.addFieldError("email", "Email is required.");
        }

        if (this.getAge() < 18) {
            this.addFieldError("age", "Age is required and must be 18 or older");
        }
    }

    public void addFieldError(String fieldName, String message) {

    }
}
