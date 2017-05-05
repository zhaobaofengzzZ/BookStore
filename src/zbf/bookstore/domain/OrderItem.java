package zbf.bookstore.domain;

public class OrderItem {
	private Order order;//订单
	private Book b; //商品
	private int buynum;//购物数量
	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	/**
	 * @return the b
	 */
	public Book getB() {
		return b;
	}
	/**
	 * @param b the b to set
	 */
	public void setB(Book b) {
		this.b = b;
	}
	/**
	 * @return the buynum
	 */
	public int getBuynum() {
		return buynum;
	}
	/**
	 * @param buynum the buynum to set
	 */
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	
	
	

}
