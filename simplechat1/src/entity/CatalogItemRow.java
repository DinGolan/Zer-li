package entity;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * entity of catalog item row (for table view)
 *
 */
public class CatalogItemRow
{


	private int m_id;

	private String m_name;

	private String m_type;

	private double m_price;

	private String m_domainColor;

	private Image m_image;

	/**
	 * constructor for row of item in Catalog
	 * @param m_id - product Id
	 * @param m_name - product name
	 * @param m_type - product type
	 * @param m_price - product price
	 * @param m_domainColor - product domain color
	 * @param is - product inputstream for picture
	 */
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
	 * @return product Id
	 */
	public int getM_id()
	{
		return m_id;
	}

	/**
	 * @return product Id for row.
	 */
	public int getId()
	{
		return m_id;
	}

	/**
	 * @param m_id - get product Id to set
	 */
	public void setM_id(int m_id)
	{
		this.m_id = m_id;
	}

	/**
	 * @return product name.
	 */
	public String getM_name()
	{
		return m_name;
	}

	/**
	 * @return product name for row.
	 */
	public String getName()
	{
		return m_name.toString();
	}

	/**
	 * @param m_name - get product name to set
	 */
	public void setM_name(String m_name)
	{
		this.m_name = m_name;
	}

	/**
	 * @return product type.
	 */
	public String getM_type()
	{
		return m_type;
	}

	/**
	 * @return product type for row.
	 */
	public String getType()
	{
		return m_type.toString();
	}

	/**
	 * @param m_type - product type to set
	 */
	public void setM_type(String m_type)
	{
		this.m_type = m_type;
	}

	/**
	 * @return product price
	 */
	public double getM_price()
	{
		return m_price;
	}

	/**
	 * @return product price for row.
	 */
	public String getPrice()
	{
		return String.valueOf(m_price);
	}

	/**
	 * @param m_price-  product price to set
	 */
	public void setM_price(double m_price)
	{
		this.m_price = m_price;
	}

	/**
	 * @return product domain color.
	 */
	public String getM_domainColor()
	{
		return m_domainColor;
	}

	/**
	 * @param m_domainColor - product domain color to set
	 */
	public void setM_domainColor(String m_domainColor)
	{
		this.m_domainColor = m_domainColor;
	}

	/**
	 * @return product image.
	 */
	public Image getM_image()
	{
		return m_image;
	}

	/**
	 * @return product image ImageView for row
	 */
	public ImageView getImage()
	{
		return new ImageView(m_image);
	}


}