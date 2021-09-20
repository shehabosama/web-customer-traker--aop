package com.shehab.springdemo.dao;

import java.util.List;

import com.shehab.springdemo.entity.Customer;

public interface CustomerDAO {
	public List<Customer> getCustomers();
	public Customer getCustomer(int theId);
	public void saveCustomer(Customer theCustomer);
	public void deleteCustomer(int theId);
	public List<Customer> searchCustomers(String theSearchName);
	public List<Customer> getCustomers(int theSortField);
	

}
