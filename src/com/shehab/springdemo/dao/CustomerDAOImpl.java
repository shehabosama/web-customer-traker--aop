package com.shehab.springdemo.dao;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shehab.springdemo.entity.Customer;
import com.shehab.springdemo.util.SortUtils;
@Repository
public class CustomerDAOImpl implements CustomerDAO{

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory; // make sure the import from hibernate package
	
	
	@Override
	public List<Customer> getCustomers() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query .... sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName" , Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the result 	
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// save the customer .. finally LoL
		// save / update the customer 
		currentSession.saveOrUpdate(theCustomer);
	}


	@Override
	public Customer getCustomer(int theId) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		//get customer
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}


	@Override
	public void deleteCustomer(int theId) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// get the customer
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		//delete the customer 
		currentSession.delete(theCustomer);
		
		// another way to delete the customer 
		/**
		Query theQuery = currentSession.createQuery("delete from Customer where id =:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();*/
		
	}

	 @Override
	    public List<Customer> searchCustomers(String theSearchName) {
	        // get the current hibernate session
	        Session currentSession = sessionFactory.getCurrentSession();
	        
	        Query theQuery = null;
	        
	        //
	        // only search by name if theSearchName is not empty
	        //
	        if (theSearchName != null && theSearchName.trim().length() > 0) {
	            // search for firstName or lastName ... case insensitive
	            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
	            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
	        }
	        else {
	            // theSearchName is empty ... so just get all customers
	            theQuery =currentSession.createQuery("from Customer", Customer.class);            
	        }
	        
	        // execute query and get result list
	        List<Customer> customers = theQuery.getResultList();
	                
	        // return the results        
	        return customers;
	        
	    }
	 
	 @Override
		public List<Customer> getCustomers(int theSortField) {
			
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
					
			// determine sort field
			String theFieldName = null;
			
			switch (theSortField) {
				case SortUtils.FIRST_NAME: 
					theFieldName = "firstName";
					break;
				case SortUtils.LAST_NAME:
					theFieldName = "lastName";
					break;
				case SortUtils.EMAIL:
					theFieldName = "email";
					break;
				default:
					// if nothing matches the default to sort by lastName
					theFieldName = "lastName";
			}
			
			// create a query  
			String queryString = "from Customer order by " + theFieldName;
			Query<Customer> theQuery = 
					currentSession.createQuery(queryString, Customer.class);
			
			// execute query and get result list
			List<Customer> customers = theQuery.getResultList();
					
			// return the results		
			return customers;
		}
}
