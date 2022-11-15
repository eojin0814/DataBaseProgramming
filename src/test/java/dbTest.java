import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			con=DriverManager.getConnection("jdbc:oracle:thin:@dblab.dongduk.ac.kr:1521:orcl","dbpr0207","0977");
			System.out.println("디비 접속 성공");
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("SQLException : 디비 연동에 실패했습니다" );
		}catch(Exception e2) {
			System.out.println("Exception : "+e2);
		}

	}

}