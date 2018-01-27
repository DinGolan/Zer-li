package entity;

/**
 * class CancelOrderItemRow for a row on the table of products 
 */
public class CancelOrderItemRow
{
	private int m_id;

	private String m_name;
	
	private int m_quantity;

	private double m_price;

	/**
	 * auto filed CancelOrderItemRow constructor
	 * @param m_id -int
	 * @param m_name-
	 * @param m_quantity
	 * @param m_price
	 */
	public CancelOrderItemRow(int m_id, String m_name, int m_quantity, double m_price) {
		super();
		this.m_id = m_id;
		this.m_name = m_name;
		this.m_price = m_price;
		this.m_quantity=m_quantity;
	}

	/**
	 * product id getter
	 * @return int- product id
	 */
	public int getM_id() {
		return m_id;
	}

	/**
	 * product id setter
	 * @param m_id- int
	 */
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	/**
	 * product name getter
	 * @return String - product name
	 */
	public String getM_name() {
		return m_name;
	}

	/**
	 * product name setter
	 * @param m_name- String
	 */
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	/**
	 * product quantity getter
	 * @return int quantity
	 */
	public int getM_quantity() {
		return m_quantity;
	}

	/**
	 * product quantity setter
	 * @param m_quantity- int
	 */
	public void setM_quantity(int m_quantity) {
		this.m_quantity = m_quantity;
	}

	/**
	 * product price getter
	 * @return double- product price
	 */
	public double getM_price() {
		return m_price;
	}

	/**
	 * product price setter
	 * @param m_price- double
	 */
	public void setM_price(double m_price) {
		this.m_price = m_price;
	}

}