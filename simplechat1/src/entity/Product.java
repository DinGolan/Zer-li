package entity;

import java.io.Serializable;

public class Product implements Serializable
{
	private String pID;
	private String pName;
	private ProductType pType;	
	private double pPrice;	
	private String pPicture;	

	public enum ProductType {BOUQUET , ARRANGEMENT , SWEET_BOUQUET , FLOWER_CROWN , BRIDAL_BOUQUET , VASE , FLOWER_WREATH}
	
	public Product(String pID, String pName, ProductType pType ,double pPrice , String pPicture) {
		super();
		this.pID = pID;
		this.pName = pName;
		this.pType = pType; 
		this.pPrice = pPrice;
		this.pPicture = pPicture;
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

	public String getpPicture() {
		return pPicture;
	}

	public void setpPicture(String pPicture) {
		this.pPicture = pPicture;
	}	
	
	
}
