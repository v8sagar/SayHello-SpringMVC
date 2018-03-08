package com.app.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.app.pojos.Customer;

@Service("hm_service")
public class CustomerServiceHMImpl implements CustomerService {
	private HashMap<String, Customer> custs;
	private SimpleDateFormat sdf;

	public CustomerServiceHMImpl() throws Exception {

		sdf = new SimpleDateFormat("dd-MM-yyyy");
		custs = new HashMap<>();
		custs.put("a@gmail", new Customer(1, "a", "a@gmail", "1234", "admin", 500, sdf.parse("1-2-2016")));
		custs.put("b@gmail", new Customer(2, "b", "b@gmail", "123", "cust", 1500, sdf.parse("31-3-2016")));
		System.out.println("in service " + custs);

	}

	@Override
	public Customer validateCustomer(String em, String pass) {
		if (custs.containsKey(em)) {
			Customer c = custs.get(em);
			if (c.getPass().equals(pass))
				return c;
		}
		return null;
	}

	@Override
	public String registerCustomer(Customer c) {
		// chk for dup email
		if (custs.containsKey(c.getEmail()))
			return "Customer Registration Failed : dup email ID";
		c.setId(custs.size() + 1);
		custs.put(c.getEmail(), c);
		return "Customer Registration succeded Id " + c.getId();

	}

	@Override
	public String unsubscribeCustomer(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
