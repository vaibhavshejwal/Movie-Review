package in.co.movie.review.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.movie.review.Exception.ApplicationException;
import in.co.movie.review.Exception.DuplicateRecordException;
import in.co.movie.review.Utility.JDBCDataSource;
import in.co.movie.review.bean.GiveReview;
import in.co.movie.review.bean.MoviesBean;
import in.co.movie.review.bean.MoviesCategoryBean;
import in.co.movie.review.bean.UserBean;

public class MoviesModel {

	public Integer nextPk() throws Exception {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM movies");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}
	
	public MoviesBean findByMovieName(String movieName) throws Exception {
		MoviesBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM movies WHERE movieName=?");
			ps.setString(1, movieName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new MoviesBean();
				bean.setId(rs.getLong(1));
				bean.setMovieName(rs.getString(2));
				bean.setActorName(rs.getString(3));
				bean.setCategory(rs.getString(4));
				bean.setRealeasedate(rs.getDate(5));
				bean.setImage(rs.getString(6));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}
	
	
	
	
	public long add(MoviesBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;
		
		MoviesModel model = new MoviesModel();
		MoviesBean moviebean = findByMovieName(bean.getMovieName());
		if (moviebean != null) {
			throw new DuplicateRecordException("Movie is already exite");
		}
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO movies VALUES(?,?,?,?,?,?)");
			ps.setLong(1, pk);
		    ps.setString(2, bean.getMovieName());
		    ps.setString(3, bean.getActorName());
		    ps.setLong(4, bean.getCategoryId());
			ps.setDate(5, new Date(bean.getRealeasedate().getTime()));
			ps.setString(6, bean.getImage());
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

	public List list() throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		try {
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT movies.id,movieName,actorName,moviescategory.moviesCategoryName,relasedate,image\r\n" + 
				"FROM movies\r\n" + 
				"INNER JOIN moviescategory ON movies.category = moviescategory.id");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			MoviesBean bean = new MoviesBean();
			bean.setId(rs.getLong(1));
			bean.setMovieName(rs.getString(2));
			bean.setActorName(rs.getString(3));
			bean.setCategory(rs.getString(4));
			bean.setRealeasedate(rs.getDate(5));
			bean.setImage(rs.getString(6));
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
			PreparedStatement stmt = conn.prepareStatement("DELETE from movies where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public List search(MoviesBean bean) throws Exception {
		StringBuffer sql = new StringBuffer("SELECT movies.id,movieName,actorName,moviescategory.moviesCategoryName,relasedate,image FROM movies INNER JOIN moviescategory ON movies.category = moviescategory.id WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getMovieName() != null && bean.getMovieName().length() > 0) {
				sql.append(" AND movieName like '" + bean.getMovieName() + "%'");
			}
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MoviesBean();
				bean.setId(rs.getLong(1));
				bean.setMovieName(rs.getString(2));
				bean.setActorName(rs.getString(3));
				bean.setCategory(rs.getString(4));
				bean.setRealeasedate(rs.getDate(5));
				bean.setImage(rs.getString(6));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
          e.printStackTrace();
		} finally {
			JDBCDataSource.closeconnection(conn);
		}

		return list;

	}
	
}
