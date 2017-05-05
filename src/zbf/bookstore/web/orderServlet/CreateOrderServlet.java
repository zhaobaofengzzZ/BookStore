package zbf.bookstore.web.orderServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import zbf.bookstore.domain.Book;
import zbf.bookstore.domain.Order;
import zbf.bookstore.domain.OrderItem;
import zbf.bookstore.domain.User;
import zbf.bookstore.service.OrderService;

public class CreateOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*String money = request.getParameter("money");
		System.out.println(money);*/
		
		//1、封装order对象
		Order order = new Order();
		try {
			BeanUtils.populate(order, request.getParameterMap());
			order.setId(UUID.randomUUID().toString());
			order.setUser((User)request.getSession().getAttribute("user"));
			order.setPaystate(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//2、获取session对象中的cart数据,封装到OrderItem的集合中去
		Map<Book,String> cart = (Map<Book, String>)request.getSession().getAttribute("cart");
		ArrayList<OrderItem> list = new ArrayList<OrderItem>();
		for(Book b: cart.keySet()){
			OrderItem oi = new OrderItem();
			oi.setOrder(order);
			oi.setB(b);
			oi.setBuynum(Integer.parseInt(cart.get(b)));
			list.add(oi);
		}
		//3、将orderitem集合放入order对象中
		order.setOrderItems(list);
		
		OrderService service = new OrderService();
		service.addOrder(order);
		request.getRequestDispatcher("/pay.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
