package zbf.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import zbf.bookstore.domain.Book;
import zbf.bookstore.domain.Order;
import zbf.bookstore.domain.OrderItem;
import zbf.bookstore.exception.BookException;
import zbf.bookstore.utils.C3P0Util;
import zbf.bookstore.utils.ManagerThreadLocal;

public class BookDao {
	
	private static QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
	/**
	 * 添加书籍
	 * @param book
	 * @throws BookException
	 */
	public void addBook(Book book) throws BookException{
		String sql = "INSERT INTO books (id,NAME,price,category,pnum,imgurl,description) VALUES (?,?,?,?,?,?,?)";
		Object[] params = {book.getId(),book.getName(),book.getPrice(),book.getCategory(),book.getPnum(),book.getImg_url(),book.getDescription()};
		
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookException("添加书籍失败！");
		}
	}
	public List<Book> findAllBooks() throws BookException {
		String sql = "select * from books";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookException("找不到书籍信息！");
		}
		
	}
	public Book findBookById(String id) throws BookException {
		String sql = "select * from books where id = ?";
		try {
			return qr.query(sql, new BeanHandler<Book>(Book.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookException("找不到该书籍信息！");
		}
	}
	public void deleteBookById(String id) throws BookException {
		String sql = "DELETE FROM books WHERE id=?";
		try {
			qr.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookException("删除书籍信息失败！");
		}
	}

	public void deleteSelectedBooks(String ids) throws BookException {
		String sql = "DELETE FROM books WHERE "+ids;
		//System.out.println(sql);
		try {
			qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookException("删除失败！");
		}
		
	}
	public List<Book> findBooksByManyCondition(String id, String name,
			String category, String minprice, String maxprice) throws BookException {
		String sql = "select * from books where 1=1";
		List<String> list = new ArrayList<String>();
		if(!"".equals(id.trim())){
			sql+=" and id like ?"; 
			list.add("%"+id.trim()+"%");
		}  //sql = "select * from bookshop where 1=1 and id like '%1001%'"
		
		if(!"".equals(category.trim())){
			sql+=" and category=?";
			list.add(category.trim());
		}
		
		if(!"".equals(name.trim())){
			sql+=" and name like ?";
			list.add("%"+name.trim()+"%");
		}
		
		if(!"".equals(minprice.trim())){
			sql+=" and price>?";
			list.add(minprice.trim());
		}
		if(!"".equals(maxprice.trim())){
			sql+=" and price< ?";
			list.add(maxprice.trim());
		}
		System.out.println(sql);
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class), list.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookException("查询失败！");
		}
		
		
		
	}
	public int count(String category) throws BookException {
		String sql = "SELECT COUNT(*) FROM books";
		
		if(!"全部".equals(category)){
			sql += " WHERE category='"+category+"'";
		}
		//System.out.println(sql);
		try {
			long l = (Long)qr.query(sql, new ScalarHandler(1));
			return (int)l;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookException("未找到书籍信息");
		}
		
	}
	public List<Book> findBooks(String category, int currentPage, int pageSize) throws BookException {
		String sql = "";
		//System.out.println(category);
		if("全部".equals(category)){
			sql = "SELECT * FROM books LIMIT ?,?";
		}else{
			sql = "SELECT * FROM books WHERE category='"+category+"' LIMIT ?,?";
		}
		Object[] params = {(currentPage-1)*pageSize,pageSize};
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookException("未找到书籍信息！");
		}
	}
	//修改商品数量
		public void updateBookNum(Order order) throws SQLException{
			List<OrderItem> orderItems = order.getOrderItems();
			QueryRunner qr = new QueryRunner();
			
			Object[][] params = new Object[orderItems.size()][];
			for (int i = 0; i < params.length; i++) {
				params[i] = new Object[]{orderItems.get(i).getBuynum(),orderItems.get(i).getB().getId()};
			}
			qr.batch(ManagerThreadLocal.getConnection(),"UPDATE books SET pnum=pnum-? WHERE id=?", params);
		}
	

}
