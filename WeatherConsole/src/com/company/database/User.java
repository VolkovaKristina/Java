package com.company.database;

public class User {
    private int id;
    private String username;
    private String city;
    private Integer serviceId;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCity() {
        return city;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", city='" + city + '\'' +
                ", service_id=" + serviceId +
                '}';
    }
}
