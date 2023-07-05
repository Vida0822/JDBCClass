package days01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Inhyuk
 * @date 2023. 4. 10.
 * @subject
 * @content
			// Ex03.java    dept 테이블 조회( SELECT )
			// Ex03_02.java dept 테이블 추가( INSERT )
			// Ex03_03.java dept 테이블 수정( UPDATE )
			// Ex03_04.java dept 테이블 삭제( DELETE )
 */
public class Ex03 {

	public static void main(String[] args) {
		// Ex03.java    dept 테이블 조회( SELECT )
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String password = "tiger";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * "
					+ "FROM dept "
					+ "ORDER BY deptno ASC ";
		
		try {
			// 1.
			Class.forName(className);
			// 2.
			conn = DriverManager.getConnection(url, user, password);
			// 3.
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // SELECT 실행하는 메서드 : executeQuery()
//			10	ACCOUNTING	NEW YORK <---- true rs.next();
//			20	RESEARCH	DALLAS 	 <---- true rs.next();
//			30	SALES	CHICAGO 	 <---- true rs.next();
//			40	OPERATIONS	BOSTON 	 <---- true rs.next();
//						 			 <---- false rs.next();
			// boolean rs.next();
			while (rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				System.out.printf("%d\t%s\t%s\n", deptno, dname, loc);
			}//while
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 4.
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}//main

}//class
