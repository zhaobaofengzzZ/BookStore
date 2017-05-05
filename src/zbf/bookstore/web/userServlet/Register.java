package zbf.bookstore.web.userServlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import zbf.bookstore.domain.User;
import zbf.bookstore.exception.UserException;
import zbf.bookstore.service.UserService;

public class Register extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//验证码校验  和 两次输入的密码是否相同
		String ckcode = request.getParameter("ckcode");			//表单提交的输入验证码
		String imgCode = (String) request.getSession().getAttribute("imgCode");	//放在session域里的图片验证码里的字符串
		
		//System.out.println(ckcode+"&&"+imgCode);
		
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		
		if( !ckcode.equalsIgnoreCase(imgCode)){
			request.setAttribute("ckcode_msg", "验证码错误");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		if( !password.equals(repassword)){
			request.setAttribute("ckpwd_msg", "两次输入的密码不相同，请重新注册！");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		//接受表单数据
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			user.setActiveCode(UUID.randomUUID().toString());//手动设置激活码
			UserService service = new UserService();
			service.regist(user);
			request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);
			
		} catch (UserException e){
			request.setAttribute("user_msg", e.getMessage());
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			
		}catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		
		
		
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
