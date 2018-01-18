package entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;

import javafx.scene.image.Image;

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

	public enum ProductColor {RED, PINK, ORANGE, WHITE, YELLOW}
	public enum ProductType {BOUQUET , ARRANGEMENT , SWEET_BOUQUET , FLOWER_CROWN , BRIDAL_BOUQUET , VASE , WREATH_FLOWERS}
	
	

	public Product() {
		super();
	}

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public ProductType getpType() {
		return pType;
	}

	public void setpType(ProductType pType) {
		this.pType = pType;
	}
	
	public String toString(){
		return String.format("\nProduct: %s\t %s\t %s\n",pID,pName,pType);
	}

	public double getpPrice() {
		return pPrice;
	}

	public void setpPrice(double pPrice) {
		this.pPrice = pPrice;
	}


	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}	

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

	public Image getImage() {
		return image;
	}
	
	public byte[] getByteArray() {
		return buffer;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Quantity;
		result = prime * result + Arrays.hashCode(buffer);
		result = prime * result + ((pColor == null) ? 0 : pColor.hashCode());
		result = prime * result + pID;
		result = prime * result + ((pName == null) ? 0 : pName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(pPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pSalePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + pStore;
		result = prime * result + ((pType == null) ? 0 : pType.hashCode());
		return result;
	}

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

	public int getpStore() {
		return pStore;
	}

	public void setpStore(int pStore) {
		this.pStore = pStore;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
	public double getpSalePrice() {
		return pSalePrice;
	}

	public void setpSalePrice(double pSalePrice) {
		this.pSalePrice = pSalePrice;
	}

	public ProductColor getpColor() {
		return pColor;
	}

	public void setpColor(ProductColor pColor) {
		this.pColor = pColor;
	}

	@Override
	public int compareTo(Product pro) {
		return (int)this.getpPrice() - (int)pro.getpPrice();
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}
	
	
}
