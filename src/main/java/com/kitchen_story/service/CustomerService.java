package com.kitchen_story.service;

import com.kitchen_story.entity.Customer;

public interface CustomerService {

	Customer purchase(Customer customer);

	Customer getCustomer(String invoiceNumber);

}
