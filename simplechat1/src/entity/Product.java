package entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;

import javafx.scene.image.Image;

/**
 * entity of Product
 *
 */
public class Product implements Serializable,Comparable<Product>
{
	private int pID;
	private String pName;
	private ProductType pType;	
	private ProductColor pColor;
	private int pStore;	
	private double pPrice;	
	private double pSalePrice;	
	private transient InputStream input;
	private transient Image image;
	private byte[] buffer;
	private int Quantity;

	/**
	 * enum for options of Product Color (red, pink , orange, white, yellow)
	 */
	public enum ProductColor {RED, PINK, ORANGE, WHITE, YELLOW}
	
	/**
	 * enum for options of Product Type (bouquet , arrangement , sweet bouquet , flower crown , bridal bouquet , vase , wreath flowers)
	 */
	public enum ProductType {BOUQUET , ARRANGEMENT , SWEET_BOUQUET , FLOWER_CROWN , BRIDAL_BOUQUET , VASE , WREATH_FLOWERS}
	
	
	/**
	 * default constructor
	 */
	public Product() {
		super();
	}

	/**
	 * @return product Id
	 */
	public int getpID() {
		return pID;
	}
	
	/**
	 * @param pID - product Id to set
	 */
	public void setpID(int pID) {
		this.pID = pID;
	}

	/**
	 * @return product name
	 */
	public String getpName() {
		return pName;
	}

	/**
	 * @param pName - product name to set
	 */
	public void setpName(String pName) {
		this.pName = pName;
	}

	/**
	 * @return product type
	 */
	public ProductType getpType() {
		return pType;
	}

	/**
	 * @param pType - product type to set
	 */
	public void setpType(ProductType pType) {
		this.pType = pType;
	}
	
	/**
	 * return string of product Id, product name and product type
	 */
	public String toString(){
		return String.format("\nProduct: %s\t %s\t %s\n",pID,pName,pType);
	}

	/**
	 * @return product price
	 */
	public double getpPrice() {
		return pPrice;
	}

	/**
	 * @param pPrice - product price to set
	 */
	public void setpPrice(double pPrice) {
		this.pPrice = pPrice;
	}

	/**
	 * @return product image inputstream
	 */
	public InputStream getInput() {
		return input;
	}

	/**
	 * @param input - product image inputstream to set
	 */
	public void setInput(InputStream input) {
		this.input = input;
	}	

	/**
	 * @param inputStream - set image of product
	 */
	public void setImage(InputStream inputStream)
	{
		this.input = inputStream;
		try {
			buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @return product picture
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * @return byte array of image
	 */
	public byte[] getByteArray() {
		return buffer;
	}

	/**
	 * equals to products by values
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (Quantity != other.Quantity)
			return false;
		if (!Arrays.equals(buffer, other.buffer))
			return false;
		if (pColor != other.pColor)
			return false;
		if (pID != other.pID)
			return false;
		if (pName == null) {
			if (other.pName != null)
				return false;
		} else if (!pName.equals(other.pName))
			return false;
		if (Double.doubleToLongBits(pPrice) != Double.doubleToLongBits(other.pPrice))
			return false;
		if (Double.doubleToLongBits(pSalePrice) != Double.doubleToLongBits(other.pSalePrice))
			return false;
		if (pStore != other.pStore)
			return false;
		if (pType != other.pType)
			return false;
		return true;
	}

	/**
	 * @return product store Id
	 */
	public int getpStore() {
		return pStore;
	}

	/**
	 * product store Id to set
	 */
	public void setpStore(int pStore) {
		this.pStore = pStore;
	}

	/**
	 * @return quantity of products
	 */
	public int getQuantity() {
		return Quantity;
	}

	/**
	 * @param quantity of products to set
	 */
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
	/**
	 * @return sale price of product
	 */
	public double getpSalePrice() {
		return pSalePrice;
	}

	/**
	 * @param pSalePrice - sale price of product to set
	 */
	public void setpSalePrice(double pSalePrice) {
		this.pSalePrice = pSalePrice;
	}

	/**
	 * @return product domain color
	 */
	public ProductColor getpColor() {
		return pColor;
	}

	/**
	 * @param pColor - product domain color to set
	 */
	public void setpColor(ProductColor pColor) {
		this.pColor = pColor;
	}

	/**
	 * compare two products by their price to order them
	 */
	@Override
	public int compareTo(Product pro) {
		return (int)this.getpPrice() - (int)pro.getpPrice();
	}

	/**
	 * set product image
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @param buffer - to set
	 */
	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}
	
	
}
