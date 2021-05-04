package vn.aptech.estore.entities;

import java.sql.Date;

public class Customer extends Person {

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
