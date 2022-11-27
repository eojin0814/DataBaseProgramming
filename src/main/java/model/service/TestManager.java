package model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import model.CustomerDTO;
import model.ReservationDTO;
import model.dao.CommunityDAO;
import model.dao.JDBCUtil;
import model.dao.ReservationDAO;
import model.dao.UserDAO;

public class TestManager {	
	
	private static ReservationDAO rd = new ReservationDAO();
	
	public static void main(String[] args) throws SQLException {
	
		Scanner scanner = new Scanner(System.in);
		
		CustomerDTO customer = new CustomerDTO(1,"seungji",0,22,1,"010-111");
		rd.create(0, customer);
		
		scanner.close();
	}
		
}
