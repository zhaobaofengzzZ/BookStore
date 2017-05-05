package zbf.bookstore.web.orderServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zbf.bookstore.domain.Book;
import zbf.bookstore.exception.BookException;
import zbf.bookstore.service.BookService;

public class AddCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String id = request.getParameter("id");
		BookService service = new BookService();
		try {
			Book book = service.findBookById(id);
			HttpSession session = request.getSession();
			Map<Book,String> cart = (Map<Book, String>)session.getAttribute("cart");
			if(cart == null){
				cart = new HashMap<Book, String>();
			}
			if(cart.containsKey(book)){
				cart.put(book, Integer.parseInt(cart.get(book))+1+"");
			}else{
				cart.put(book, 1+"");
			}
			session.setAttribute("cart", cart);
			session.setMaxInactiveInterval(Integer.MAX_VALUE);
			pw.print("<a href='"+request.getContextPath()+"/servlet/pageServlet'>继续购物</a>，<a href='"
					+request.getContextPath()+"/cart.jsp'>查看购物车</a>");
			
		} catch (BookException e) {
			request.setAttribute("book_msg", e.getMessage());
			request.getRequestDispatcher("/product_info.jsp").forward(request, response);
			
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
