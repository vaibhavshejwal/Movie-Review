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
import in.co.movie.review.bean.GiveReview;
import in.co.movie.review.bean.MoviesCategoryBean;

public class GiveReviewModel {

	public Integer nextPk() throws Exception {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM givereview");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}
	
	
	public GiveReview status(String movie,long userid) throws Exception {
		GiveReview bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM givereview WHERE movie=? and userid=?");
			ps.setString(1, movie);
			ps.setLong(2, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new GiveReview();
				bean.setId(rs.getLong(1));
				bean.setMovieName(rs.getString(2));
				bean.setUserId(rs.getLong(3));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}
	
	
	public long add(GiveReview bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;
		
		GiveReviewModel model = new GiveReviewModel();
		GiveReview reviewbean = status(bean.getMovieName(),bean.getUserId());
		if (reviewbean != null) {
			throw new DuplicateRecordException("Already Given Review");
		}
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO givereview VALUES(?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
		    ps.setString(2, bean.getComment());
		    ps.setString(3, bean.getMovieName());
		    ps.setString(4, bean.getActorName());
		    ps.setString(5, bean.getCategory());
			ps.setLong(6, bean.getUserId());
			ps.setLong(7, bean.getStar());
			ps.executeUpdate();
			conn.commit();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
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
	public GiveReview findByPk(long pk) throws Exception {
		GiveReview bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
		//	PreparedStatement ps = conn.prepareStatement("SELECT * FROM movies WHERE id=?");
			PreparedStatement ps = conn.prepareStatement("SELECT movies.id,movieName,actorName,moviescategory.moviesCategoryName,image\r\n" + 
					"FROM movies\r\n" + 
					"INNER JOIN moviescategory ON movies.category = moviescategory.id WHERE movies.id=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new GiveReview();
				bean.setId(rs.getLong(1));
				bean.setMovieName(rs.getString(2));
				bean.setActorName(rs.getString(3));
				bean.setCategory(rs.getString(4));
				//bean.setRealeasedate(rs.getDate(5));
				bean.setImage(rs.getString(5));

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	public List list() throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		try {
			Connection conn = null;
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT givereview.id,comment,movie,actor,category,user.emailId,star\r\n" + 
					"FROM givereview INNER JOIN user ON givereview.userid = user.id");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				GiveReview bean = new GiveReview();
				bean.setId(rs.getLong(1));
				bean.setComment(rs.getString(2));
				bean.setMovieName(rs.getString(3));
				bean.setActorName(rs.getString(4));
				bean.setCategory(rs.getString(5));
				bean.setUserName(rs.getString(6));
				bean.setStar(rs.getLong(7));
				list.add(bean);
			}
		} catch (Exception e) {
                  e.printStackTrace();
		}
		
		return list;
	}
	
	public List Showlist(long userid) throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * from givereview where userid=?");
		ps.setLong(1, userid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			GiveReview bean = new GiveReview();
			bean.setId(rs.getLong(1));
			bean.setComment(rs.getString(2));
			bean.setMovieName(rs.getString(3));
			bean.setActorName(rs.getString(4));
			bean.setCategory(rs.getString(5));
			bean.setStar(rs.getLong(7));
			list.add(bean);
		}
		return list;
	}
	
	public static long delete(long id) {
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from givereview where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
}
