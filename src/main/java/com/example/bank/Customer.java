package com.example.bank;

public class Customer {
  String Name;
  String Phone;
  int balance;
  public String getName() {
	return Name;
}
  public void setName(String name) {
	Name = name;
  }
  public String getPhone() {
	return Phone;
  }
  public void setPhone(String phone) {
	Phone = phone;
  }
  public int getBalance() {
	return balance;
  }
  public void setBalance(int balance) {
	this.balance = balance;
  }
  public Customer(String name, String phone, int balance) {
	super();
	Name = name;
	Phone = phone;
	this.balance = balance;
	}
}
