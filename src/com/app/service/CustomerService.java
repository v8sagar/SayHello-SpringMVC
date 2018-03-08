package com.app.service;

import com.app.pojos.Customer;

public interface CustomerService {
	Customer validateCustomer(String em,String pass);
	String registerCustomer(Customer c);
	String unsubscribeCustomer(String email);
	

}
