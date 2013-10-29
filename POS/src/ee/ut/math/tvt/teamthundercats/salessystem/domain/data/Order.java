package ee.ut.math.tvt.teamthundercats.salessystem.domain.data;

import java.util.Date;
public class Order implements Cloneable, DisplayableItem {
	private int orderId;
	private Date dateTime;
	private double totalPrice;
	
	public Order (int id, double tPrice){
		this.orderId=id;
		this.dateTime = new Date();
		this.totalPrice = tPrice;
		
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
