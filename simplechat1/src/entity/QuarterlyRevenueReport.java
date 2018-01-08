package entity;

import java.io.Serializable;

public class QuarterlyRevenueReport extends Report implements Serializable{

	private double totalStoreIncome;

	public QuarterlyRevenueReport(String qaurterReport, int serialNumberReport, int storeId, double totalStoreIncome) {
		super(qaurterReport, serialNumberReport, storeId);
		this.totalStoreIncome = totalStoreIncome;
	}

	public double getTotalStoreIncome() {
		return totalStoreIncome;
	}

	public void setTotalStoreIncome(double totalStoreIncome) {
		this.totalStoreIncome = totalStoreIncome;
	}
	
	@Override
	public String toString() {
		return "IncomeReport [totalStoreIncome=" + totalStoreIncome + "]";
	}
}
