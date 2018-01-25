package entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;

public class Order implements Serializable{

	private Date orderDate;
	
	private SupplyOption supply;
	
	private double orderTotalPrice;
	
	private HashMap<Product , Integer> productsInOrder;
	
	private HashMap<Product.ProductType,Integer> ProductInOrderType; 

	private LocalDate requiredSupplyDate;
	
	private int orderID;
	
	private String customerID;
	
	private int storeID;
	
	private String requiredSupplyTime;
	
	private String recipientAddress;
	
	private String recipientName;
	
	private String recipienPhoneNum;
	
	private String postCard;
	
	private double refund;
	
	private orderStatus oStatus;
	
	private Account.PaymentMethod paymentMethod;
	
	private Account.PaymentArrangement paymentArrangement;

	private static final double deliveryPrice = 20;
	
	public enum SupplyOption { DELIVERY , PICKUP }
	
	public enum orderStatus { APPROVED , CANCEL , RECIVED}
	
	public Order()
	{
		productsInOrder = new HashMap<Product, Integer>();
	}



	public Order(SupplyOption supply, double orderTotalPrice, HashMap<Product, Integer> productsInOrder,
			LocalDate requiredSupplyDate, String customerID, String requiredSupplyTime, String recipientAddress,
			String recipientName, String recipienPhoneNum, String postCard,  int storeID, Account.PaymentMethod paymentMethod, orderStatus oStatus, double refund) {
		super();
		this.supply = supply;
		this.orderDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		this.orderTotalPrice = orderTotalPrice;
		this.productsInOrder = productsInOrder;
		this.requiredSupplyDate = requiredSupplyDate;
		this.customerID = customerID;
		this.requiredSupplyTime = requiredSupplyTime;
		this.recipientAddress = recipientAddress;
		this.recipientName = recipientName;
		this.recipienPhoneNum = recipienPhoneNum;
		this.postCard = postCard;
		this.storeID = storeID;
		this.paymentMethod = paymentMethod;
		this.oStatus=oStatus;
		this.refund=refund;
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

	public HashMap<Product, Integer> getProductsInOrder() {
		return productsInOrder;
	}

	public void setProductsInOrder(HashMap<Product, Integer> productsInOrder) {
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

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public HashMap<Product.ProductType, Integer> getProductInOrderType() {
		return ProductInOrderType;
	}

	public void setProductInOrderType(HashMap<Product.ProductType, Integer> productInOrderType) {
		ProductInOrderType = productInOrderType;
	}

	@Override
	public String toString() {
		return "Order [orderDate=" + orderDate + ", supply=" + supply + ", orderTotalPrice=" + orderTotalPrice
				+ ", productsInOrder=" + productsInOrder + ", ProductInOrderType=" + ProductInOrderType
				+ ", requiredSupplyDate=" + requiredSupplyDate + ", orderID=" + orderID + ", customerID=" + customerID
				+ ", storeID=" + storeID + ", requiredSupplyTime=" + requiredSupplyTime + ", recipientAddress="
				+ recipientAddress + ", recipientName=" + recipientName + ", recipienPhoneNum=" + recipienPhoneNum
				+ ", postCard=" + postCard + ", refund=" + refund + ", oStatus=" + oStatus + ", paymentMethod="
				+ paymentMethod + ", paymentArrangement=" + paymentArrangement + "]";
	}

	public Account.PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Account.PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getRefund() {
		return refund;
	}

	public void setRefund(double refund) {
		this.refund = refund;
	}

	public orderStatus getoStatus() {
		return oStatus;
	}

	public void setoStatus(orderStatus oStatus) {
		this.oStatus = oStatus;
	}

	public Account.PaymentArrangement getPaymentArrangement() {
		return paymentArrangement;
	}

	public void setPaymentArrangement(Account.PaymentArrangement paymentArrangement) {
		this.paymentArrangement = paymentArrangement;
	}
}
