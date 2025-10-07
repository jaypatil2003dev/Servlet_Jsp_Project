package in.jp.quiz;

import java.sql.*;

public class DBConnect {
	private static Connection con;

	public static Connection getConn() {
		try {
			if (con == null || con.isClosed()) {
				// PostgreSQL driver
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/quizdb", // DB name
						"postgres", // PostgreSQL username
						"root" // PostgreSQL password
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
