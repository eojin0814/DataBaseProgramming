package model.service;

import java.sql.SQLException;

import model.CustomerDTO;
import model.dao.CommunityDAO;
import model.dao.CustomerDAO;
import model.dao.UserDAO;

public class CustomerManager {
	private static CustomerManager customerMan = new CustomerManager();
	private CustomerDAO customerDAO;

	private CustomerManager() {
		try {
			customerDAO = new CustomerDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static CustomerManager getInstance() {
		return customerMan;
	}
	
	public void customerCreate(CustomerDTO customer) throws SQLException, ExistingUserException {
//		if (userDAO.existingUser(user.getUserId()) == true) {
//			throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
//		}
		customerDAO.create(customer);
	}
}
