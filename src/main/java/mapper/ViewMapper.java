package mapper;

import java.sql.*;

import domain.BoardVO;

public class ViewMapper {

	public BoardVO read(BoardVO vo) {
		//DB불러오기
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		String sql = " SELECT * FROM board WHERE num = ? ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BoardVO bvo = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, vo.getNum());
			rs = stmt.executeQuery();
			if(rs.next()){
				bvo = new BoardVO();
				bvo.setNum(rs.getInt("num"));
				bvo.setTitle(rs.getString("title"));
				bvo.setContent(rs.getString("content"));
				bvo.setWriter(rs.getString("writer"));			
			}
			
		} catch (Exception e){
			e.getLocalizedMessage();
		} finally {
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(Exception e){
				e.getLocalizedMessage();
			}
		}
		return bvo;
	}

}
