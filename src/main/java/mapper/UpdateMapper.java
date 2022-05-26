package mapper;

import java.sql.*;

import domain.BoardVO;

public class UpdateMapper {

	public void update(BoardVO vo) {

		//DB저장
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		String sql = " UPDATE board SET title = ?, content = ?, writer = ? ";
		sql += " WHERE num = ? ";
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setString(3, vo.getWriter());
			stmt.setInt(4, vo.getNum());
			
			stmt.executeUpdate();
		} catch (Exception e){
			e.getLocalizedMessage();
		} finally {
			try{
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(Exception e){
				e.getLocalizedMessage();
			}
		}
		
	}

}
