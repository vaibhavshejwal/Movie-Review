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
import in.co.movie.review.bean.UserBean;

public class UserModel {

	public Integer nextPk() throws Exception {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM USER");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}

	public UserBean findByLogin(String email) throws Exception {
		UserBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER WHERE emailId=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setUserName(rs.getString(2));
				bean.setEmailId(rs.getString(3));
				bean.setPassword(rs.getString(4));
				bean.setRepeatPassword(rs.getString(5));
				bean.setPhoneNo(rs.getLong(6));
				bean.setGender(rs.getString(7));
				bean.setRoleid(rs.getLong(8));
				bean.setRoleName(rs.getString(9));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}

	public UserBean findByPk(long pk) throws Exception {
		UserBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER WHERE id=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setUserName(rs.getString(2));
				bean.setEmailId(rs.getString(3));
				bean.setPassword(rs.getString(4));
				bean.setRepeatPassword(rs.getString(5));
				bean.setPhoneNo(rs.getLong(6));
				bean.setGender(rs.getString(7));
				bean.setRoleid(rs.getLong(8));
				bean.setRoleName(rs.getString(9));

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	public UserBean Authenticate(String Email, String Password) throws Exception {
		UserBean bean = null;
		Connection conn = null;

		conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER WHERE emailId =? AND password =?");
		ps.setString(1, Email);
		ps.setString(2, Password);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setUserName(rs.getString(2));
			bean.setEmailId(rs.getString(3));
			bean.setPassword(rs.getString(4));
			bean.setRepeatPassword(rs.getString(5));
			bean.setPhoneNo(rs.getLong(6));
			bean.setGender(rs.getString(7));
			bean.setRoleid(rs.getLong(8));
			bean.setRoleName(rs.getString(9));
		}
		return bean;
	}

public long add(UserBean bean) throws Exception {
	System.out.println("in add method");
	Connection conn = null;
	int pk = 0;

	UserModel model = new UserModel();
	UserBean existbean = findByLogin(bean.getEmailId());
	if (existbean != null) {
		throw new DuplicateRecordException("Email Id already exite");
	}

	try {
		conn = JDBCDataSource.getConnection();
		pk = nextPk();
		conn.setAutoCommit(false);
		PreparedStatement ps = conn.prepareStatement("INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?)");
		ps.setLong(1, pk);
	    ps.setString(2, bean.getUserName());
		ps.setString(3, bean.getEmailId());
		ps.setString(4, bean.getPassword());
		ps.setString(5, bean.getRepeatPassword());
		ps.setLong(6, bean.getPhoneNo());
		ps.setString(7, bean.getGender());
		ps.setLong(8, bean.getRoleid());
		ps.setString(9, bean.getRoleName());
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
	Connection conn = null;
	conn = JDBCDataSource.getConnection();
	PreparedStatement pstmt = conn.prepareStatement("SELECT * from user");
	ResultSet rs = pstmt.executeQuery();
	while (rs.next()) {
		UserBean bean = new UserBean();
		bean.setId(rs.getLong(1));
		bean.setUserName(rs.getString(2));
		bean.setEmailId(rs.getString(3));
		bean.setPassword(rs.getString(4));
		bean.setRepeatPassword(rs.getString(5));
		bean.setPhoneNo(rs.getLong(6));
		bean.setGender(rs.getString(7));
		bean.setRoleid(rs.getLong(8));
		bean.setRoleName(rs.getString(9));
		list.add(bean);
	}
	return list;
}
public static long delete(long id) {
	int i = 0;
	try {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement("DELETE from USER where id=?");
		stmt.setLong(1, id);
		i = stmt.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return i;
}

public long Update(UserBean bean) {
	System.out.println("in model update method");
	int pk = 0;
	try {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(
				"update USER set userName=?, emailId=?, password=?, repeatPassword=?, phoneNo=?,gender=?,roleid=?,roleName=? where id=?");
		    ps.setString(1, bean.getUserName());
			ps.setString(2, bean.getEmailId());
			ps.setString(3, bean.getPassword());
			ps.setString(4, bean.getRepeatPassword());
			ps.setLong(5, bean.getPhoneNo());
			ps.setString(6, bean.getGender());
			ps.setLong(7, bean.getRoleid());
			ps.setString(8, bean.getRoleName());
		    ps.setLong(9, bean.getId());
		    ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return pk;
}


public List search(UserBean bean) throws Exception {
	StringBuffer sql = new StringBuffer("SELECT * from USER WHERE 1=1");
	if (bean != null) {
		if (bean.getId() > 0) {
			sql.append(" AND id = " + bean.getId());
		}
		if (bean.getEmailId() != null && bean.getEmailId().length() > 0) {
			sql.append(" AND emailId like '" + bean.getEmailId() + "%'");
		}
	}

	ArrayList list = new ArrayList();
	Connection conn = null;
	try {
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setUserName(rs.getString(2));
			bean.setEmailId(rs.getString(3));
			bean.setPassword(rs.getString(4));
			bean.setRepeatPassword(rs.getString(5));
			bean.setPhoneNo(rs.getLong(6));
			bean.setGender(rs.getString(7));
			bean.setRoleid(rs.getLong(8));
			bean.setRoleName(rs.getString(9));
			list.add(bean);
		}
		rs.close();
	} catch (Exception e) {

	} finally {
		JDBCDataSource.closeconnection(conn);
	}

	return list;

}

}