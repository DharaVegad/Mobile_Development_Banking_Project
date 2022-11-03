package com.example.bankingapp;

public class Account {

    private String account_name;
    private double current_balance;
    private String account_type;

    public Account(String account_name, double current_balance, String account_type) {
        this.account_name = account_name;
        this.current_balance = current_balance;
        this.account_type = account_type;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public double getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(double current_balance) {
        this.current_balance = current_balance;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public void withdraw(double amount){
        if(this.current_balance<=0 && this.current_balance < amount){
            current_balance = 0;
        }else{
            this.current_balance -= amount;
        }
    }

    public void deposit(double amount){
        this.current_balance += amount;
    }
}

