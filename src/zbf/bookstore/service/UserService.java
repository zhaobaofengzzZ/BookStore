package zbf.bookstore.service;

import zbf.bookstore.dao.UserDao;
import zbf.bookstore.domain.User;
import zbf.bookstore.exception.UserException;
import zbf.bookstore.utils.SendJMail;

public class UserService {
	private UserDao userdao = new UserDao(); 
	/**
	 * 用户注册
	 * @param user
	 * @throws UserException
	 */
	public void regist(User user) throws UserException{
		userdao.addUser(user);
		String emailMsg = "注册成功，请<a href='http://www.product.com/activeServlet?activeCode="+user.getActiveCode()+"'>激活</a>后登录";
		SendJMail.sendMail(user.getEmail(), emailMsg);
	}
	/**
	 * 用户激活
	 * @param activeCode
	 * @throws UserException
	 */
	public void activeUser(String activeCode) throws UserException{
		
		User user = userdao.findUserByActiveCode(activeCode);
		if(user != null){
			userdao.setActiveState(activeCode);
			return;
		}else{
		throw new UserException("激活失败！");
		}
	}
	/**
	 * 用户登陆
	 * @param username
	 * @param password
	 * @return
	 * @throws UserException 
	 */
	public User login(String username, String password) throws UserException{
		User user = userdao.findUserByNameAndPassword(username, password);
		if(user == null){
			throw new UserException("登陆失败，请验证用户名和密码是否证正确");
		}
		if( user.getState() == 0){
			throw new UserException("用户未激活");
		}
		return user;
	}
	/**
	 * 修改用户信息，先根据id找到用户，
	 * @param id
	 * @param telephone 
	 * @param gender 
	 * @param password 
	 * @throws UserException 
	 */
	public void modifyUser(String id, String password, String gender, String telephone) throws UserException {
		User user = userdao.findUserById(id);
		user.setPassword(password);
		user.setGender(gender);
		user.setTelephone(telephone);
		userdao.modifyUser(user);
		//System.out.println(user.toString());
	}

}
