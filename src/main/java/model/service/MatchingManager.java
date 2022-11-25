package model.service;

import java.sql.SQLException;
import java.util.List;

import model.BoardDTO;
import model.CustomerDTO;
import model.dao.BoardDao;
import model.dao.CommunityDAO;
import model.dao.CustomerDAO;
import model.dao.MatchingDAO;
import model.dao.UserDAO;

public class MatchingManager {
	private static MatchingManager matchingmanager = new MatchingManager();
	private MatchingDAO matchingDao;

	private MatchingManager() {
		try {
			matchingDao = new MatchingDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}


}
