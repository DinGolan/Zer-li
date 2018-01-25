package entity;

import java.sql.Date;

import entity.Complaint.ComplaintStatus;

public class CustomerComplaintDetailsRow
{

	private String Complaint_Month;
	
	private double Complaint_Compansation;
	
	private Date Complaint_Date;
	
	private int Complaint_ID;
	
	private Complaint.ComplaintStatus Complaint_Status;
	
	private int Complaint_Order_ID;

	public CustomerComplaintDetailsRow()
	{
		
	}
	
	public CustomerComplaintDetailsRow(String complaint_Month, double complaint_Compansation, Date complaint_Date,
			int complaint_ID, ComplaintStatus complaint_Status, int complaint_Order_ID) {
		super();
		Complaint_Month = complaint_Month;
		Complaint_Compansation = complaint_Compansation;
		Complaint_Date = complaint_Date;
		Complaint_ID = complaint_ID;
		Complaint_Status = complaint_Status;
		Complaint_Order_ID = complaint_Order_ID;
	}

	public String getComplaint_Month() {
		return Complaint_Month;
	}

	public void setComplaint_Month(String complaint_Month) {
		Complaint_Month = complaint_Month;
	}

	public double getComplaint_Compansation() {
		return Complaint_Compansation;
	}

	public void setComplaint_Compansation(double complaint_Compansation) {
		Complaint_Compansation = complaint_Compansation;
	}

	public Date getComplaint_Date() {
		return Complaint_Date;
	}

	public void setComplaint_Date(Date complaint_Date) {
		Complaint_Date = complaint_Date;
	}

	public int getComplaint_ID() {
		return Complaint_ID;
	}

	public void setComplaint_ID(int complaint_ID) {
		Complaint_ID = complaint_ID;
	}

	public Complaint.ComplaintStatus getComplaint_Status() {
		return Complaint_Status;
	}

	public void setComplaint_Status(Complaint.ComplaintStatus complaint_Status) {
		Complaint_Status = complaint_Status;
	}

	public int getComplaint_Order_ID() {
		return Complaint_Order_ID;
	}

	public void setComplaint_Order_ID(int complaint_Order_ID) {
		Complaint_Order_ID = complaint_Order_ID;
	}

	@Override
	public String toString() {
		return "CustomerComplaintDetailsRow [Complaint_Month=" + Complaint_Month + ", Complaint_Compansation="
				+ Complaint_Compansation + ", Complaint_Date=" + Complaint_Date + ", Complaint_ID=" + Complaint_ID
				+ ", Complaint_Status=" + Complaint_Status + ", Complaint_Order_ID=" + Complaint_Order_ID + "]";
	}
}
