package zbf.bookstore.service;

import java.util.List;

import zbf.bookstore.dao.BookDao;
import zbf.bookstore.domain.Book;
import zbf.bookstore.domain.PageBean;
import zbf.bookstore.exception.BookException;

public class BookService {
	
	private BookDao bookdao = new BookDao();
	
	public void addBook(Book book) throws BookException{
		bookdao.addBook(book);
	}
	public List<Book> findAllBooks() throws BookException{
		return bookdao.findAllBooks();
	}
	public Book findBookById(String id) throws BookException {
		return bookdao.findBookById(id);
	}
	public void deleteBookById(String id) throws BookException {
		bookdao.deleteBookById(id);
	}
	public void deleteSelectedBooks(String ids) throws BookException {
		bookdao.deleteSelectedBooks(ids);
	}
	public List<Book> findBooksByManyCondition(String id, String name,
			String category, String minprice, String maxprice) throws BookException {
		// TODO Auto-generated method stub
		return bookdao.findBooksByManyCondition(id,name,category,minprice,maxprice);
	}
	public PageBean findBooksPage(String category, int currentPage, int pageSize) throws BookException {
		int count = bookdao.count(category);
		int totalPage = (int) Math.ceil(count*1.0/pageSize);  
		
		
		List<Book> books = bookdao.findBooks(category,currentPage, pageSize);
		//System.out.println("bookservice:"+count+"\t"+pageSize+"\t"+totalPage);
		PageBean pb = new PageBean(currentPage, pageSize,  count, totalPage, books);
		
		return pb;
	}
	

}
