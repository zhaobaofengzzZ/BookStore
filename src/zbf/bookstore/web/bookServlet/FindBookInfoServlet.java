package zbf.bookstore.web.bookServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zbf.bookstore.domain.Book;
import zbf.bookstore.exception.BookException;
import zbf.bookstore.service.BookService;

public class FindBookInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		BookService service = new BookService();
		try {
			Book book = service.findBookById(id);
			request.setAttribute("book", book);
			request.getRequestDispatcher("/product_info.jsp").forward(request, response);
			
		} catch (BookException e) {
			e.printStackTrace();
			request.setAttribute("book_msg", e.getMessage());
			request.getRequestDispatcher("/product_info.jsp").forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
