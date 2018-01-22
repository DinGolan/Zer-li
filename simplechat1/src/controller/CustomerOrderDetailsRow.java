package controller;

import java.sql.Date;

import entity.Account;
import entity.Account.PaymentMethod;
import entity.Order;
import entity.Order.SupplyOption;
import entity.Order.orderStatus;

public class CustomerOrderDetailsRow 
{
	private int Order_ID;
	
	private int Store_ID;
	
	private String Order_Address;
	
	private Date Order_Date;
	
	private Account.PaymentMethod Order_PayMent;
	
	private Order.orderStatus Order_Status;
	 
	private Order.SupplyOption Supply;
	 
	private double Refund;
	
	private double Total_Price;
	
	public CustomerOrderDetailsRow()
	{
	
	}

	public CustomerOrderDetailsRow(int order_ID, int store_ID, String order_Address, Date order_Date,
			PaymentMethod order_PayMent, orderStatus order_Status, SupplyOption supply, double refund,
			double total_Price) {
		super();
		Order_ID = order_ID;
		Store_ID = store_ID;
		Order_Address = order_Address;
		Order_Date = order_Date;
		Order_PayMent = order_PayMent;
		Order_Status = order_Status;
		Supply = supply;
		Refund = refund;
		Total_Price = total_Price;
	}

	public int getOrder_ID() {
		return Order_ID;
	}

	public void setOrder_ID(int order_ID) {
		Order_ID = order_ID;
	}

	public int getStore_ID() {
		return Store_ID;
	}

	public void setStore_ID(int store_ID) {
		Store_ID = store_ID;
	}

	public String getOrder_Address() {
		return Order_Address;
	}

	public void setOrder_Address(String order_Address) {
		Order_Address = order_Address;
	}

	public Date getOrder_Date() {
		return Order_Date;
	}

	public void setOrder_Date(Date order_Date) {
		Order_Date = order_Date;
	}

	public Account.PaymentMethod getOrder_PayMent() {
		return Order_PayMent;
	}

	public void setOrder_PayMent(Account.PaymentMethod order_PayMent) {
		Order_PayMent = order_PayMent;
	}

	public Order.orderStatus getOrder_Status() {
		return Order_Status;
	}

	public void setOrder_Status(Order.orderStatus order_Status) {
		Order_Status = order_Status;
	}

	public Order.SupplyOption getSupply() {
		return Supply;
	}

	public void setSupply(Order.SupplyOption supply) {
		Supply = supply;
	}

	public double getRefund() {
		return Refund;
	}

	public void setRefund(double refund) {
		Refund = refund;
	}

	public double getTotal_Price() {
		return Total_Price;
	}

	public void setTotal_Price(double total_Price) {
		Total_Price = total_Price;
	}

	@Override
	public String toString() {
		return "CustomerOrderDetailsRow [Order_ID=" + Order_ID + ", Store_ID=" + Store_ID + ", Order_Address="
				+ Order_Address + ", Order_Date=" + Order_Date + ", Order_PayMent=" + Order_PayMent + ", Order_Status="
				+ Order_Status + ", Supply=" + Supply + ", Refund=" + Refund + ", Total_Price=" + Total_Price + "]";
	}
}
