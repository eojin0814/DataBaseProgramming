package model.service;

import java.sql.SQLException;
import java.util.List;

import model.BoardDTO;
import model.CommentDTO;
import model.CustomerDTO;
import model.ReservationDTO;
import model.dao.BoardDao;
import model.dao.CommunityDAO;
import model.dao.CustomerDAO;
import model.dao.ReservationDAO;
import model.dao.UserDAO;

public class ReservationManager {
	private static ReservationManager reservationManager = new ReservationManager();
	private ReservationDAO reservationdao;

	private ReservationManager() {
		try {
			reservationdao = new ReservationDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static ReservationManager getInstance() {
		return reservationManager;
	}
	
	public void createReservation(int boardId, int userId) throws SQLException, ExistingUserException {

		reservationdao.create(boardId,userId);
	}
	
	public List<ReservationDTO> selectWaitReservation(int userId) throws SQLException, ExistingUserException {

		System.out.println(reservationdao.confirmReservation(userId));
		return reservationdao.waitReservation(userId);
	}
	public List<ReservationDTO> selectConfirmReservation(int userId) throws SQLException, ExistingUserException {

		System.out.println(reservationdao.confirmReservation(userId));
		return reservationdao.confirmReservation(userId);
		
	}
	
	//delete(유저버전),update(driver 버전) 추가
	


}
