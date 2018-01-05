package entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements Serializable {

	private Date orderDate;
	
	private SupplyOption supply;
	
	private double orderTotalPrice;
	
	private ArrayList<Product> productsInOrder;
	
	private LocalDate requiredSupplyDate;
	
	private int orderID;
	
	private String customerID;
	
	private String requiredSupplyTime;
	
	private String recipientAddress;
	
	private String recipientName;
	
	private String recipienPhoneNum;
	
	private String postCard;
	
	private int StoreId;

	private static final double deliveryPrice = 20;
	
	public enum SupplyOption { DELIVERY , PICKUP }
	
	public Order()
	{
		productsInOrder = new ArrayList<Product>();
	}

	public Order(SupplyOption supply, double orderTotalPrice, ArrayList<Product> productsInOrder,
			LocalDate requiredSupplyDate, String customerID, String requiredSupplyTime, String recipientAddress,
			String recipientName, String recipienPhoneNum, String postCard) {
		super();
		this.supply = supply;
		this.orderTotalPrice = orderTotalPrice;
		this.productsInOrder = productsInOrder;
		this.requiredSupplyDate = requiredSupplyDate;
		this.customerID = customerID;
		this.requiredSupplyTime = requiredSupplyTime;
		this.recipientAddress = recipientAddress;
		this.recipientName = recipientName;
		this.recipienPhoneNum = recipienPhoneNum;
		this.postCard = postCard;
	}
	
	public static double getDeliveryprice() {
		return deliveryPrice;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public SupplyOption getSupply() {
		return supply;
	}

	public void setSupply(SupplyOption supply) {
		this.supply = supply;
	}

	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public ArrayList<Product> getProductsInOrder() {
		return productsInOrder;
	}

	public void setProductsInOrder(ArrayList<Product> productsInOrder) {
		this.productsInOrder = productsInOrder;
	}

	public LocalDate getRequiredSupplyDate() {
		return requiredSupplyDate;
	}

	public void setRequiredSupplyDate(LocalDate requiredSupplyDate) {
		this.requiredSupplyDate = requiredSupplyDate;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public static double getDeliveryPrice() {
		return deliveryPrice;
	}
	
	public String getPostCard() {
		return postCard;
	}

	public void setPostCard(String postCard) {
		this.postCard = postCard;
	}

	public String getRequiredSupplyTime() {
		return requiredSupplyTime;
	}

	public void setRequiredSupplyTime(String requiredSupplyTime) {
		this.requiredSupplyTime = requiredSupplyTime;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipienPhoneNum() {
		return recipienPhoneNum;
	}

	public void setRecipienPhoneNum(String recipienPhoneNum) {
		this.recipienPhoneNum = recipienPhoneNum;
	}
	
	public int getStoreId() {
		return StoreId;
	}

	public void setStoreId(int storeId) {
		StoreId = storeId;
	}
}
