package zbf.bookstore.web.bookServlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import zbf.bookstore.domain.Book;
import zbf.bookstore.exception.BookException;
import zbf.bookstore.service.BookService;

public class AddBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = new Book();
		BookService service = new BookService();
		
		
		try {
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			int pnum = Integer.parseInt(request.getParameter("pnum")); 
			String category = request.getParameter("category");
			String img_url = request.getParameter("upload");
			double price = Double.parseDouble(request.getParameter("price"));
			String description = request.getParameter("description");
			
			book.setName(name);
			book.setId(id);
			book.setPnum(pnum);
			book.setCategory(category);
			book.setImg_url(img_url);
			book.setPrice(price);
			book.setDescription(description);
			//System.out.println(book.toString());
			service.addBook(book);
			request.getRequestDispatcher("bookListServlet").forward(request, response);
		} catch (BookException e) {
			e.printStackTrace();
			request.setAttribute("book_msg", e.getMessage());
			request.getRequestDispatcher("/admin/products/edit.jsp").forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
