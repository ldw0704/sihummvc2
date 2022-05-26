package mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import domain.BoardVO;

public class ListMapper {

	public Collection<BoardVO> read() {
		//DB불러오기
				String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
				String user = "root";
				String password = "smart";
				String sql = " SELECT * FROM board ORDER BY num DESC ";
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				ArrayList<BoardVO> list = new ArrayList<BoardVO>();
				BoardVO vo = null;
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					conn = DriverManager.getConnection(url, user, password);
					stmt = conn.prepareStatement(sql);

					rs = stmt.executeQuery();
					while(rs.next()){
						vo = new BoardVO();
						vo.setNum(rs.getInt("num"));
						vo.setTitle(rs.getString("title"));
						vo.setWriter(rs.getString("writer"));
						vo.setWriterDate(rs.getTimestamp("writerDate"));
						list.add(vo);
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
				return list;
	}

	public int totalRow() {

		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		String sql = " SELECT count(*) as cnt FROM board ORDER BY num DESC ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int totalRow = 0;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(sql);
		
			rs = stmt.executeQuery();
			if(rs.next()){
				totalRow = rs.getInt("cnt");
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
		return totalRow;
	}
	
	public int totalRow(String keyWordTitle, String keyWordContent, String keyWordWriter) {
		//DB불러오기
				String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
				String user = "root";
				String password = "smart";
				StringBuffer qry = new StringBuffer().append(" SELECT count(*) as cnt FROM board ")
						.append("WHERE 1=1");
				if(!"".equals(keyWordTitle)) {
					qry.append(" AND title LIKE concat('%',?,'%') ");
				}
				if(!"".equals(keyWordContent)) {
					qry.append(" AND content LIKE concat('%',?,'%') ");
				}
				if(!"".equals(keyWordWriter)) {
					qry.append(" AND writer LIKE concat('%',?,'%') ");
				}
								
				String sql = qry.toString();
								
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				int idx = 1;
				int totalRow = 0;
				
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					conn = DriverManager.getConnection(url, user, password);
					stmt = conn.prepareStatement(sql);				
					
					if(!"".equals(keyWordTitle)) {
						stmt.setString(idx++, keyWordTitle);
					}
					if(!"".equals(keyWordContent)) {
						stmt.setString(idx++, keyWordContent);
					}
					if(!"".equals(keyWordWriter)) {
						stmt.setString(idx++, keyWordWriter);
					}
					
					rs = stmt.executeQuery();
					if(rs.next()){
						totalRow = rs.getInt("cnt");
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
				return totalRow;
	}

	public Collection<BoardVO> read(int startPage, int pageRow, String keyWordTitle, String keyWordContent, String keyWordWriter) {
		//DB불러오기
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		StringBuffer qry = new StringBuffer().append(" SELECT * FROM board ")
				.append("WHERE 1=1");
		if(!"".equals(keyWordTitle)) {
			qry.append(" AND title LIKE concat('%',?,'%') ");
		}
		if(!"".equals(keyWordContent)) {
			qry.append(" AND content LIKE concat('%',?,'%') ");
		}
		if(!"".equals(keyWordWriter)) {
			qry.append(" AND writer LIKE concat('%',?,'%') ");
		}
		
		qry.append(" ORDER BY num DESC LIMIT ?, ? ");
		String sql = qry.toString();
		
		//컬럼명은 따옴표를 칠숭벗음. setstring을쓰면 따옴표를 치게되므로.
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int idx = 1;
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(sql);
			
			if(!"".equals(keyWordTitle)) {
				stmt.setString(idx++, keyWordTitle);
			}
			if(!"".equals(keyWordContent)) {
				stmt.setString(idx++, keyWordContent);
			}
			if(!"".equals(keyWordWriter)) {
				stmt.setString(idx++, keyWordWriter);
			}
			
			stmt.setInt(idx++, startPage);
			stmt.setInt(idx++, pageRow);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				vo = new BoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriterDate(rs.getTimestamp("writerDate"));
				list.add(vo);
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
		return list;
	}

}
