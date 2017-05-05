package zbf.bookstore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import zbf.bookstore.domain.User;
import zbf.bookstore.exception.UserException;
import zbf.bookstore.utils.C3P0Util;

public class UserDao {
	private static QueryRunner qr =new QueryRunner(C3P0Util.getDataSource());
	/**
	 * 添加用户
	 * @param user
	 * @throws UserException
	 */
	public void addUser(User user) throws UserException{
		String sql = "insert into user (username,PASSWORD,gender,email,telephone,introduce,activecode,state,registtime) "
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		Object[] params = {user.getUsername(), user.getPassword(), user.getGender(), user.getEmail(), user.getTelephone(),
							user.getIntroduce(), user.getActiveCode(), user.getState(), user.getRegistTime()};
		try {
			qr.update(sql, params);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("注册失败");
		}
	}
	/**
	 * 通过激活码找到用户
	 * @param activeCode
	 * @return
	 * @throws UserException
	 */
	public User findUserByActiveCode(String activeCode) throws UserException{
		String sql = "select * from user where activeCode = ?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("激活失败");
		}
	}
	/**
	 * 通过激活码找到用户信息并修改用户激活状态
	 * @param activeCode
	 * @throws UserException
	 */
	public void setActiveState(String activeCode) throws UserException{
		String sql ="update user set state=1 where activeCode = ?";
		try {
			qr.update(sql, activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("激活失败！");
		}
		
	}
	/**
	 * 通过用户名和密码查找用户信息，用于登录
	 * @param username
	 * @param password
	 * @return
	 * @throws UserException
	 */
	public User findUserByNameAndPassword(String username, String password) throws UserException{
		String sql = "select * from user where username = ? and PASSWORD = ?";
		Object[] params = {username,password};
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("登陆失败，请验证用户名和密码是否证正确");
		}
	}
	/**
	 * 通过id查找用户信息
	 * @param id
	 * @return
	 * @throws UserException
	 */
	public User findUserById(String id) throws UserException{
		String sql = "select * from user where id=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("找不到该用户信息");
		}
		
	}
	public void modifyUser(User user) throws UserException{
		String sql = "UPDATE USER SET PASSWORD=?,gender=?,telephone=? WHERE id=?";
		Object[] params = {user.getPassword(),user.getGender(),user.getTelephone(),user.getId()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("修改用户信息失败！");
		}
	}
	
}
