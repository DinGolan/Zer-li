package entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javafx.scene.image.Image;

public class Product implements Serializable
{
	private String pID;
	private String pName;
	private ProductType pType;	
	private double pPrice;	
	private transient InputStream input;
	private transient Image image;
	private byte[] buffer;

	public enum ProductType {BOUQUET , ARRANGEMENT , SWEET_BOUQUET , FLOWER_CROWN , BRIDAL_BOUQUET , VASE , FLOWER_WREATH}
	
	

	public Product() {
		super();
	}

	public String getpID() {
		return pID;
	}

	public void setpID(String pID) {
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
		result = prime * result + ((pID == null) ? 0 : pID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.getpID() == ((Product)obj).getpID())
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (pID == null) {
			if (other.pID != null)
				return false;
		} else if (!pID.equals(other.pID))
			return false;
		return true;
	}
	
	
}
