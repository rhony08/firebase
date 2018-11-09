package me.rhon.test;

public class User {
    private String nama;
    private String pwd;

    public User() {}

    public User(String nama, String pwd) {
        this.nama = nama;
        this.pwd = pwd;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
