package entity;

import java.io.Serializable;

/**
 *class for the store entity- variables: store id, address, amount of orders and constructor
 */
public class Store implements Serializable {
	
	private int StoreId;
	private String Store_Address;
	private int QuantityOfOrders;
	
	/**
	 * empty constructor
	 */
	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * auto filled constructor
	 */
	public Store(int storeId, String store_Address, int quantityOfOrders) {
		super();
		StoreId = storeId;
		Store_Address = store_Address;
		QuantityOfOrders = quantityOfOrders;
	}
	
	/**
	 * store id getter
	 * @return int- store number
	 */
	public int getStoreId() {
		return StoreId;
	}
	/**
	 * store id setter
	 * @param storeId- int
	 */
	public void setStoreId(int storeId) {
		StoreId = storeId;
	}
	
	/**
	 * store address getter
	 * @return String- store address
	 */
	public String getStore_Address() {
		return Store_Address;
	}
	
	/**
	 * store address setter
	 * @param store_Address- String
	 */
	public void setStore_Address(String store_Address) {
		Store_Address = store_Address;
	}
	
	/**
	 * store amount of orders getter
	 * @return int- amount
	 */
	public int getQuantityOfOrders() {
		return QuantityOfOrders;
	}

	/**
	 * store amount of orders setter
	 * @param quantityOfOrders- int
	 */
	public void setQuantityOfOrders(int quantityOfOrders) {
		QuantityOfOrders = quantityOfOrders;
	}
	
	/**
	 * to string override method
	 */
	@Override
	public String toString() {
		return "Store [StoreId=" + StoreId + ", Store_Address=" + Store_Address + ", QuantityOfOrders="
				+ QuantityOfOrders + "]";
	}

}