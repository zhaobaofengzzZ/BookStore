package zbf.bookstore.web.bookServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zbf.bookstore.domain.Book;
import zbf.bookstore.exception.BookException;
import zbf.bookstore.service.BookService;

public class FindProductByManyCondition extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String minprice = request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice");
		System.out.println(id);
		BookService service = new BookService();
		try {
			List<Book> books = service.findBooksByManyCondition(id,name,category,minprice,maxprice);
			request.setAttribute("books", books);
			request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
			
		} catch (BookException e) {
			e.printStackTrace();
			request.setAttribute("book_msg", e.getMessage());
			request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
			
		}
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
