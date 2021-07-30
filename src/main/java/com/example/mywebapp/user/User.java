package com.example.mywebapp.user;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(length = 15)
    private String password;

    @Column(length = 45, nullable = false, name = "name")
    private String Name;

    @Column(length = 15)
    private String phoneNum;

    @Column(length = 15)
    private Double balance = 0.0;

    @Column(length = 15)
    private Double moneyForDeposit = 0.0;

    @Column(length = 15)
    private Double moneyForWithdraw = 0.0;


    public Double getMoneyForWithdraw() {
        return moneyForWithdraw;
    }

    public void setMoneyForWithdraw(Double moneyForWithdraw) {
        this.moneyForWithdraw = moneyForWithdraw;
    }

    public Double getMoneyForDeposit() {
        return moneyForDeposit;
    }

    public void setMoneyForDeposit(Double moneyForDeposit) {
        this.moneyForDeposit = moneyForDeposit;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", Name='" + Name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", balance=" + balance +
                '}';
    }
}
