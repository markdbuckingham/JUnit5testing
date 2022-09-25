package org.example;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        validateFirstName();
        validateLastName();
        validatePhoneNumber();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void validateFirstName() {
        if (firstName == null) {
            throw new RuntimeException();
        }
    }

    public void validateLastName() {
        if (lastName == null) {
            throw new RuntimeException();
        }
    }

    public void validatePhoneNumber() {
        if (phoneNumber == null) {
            throw new RuntimeException("Phone number null");
        }
        if (phoneNumber.length() != 10) {
            throw new RuntimeException("Phone number not 10 chars");
        }
        if (phoneNumber.contains("\\d+")) {
            throw new RuntimeException("Phone number not just digits");
        }
    }
}
