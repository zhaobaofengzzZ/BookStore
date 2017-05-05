package zbf.bookstore.web.userServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zbf.bookstore.domain.User;
import zbf.bookstore.exception.UserException;
import zbf.bookstore.service.UserService;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String imgCode = (String) request.getSession().getAttribute("imgCode");
		String ckCode = request.getParameter("ckcode");
		//System.out.println(imgCode+"\t"+ckCode);
		if(!ckCode.equalsIgnoreCase(imgCode)){
			request.setAttribute("ckcode_msg", "验证码错误，请重新登陆！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserService service = new UserService();
		try {
			String path="/index.jsp";
			User user = service.login(username, password);
			if("admin".equals(user.getRole())){
				path="/admin/login/home.jsp";
			}
			request.getSession().setAttribute("user", user);
			//request.getRequestDispatcher(path).forward(request, response);
			response.sendRedirect(request.getContextPath()+path);
			
		} catch (UserException e) {
			e.printStackTrace();
			request.setAttribute("user_msg", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
