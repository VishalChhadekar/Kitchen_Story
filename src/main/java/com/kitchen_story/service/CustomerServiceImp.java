package com.kitchen_story.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchen_story.entity.Customer;
import com.kitchen_story.repository.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer purchase(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomer(String invoiceNumber) {
		return customerRepository.findByInvoiceNumber(invoiceNumber);
	}

}
