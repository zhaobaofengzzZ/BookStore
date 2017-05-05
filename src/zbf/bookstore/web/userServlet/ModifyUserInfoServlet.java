package zbf.bookstore.web.userServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zbf.bookstore.exception.UserException;
import zbf.bookstore.service.UserService;

public class ModifyUserInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = new UserService();
		String id = request.getParameter("id");
		//System.out.println(id);
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String telephone = request.getParameter("telephone");
		try {
			service.modifyUser(id,password,gender,telephone);
			request.getSession().invalidate();//用户注销
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		} catch (UserException e) {
			e.printStackTrace();
			request.setAttribute("user_msg", e.getMessage());
			//response.sendRedirect(request.getContextPath()+"/login.jsp");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
