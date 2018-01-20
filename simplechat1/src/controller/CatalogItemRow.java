package controller;

import java.io.InputStream;

import entity.Product;
import entity.Product.ProductColor;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CatalogItemRow
{


	private int m_id;

	private String m_name;

	private String m_type;

	private double m_price;

	private String m_domainColor;

	private Image m_image;


		public CatalogItemRow(int m_id, String m_name, String m_type, double m_price, String m_domainColor,InputStream is) {
		super();
		this.m_id = m_id;
		this.m_name = m_name;
		this.m_type = m_type;
		this.m_price = m_price;
		this.m_domainColor = m_domainColor;
		this.m_image = new Image(is);
	}

		/**
	 * @return Item id.
	 */
	public int getM_id()
	{
		return m_id;
	}

	/**
	 * @return String item id.
	 */
	public int getId()
	{
		return m_id;
	}

	/**
	 * @param m_id
	 *            Item id to set.
	 */
	public void setM_id(int m_id)
	{
		this.m_id = m_id;
	}

	/**
	 * @return Item name.
	 */
	public String getM_name()
	{
		return m_name;
	}

	/**
	 * @return Item name.
	 */
	public String getName()
	{
		return m_name.toString();
	}

	/**
	 * @param m_name
	 *            Item name to set.
	 */
	public void setM_name(String m_name)
	{
		this.m_name = m_name;
	}

	/**
	 * @return Item type.
	 */
	public String getM_type()
	{
		return m_type;
	}

	/**
	 * @return Item type.
	 */
	public String getType()
	{
		return m_type.toString();
	}

	/**
	 * @param m_type
	 *            Item type to set.
	 */
	public void setM_type(String m_type)
	{
		this.m_type = m_type;
	}

	/**
	 * @return Item price.
	 */
	public double getM_price()
	{
		return m_price;
	}

	/**
	 * @return Item string price.
	 */
	public String getPrice()
	{
		return String.valueOf(m_price);
	}

	/**
	 * @param m_price
	 *            Item price to set.
	 */
	public void setM_price(double m_price)
	{
		this.m_price = m_price;
	}

	/**
	 * @return Item domain color.
	 */
	public String getM_domainColor()
	{
		return m_domainColor;
	}

	/**
	 * @param m_domainColor
	 *            Item domain color to set.
	 */
	public void setM_domainColor(String m_domainColor)
	{
		this.m_domainColor = m_domainColor;
	}

	/**
	 * @return Item image.
	 */
	public Image getM_image()
	{
		return m_image;
	}

	/**
	 * @return Item image ImageView.
	 */
	public ImageView getImage()
	{
		return new ImageView(m_image);
	}


}