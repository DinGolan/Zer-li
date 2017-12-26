package entity;

import java.io.Serializable;

public class Product implements Serializable
{
	private String pID;
	private String pName;
	private String pType;	

	public Product(String pID, String pName, String pType) {
		super();
		this.pID = pID;
		this.pName = pName;
		this.pType = pType; 
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

	public String getpType() {
		return pType;
	}

	public void setpType(String pType) {
		this.pType = pType;
	}
	
	public String toString(){
		return String.format("\nProduct: %s\t %s\t %s\n",pID,pName,pType);
	}	
}
