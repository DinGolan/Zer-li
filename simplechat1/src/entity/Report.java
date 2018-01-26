package entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * This Entity Is The Report And All The Properties That Report Need . 
 * @author dingo
 *
 */
@SuppressWarnings("serial")
public class Report implements Serializable {
	
	private String qaurterReportNumber;      /* The Quarter Report Number */
	private int serialNumberReport;			 /* The Serial Number Of the Report */
	private int storeId;                     /* Report That Belong to Specific Store */
	private Date Date_Of_Report;			 /* The Date That We Create The Report */
	
	public Report() {}
	
	public Report(String qaurterReport, int serialNumberReport, int storeId , Date Date_Of_Report) {
		super();
		this.qaurterReportNumber = qaurterReport;
		this.serialNumberReport = serialNumberReport;
		this.storeId = storeId;
		this.Date_Of_Report = Date_Of_Report;
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

	public Date getDate_Of_Report() {
		return Date_Of_Report;
	}

	public void setDate_Of_Report(Date date_Of_Report) {
		Date_Of_Report = date_Of_Report;
	}

	@Override
	public String toString() {
		return "Report [qaurterReportNumber=" + qaurterReportNumber + ", serialNumberReport=" + serialNumberReport
				+ ", storeId=" + storeId + ", Date_Of_Report=" + Date_Of_Report + "]";
	}
}
