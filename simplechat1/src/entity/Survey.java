package entity;

import java.sql.Date;

public class Survey {
	private int Survey_Id;
	private String Question;
	private String Answer;
	private int Num_Of_Clients;
	private int Rank;
	private Date Survey_Date;
	private int Store_ID;
	private String QuarterNumber;
	
	public Survey()
	{
		
	}
	
	public Survey(int survey_Id, String question, String answer, int num_Of_Clients, int rank, Date survey_Date) {
		super();
		Survey_Id = survey_Id;
		Question = question;
		Answer = answer;
		Num_Of_Clients = num_Of_Clients;
		Rank = rank;
		Survey_Date = survey_Date;
	}

	public int getSurvey_Id() {
		return Survey_Id;
	}

	public void setSurvey_Id(int survey_Id) {
		Survey_Id = survey_Id;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}

	public int getNum_Of_Clients() {
		return Num_Of_Clients;
	}

	public void setNum_Of_Clients(int num_Of_Clients) {
		Num_Of_Clients = num_Of_Clients;
	}

	public int getRank() {
		return Rank;
	}

	public void setRank(int rank) {
		Rank = rank;
	}

	public Date getSurvey_Date() {
		return Survey_Date;
	}

	public void setSurvey_Date(Date survey_Date) {
		Survey_Date = survey_Date;
	}

	public int getStore_ID() {
		return Store_ID;
	}

	public void setStore_ID(int store_ID) {
		Store_ID = store_ID;
	}

	public String getQuarterNumber() {
		return QuarterNumber;
	}

	public void setQuarterNumber(String quarterNumber) {
		QuarterNumber = quarterNumber;
	}

	@Override
	public String toString() {
		return "Survey [Survey_Id=" + Survey_Id + ", Question=" + Question + ", Answer=" + Answer + ", Num_Of_Clients="
				+ Num_Of_Clients + ", Rank=" + Rank + ", Survey_Date=" + Survey_Date + "]";
	}
}
