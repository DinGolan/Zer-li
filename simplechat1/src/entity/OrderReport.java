package entity;

import java.io.Serializable;
import java.util.HashMap;

public class OrderReport extends Report implements Serializable {
	
	private HashMap<Order,Product> orderReort = new HashMap<Order,Product>();

	public OrderReport(String qaurterReport, int serialNumberReport, int storeId, HashMap<Order, Product> orderReort) {
		super(qaurterReport, serialNumberReport, storeId);
		this.orderReort = orderReort;
	}
	
	public HashMap<Order, Product> getOrderReort() {
		return orderReort;
	}

	public void setOrderReort(HashMap<Order, Product> orderReort) {
		this.orderReort = orderReort;
	}
	
	@Override
	public String toString() {
		return "OrderReport [orderReort=" + orderReort + "]";
	}
}
