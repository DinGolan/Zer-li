package entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;

/**
 * entity of order
 *
 */
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
	
	/**
	 * enum for options of Supply (delivery, pickup)
	 */
	public enum SupplyOption { DELIVERY , PICKUP }
	
	/**
	 * enum for options of order status (approved , cancel , recived)
	 */
	public enum orderStatus { APPROVED , CANCEL , RECIVED}
	
	/**
	 * constructor to initialize hashMap od product in order
	 */
	public Order()
	{
		productsInOrder = new HashMap<Product, Integer>();
	}


	/**
	 * constructor to set all fields 
	 * @param supply - order supply option
	 * @param orderTotalPrice - order total price
	 * @param productsInOrder - hashMap of products in order
	 * @param requiredSupplyDate - required supply date
	 * @param customerID - customer Id
	 * @param requiredSupplyTime - required supply time
	 * @param recipientAddress - recipient address
	 * @param recipientName - recipient name
	 * @param recipienPhoneNum - recipient phone number
	 * @param postCard - post card 
	 * @param storeID - store Id
	 * @param paymentMethod - order payment method
	 * @param oStatus - order status
	 * @param refund - refund in case of cancellation
	 */
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
	
	/**
	 * @return order date
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return order supply option
	 */
	public SupplyOption getSupply() {
		return supply;
	}

	/**
	 * @param supply option of order to set
	 */
	public void setSupply(SupplyOption supply) {
		this.supply = supply;
	}

	/**
	 * @return order total price
	 */
	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	/**
	 * @param orderTotalPrice - order total price to set
	 */
	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	/**
	 * @return hashMap of product in order and their amount
	 */
	public HashMap<Product, Integer> getProductsInOrder() {
		return productsInOrder;
	}

	/**
	 * @param productsInOrder- hashMap of product in order and their amount to set
	 */
	public void setProductsInOrder(HashMap<Product, Integer> productsInOrder) {
		this.productsInOrder = productsInOrder;
	}

	/**
	 * @return Required Supply Date
	 */
	public LocalDate getRequiredSupplyDate() {
		return requiredSupplyDate;
	}

	/**
	 * @param requiredSupplyDate - Required Supply Date to set
	 */
	public void setRequiredSupplyDate(LocalDate requiredSupplyDate) {
		this.requiredSupplyDate = requiredSupplyDate;
	}

	/**
	 * @return - order Id
	 */
	public int getOrderID() {
		return orderID;
	}

	/**
	 * @param orderID to set
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	/**
	 * @return customer Id
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/**
	 * @return delivery price, final variable = 20
	 */
	public static double getDeliveryPrice() {
		return deliveryPrice;
	}
	
	/**
	 * @return order post card
	 */
	public String getPostCard() {
		return postCard;
	}

	/**
	 * @param postCard to set
	 */
	public void setPostCard(String postCard) {
		this.postCard = postCard;
	}

	/**
	 * @return required Supply Time
	 */
	public String getRequiredSupplyTime() {
		return requiredSupplyTime;
	}

	/**
	 * @param requiredSupplyTime - required Supply Time to set
	 */
	public void setRequiredSupplyTime(String requiredSupplyTime) {
		this.requiredSupplyTime = requiredSupplyTime;
	}

	/**
	 * @return recipient Address
	 */
	public String getRecipientAddress() {
		return recipientAddress;
	}

	/**
	 * @param recipientAddress - recipient Address to set
	 */
	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	/**
	 * @return recipient Name
	 */
	public String getRecipientName() {
		return recipientName;
	}

	/**
	 * @param recipientName - recipient Name to set
	 */
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	/**
	 * @return recipient phone Number
	 */
	public String getRecipienPhoneNum() {
		return recipienPhoneNum;
	}

	/**
	 * @param recipienPhoneNum - recipient phone Number to set
	 */
	public void setRecipienPhoneNum(String recipienPhoneNum) {
		this.recipienPhoneNum = recipienPhoneNum;
	}

	/**
	 * @return storeID
	 */
	public int getStoreID() {
		return storeID;
	}

	/**
	 * @param storeID to set
	 */
	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	/**
	 * @return hashMap of product type in order and their amount
	 */
	public HashMap<Product.ProductType, Integer> getProductInOrderType() {
		return ProductInOrderType;
	}

	/**
	 * @param productInOrderType - hashMap of product type in order and their amount to set
	 */
	public void setProductInOrderType(HashMap<Product.ProductType, Integer> productInOrderType) {
		ProductInOrderType = productInOrderType;
	}

	/**
	 * to string - return string of all fields of order
	 */
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

	/**
	 * @return payment Method
	 */
	public Account.PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod - payment Method to set
	 */
	public void setPaymentMethod(Account.PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return order refund
	 */
	public double getRefund() {
		return refund;
	}

	/**
	 * @param refund to set
	 */
	public void setRefund(double refund) {
		this.refund = refund;
	}

	/**
	 * @return order status
	 */
	public orderStatus getoStatus() {
		return oStatus;
	}

	/**
	 * @param oStatus - order status to set
	 */
	public void setoStatus(orderStatus oStatus) {
		this.oStatus = oStatus;
	}

	/**
	 * @return payment Arrangement
	 */
	public Account.PaymentArrangement getPaymentArrangement() {
		return paymentArrangement;
	}

	/**
	 * @param paymentArrangement - payment Arrangement to set
	 */
	public void setPaymentArrangement(Account.PaymentArrangement paymentArrangement) {
		this.paymentArrangement = paymentArrangement;
	}
}
