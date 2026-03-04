package com.example.bank;

public class CustomerController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer c1=new Customer("Sakthi","89225201223",102923920);
		c1.setName("Associate");
		c1.setPhone("2825201524");
		c1.setBalance(100000);
		System.out.println(c1.getName()+""+c1.getBalance()+""+c1.getPhone());
	}

}
