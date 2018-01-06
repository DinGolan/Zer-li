package entity;

import java.io.Serializable;
import java.sql.Date;

public class Complaint implements Serializable{
	private int complaintNum; //save the complaint number
	private Date complaintDate;
	private ComplaintStatus complaintStat;
	private double complaintCompansation;
	private String complaintCompanyServiceWorkerAnswer;
	private String complaintDetails;
	private String complaintMonth;
	private int orderID;

	public enum ComplaintStatus{ //enum for the complaint status
		OPEN,INPROGRESS,CLOSE
	}

	/*public Complaint(int complaintNum, Date complaintDate, ComplaintStatus complaintStat, double complaintCompansation,
			String companyServiceWorkerAnswer, String complaintDetails) {
		super();
		this.complaintNum = complaintNum;
		this.complaintDate = complaintDate;
		this.complaintStat = complaintStat;
		this.complaintCompansation = complaintCompansation;
		this.companyServiceWorkerAnswer = companyServiceWorkerAnswer;
		this.complaintDetails = complaintDetails;
	}*/
	
	/*public Complaint() { //Constructor
		super();
		this.complaintNum = complaintNum;
		this.complaintDate = null;
		this.complaintStat = ComplaintStatus.OPEN;
		this.complaintCompansation = 0;
		this.companyServiceWorkerAnswer = null;
		this.complaintDetails = null;
	}*/
	
	//getters and setters
	
	public int getComplaintNum() {
		return complaintNum;
	}

	public void setComplaintNum(int complaintNum) {
		this.complaintNum = complaintNum;
	}

	public Date getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}

	public ComplaintStatus getComplaintStat() {
		return complaintStat;
	}

	public void setComplaintStat(ComplaintStatus complaintStat) {
		this.complaintStat = complaintStat;
	}

	public double getComplaintCompansation() {
		return complaintCompansation;
	}

	public void setComplaintCompansation(double complaintCompansation) {
		this.complaintCompansation = complaintCompansation;
	}

	public String getCompanyServiceWorkerAnswer() {
		return complaintCompanyServiceWorkerAnswer;
	}

	public void setCompanyServiceWorkerAnswer(String complaintCompanyServiceWorkerAnswer) {
		this.complaintCompanyServiceWorkerAnswer = complaintCompanyServiceWorkerAnswer;
	}

	public String getComplaintDetails() {
		return complaintDetails;
	}

	public void setComplaintDetails(String complaintDetails) {
		this.complaintDetails = complaintDetails;
	}
	
	
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getComplaintMonth() {
		return complaintMonth;
	}
	
	public void setComplaintMonth(String complaintMonth) {
		this.complaintMonth = complaintMonth;
	}

	@Override
	public String toString() {
		return "Complaint [complaintNum=" + complaintNum + ", complaintDate=" + complaintDate + ", complaintStat="
				+ complaintStat + ", complaintCompansation=" + complaintCompansation + ", complaintCompanyServiceWorkerAnswer="
				+ complaintCompanyServiceWorkerAnswer + ", complaintDetails=" + complaintDetails + "]";
	}	
}
	
	
	


