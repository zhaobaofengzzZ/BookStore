package zbf.bookstore.web.bookServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zbf.bookstore.domain.PageBean;
import zbf.bookstore.exception.BookException;
import zbf.bookstore.service.BookService;

public class PageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService service = new BookService();
		
		int pageSize = 4;
		int currentPage = 1;
		String category = "全部";
		String page = request.getParameter("currentPage");
		String ctg = request.getParameter("category");
		if(page!=null){
			currentPage = Integer.parseInt(page);
		}
		if(ctg!=null){
			category = request.getParameter("category");
		}
		
		if(category!=null){
			request.getSession().setAttribute("category", category);
		}
		try {
			PageBean pb = service.findBooksPage(category,currentPage,pageSize);
			//System.out.println("pageservlet"+pb.getCount()+"\t"+pageSize+"\t"+pb.getTotalPage());
			request.setAttribute("pb", pb);
			request.getRequestDispatcher("/product_list.jsp").forward(request, response);
			
		} catch (BookException e) {
			e.printStackTrace();
			request.setAttribute("page_msg", e.getMessage());
			request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
