package zbf.bookstore.web.orderServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zbf.bookstore.domain.User;
/**
 * 结账生成订单
 * @author zhaobaofeng
 *
 */
public class SetCountServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			//response.sendRedirect(request.getContextPath()+"/login.jsp");
			request.setAttribute("user_msg", "您还没登陆，请登陆后再结账！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect(request.getContextPath()+"/order.jsp");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
