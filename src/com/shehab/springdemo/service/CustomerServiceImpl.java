package com.shehab.springdemo.service;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shehab.springdemo.dao.CustomerDAO;
import com.shehab.springdemo.entity.Customer;
@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject the customer dao 
		@Autowired
		// need to know also that it can be a dependency because the CustomerDAOImp implements from it
		private CustomerDAO customerDAO ; // note this the interface and the interface access to the real class
	
	@Transactional
	@Override
	public List<Customer> getCustomers() {
		
		return customerDAO.getCustomers();
	}

	@Transactional
	@Override
	public void saveCustomer(Customer theCustomer) {
		customerDAO.saveCustomer(theCustomer);
		
	}

	@Transactional
	@Override
	public Customer getCustomer(int theId) {
		Customer theCustomer = customerDAO.getCustomer(theId);
		return theCustomer;
	}

	@Transactional
	@Override
	public void deleteCustomer(int theId) {
		customerDAO.deleteCustomer(theId);
	}
	@Override
    @Transactional
    public List<Customer> searchCustomers(String theSearchName) {
        return customerDAO.searchCustomers(theSearchName);
    }
	
	@Override
	@Transactional
	public List<Customer> getCustomers(int theSortField) {
		return customerDAO.getCustomers(theSortField);
	}
}
