package zbf.bookstore.web.orderServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zbf.bookstore.domain.Order;
import zbf.bookstore.domain.User;
import zbf.bookstore.service.OrderService;

public class OrderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = (User)request.getSession().getAttribute("user");
		
		OrderService service = new OrderService();
		List<Order> orders = service.findOrdersByUserId(user.getId());
		
		request.setAttribute("orders", orders);
		request.setAttribute("count", orders.size());
		request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
