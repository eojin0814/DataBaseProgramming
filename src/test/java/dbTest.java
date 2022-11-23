import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import model.BoardDTO;
import model.dao.BoardDao;

public class dbTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con;
		BoardDao bd = new BoardDao();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			con=DriverManager.getConnection("jdbc:oracle:thin:@dblab.dongduk.ac.kr:1521:orcl","dbpr0207","0977");
			System.out.println("디비 접속 성공");
			List<BoardDTO> list = bd.select();
			System.out.println(list);
//			BoardDTO board = new BoardDTO(4,"a","b","10","12","12/12",5);	
//			bd.create(board);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("SQLException : 디비 연동에 실패했습니다" );
		}catch(Exception e2) {
			System.out.println("Exception : "+e2);
		}

	}

}