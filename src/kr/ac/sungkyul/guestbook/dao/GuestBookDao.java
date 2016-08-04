package kr.ac.sungkyul.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.guestbook.vo.GuestBookVo;

public class GuestBookDao {

	// connection
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.연결얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url,"webdb","webdb");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// getList()
	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1.연결
			conn = getConnection();
			// 2. stmt 준비
			stmt = conn.createStatement();
			// 3. sql 준비
			String sql = "select no,name,content,to_char(reg_date,'yyyy-mm-dd pm hh:mm:dd')" 
			+ " from guestbook"
			+ " order by reg_date desc";
			// 4. sql실행
			rs = stmt.executeQuery(sql);

			// 5. 결과처리
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String content = rs.getString(3);
				String regDate = rs.getString(4);

				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setRegDate(regDate);

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// insert
	public boolean insert(GuestBookVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 1. sql 준비
			String sql = "insert into guestbook "
					+ "  values(seq_guestbook.nextval,?,?,?,sysdate)";

			// 2. pstmt 준비
			pstmt = conn.prepareStatement(sql);

			// 3. 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());

			// 4. sql실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

		return (count == 1);
	}

	
	//delet
	public boolean delete(Long no,String password){
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			//1. 연결
			conn = getConnection();
			
			//2.sql 준비 
			String sql = "delete from guestbook where no = ? and password = ?";
			//3. pstmt 준비
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩
			pstmt.setLong(1, no);
			pstmt.setString(2, password);
			//5. sql실행
			count = pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return (count ==1);
	}
}
