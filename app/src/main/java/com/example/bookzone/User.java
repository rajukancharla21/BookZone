package com.example.bookzone;

public class User {
    public String name;
    public String email;
    public String phone;
    public String orders;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String address;

    public User(String name, String email, String phone,String address,String orders) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address=address;
        this.orders=orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
