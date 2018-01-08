package entity;

import java.io.Serializable;
import java.util.HashMap;

public class SatisfactionReport extends Report implements Serializable {
	
	private User user;
	private double averageOfTotalSaticfaction;
	private int rank;
	private HashMap<User,Integer> satisfactionReport = new HashMap<User,Integer>();

	public SatisfactionReport(String qaurterReport, int serialNumberReport , int storeId , User user, double averageOfTotalSaticfaction, int rank) {
		super(qaurterReport, serialNumberReport, storeId);
		this.user = user;
		this.averageOfTotalSaticfaction = averageOfTotalSaticfaction;
		this.rank = rank;
	}
	
	public HashMap<User, Integer> getSatisfactionReport() {
		return satisfactionReport;
	}

	public void setSatisfactionReport(HashMap<User, Integer> satisfactionReport) {
		this.satisfactionReport = satisfactionReport;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getAverageOfTotalSaticfaction() {
		return averageOfTotalSaticfaction;
	}
	public void setAverageOfTotalSaticfaction(double averageOfTotalSaticfaction) {
		this.averageOfTotalSaticfaction = averageOfTotalSaticfaction;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "SatisfactionReport [user=" + user + ", averageOfTotalSaticfaction=" + averageOfTotalSaticfaction
				+ ", rank=" + rank + ", satisfactionReport=" + satisfactionReport + "]";
	}
}
