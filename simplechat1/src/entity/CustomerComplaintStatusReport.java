package entity;

import java.io.Serializable;
import java.util.HashMap;

public class CustomerComplaintStatusReport extends Report implements Serializable {
	
	private HashMap<User,Complaint> CustomerComplaintStatusReport = new HashMap<User,Complaint>();

	public CustomerComplaintStatusReport(String qaurterReport, int serialNumberReport, int storeId,
			HashMap<User, Complaint> customerComplaintStatusReport) {
		super(qaurterReport, serialNumberReport, storeId);
		CustomerComplaintStatusReport = customerComplaintStatusReport;
	}
	
	public HashMap<User, Complaint> getCustomerComplaintStatusReport() {
		return CustomerComplaintStatusReport;
	}

	public void setCustomerComplaintStatusReport(HashMap<User, Complaint> customerComplaintStatusReport) {
		CustomerComplaintStatusReport = customerComplaintStatusReport;
	}
	
	@Override
	public String toString() {
		return "CustomerComplaintStatusReport [CustomerComplaintStatusReport=" + CustomerComplaintStatusReport + "]";
	}
}
