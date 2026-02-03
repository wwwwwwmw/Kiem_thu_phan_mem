package org.example;

public class Bai6 {
    private String username;
    private String password;
    private String fullname;
    private String email;

    public Bai6(String username, String password, String fullname, String email) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFullname() { return fullname; }
    public String getEmail() { return email; }
}