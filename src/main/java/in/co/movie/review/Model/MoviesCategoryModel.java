package in.co.movie.review.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import in.co.movie.review.Exception.ApplicationException;
import in.co.movie.review.Exception.DuplicateRecordException;
import in.co.movie.review.Utility.JDBCDataSource;
import in.co.movie.review.bean.MoviesCategoryBean;
import in.co.movie.review.bean.UserBean;

public class MoviesCategoryModel {

	
	public Integer nextPk() throws Exception {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM moviescategory");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}
	
	public MoviesCategoryBean findByCategoryName(String categoryName) throws Exception {
		MoviesCategoryBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM moviescategory WHERE moviesCategoryName=?");
			ps.setString(1, categoryName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new MoviesCategoryBean();
				bean.setId(rs.getLong(1));
				bean.setCategoryName(rs.getString(2));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}
	
	
	public long add(MoviesCategoryBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;
		
		MoviesCategoryModel model = new MoviesCategoryModel();
		MoviesCategoryBean categorybean = findByCategoryName(bean.getCategoryName());
		if (categorybean != null) {
			throw new DuplicateRecordException("Category Name Already Available");
		}
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO moviescategory VALUES(?,?)");
			ps.setLong(1, pk);
		    ps.setString(2, bean.getCategoryName());
			ps.executeUpdate();
			conn.commit();
			ps.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + e.getMessage());
			}
		} finally {
			JDBCDataSource.closeconnection(conn);
		}
		return pk;
	}

	public List list() throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		try {
			Connection conn = null;
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * from moviescategory");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MoviesCategoryBean bean = new MoviesCategoryBean();
				bean.setId(rs.getLong(1));
				bean.setCategoryName(rs.getString(2));
				list.add(bean);
			}
		} catch (Exception e) {
                  e.printStackTrace();
		}
		
		return list;
	}
	
	
	public static long delete(long id) {
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from moviescategory where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	
}
