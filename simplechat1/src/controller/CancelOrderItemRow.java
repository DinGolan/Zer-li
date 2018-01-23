package controller;

public class CancelOrderItemRow
{
	private int m_id;

	private String m_name;
	
	private int m_quantity;

	private double m_price;

	public CancelOrderItemRow(int m_id, String m_name, int m_quantity, double m_price) {
		super();
		this.m_id = m_id;
		this.m_name = m_name;
		this.m_price = m_price;
		this.m_quantity=m_quantity;
	}

	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public int getM_quantity() {
		return m_quantity;
	}

	public void setM_quantity(int m_quantity) {
		this.m_quantity = m_quantity;
	}

	public double getM_price() {
		return m_price;
	}

	public void setM_price(double m_price) {
		this.m_price = m_price;
	}

}