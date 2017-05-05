package zbf.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import zbf.bookstore.dao.BookDao;
import zbf.bookstore.dao.OrderDao;
import zbf.bookstore.dao.OrderItemDao;
import zbf.bookstore.domain.Order;
import zbf.bookstore.utils.ManagerThreadLocal;

public class OrderService {

	OrderDao orderDao = new OrderDao();
	OrderItemDao orderItemDao = new OrderItemDao();
	BookDao bookdao = new BookDao();
	
	public void addOrder(Order order){
		try {
			ManagerThreadLocal.startTransacation();
			orderDao.addOrder(order);
			orderItemDao.addOrderItem(order);
			bookdao.updateBookNum(order);
			
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			ManagerThreadLocal.rollback();
		}
	}

	public List<Order> findOrdersByUserId(int id) {
		try {
			return orderDao.findOrders(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Order findOrdersByOrderId(String orderid) {
		try {
			return orderDao.findOrdersByOrderId(orderid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void pay(String orderid){
		try {
			orderDao.changePayState(orderid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
