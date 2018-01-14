package entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Report implements Serializable {
	
	private String qaurterReportNumber;
	private int serialNumberReport;
	private int storeId;
	
	public Report() {}
	
	public Report(String qaurterReport, int serialNumberReport, int storeId) {
		super();
		this.qaurterReportNumber = qaurterReport;
		this.serialNumberReport = serialNumberReport;
		this.storeId = storeId;
	}
	
	public String getQaurterReportNumber() {
		return qaurterReportNumber;
	}

	public void setQaurterReportNumber(String qaurterReportNumber) {
		this.qaurterReportNumber = qaurterReportNumber;
	}

	public int getSerialNumberReport() {
		return serialNumberReport;
	}
	public void setSerialNumberReport(int serialNumberReport) {
		this.serialNumberReport = serialNumberReport;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
	@Override
	public String toString() {
		return "Report [qaurterReport=" + qaurterReportNumber + ", serialNumberReport=" + serialNumberReport + ", storeId="
				+ storeId + "]";
	}
}
