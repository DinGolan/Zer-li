package entity;

import java.sql.Date;

public class Survey {
	private int Survey_Id;
	private String Question;
	private String Answer;
	private int Num_Of_Clients;
	private int Rank;
	private Date Survey_Start_Date;
	private Date Survey_End_Date;
	private int Store_ID;
	private String QuarterNumber;
	
	public Survey()
	{
		
	}

	public Survey(int survey_Id, String question, String answer, int num_Of_Clients, int rank, Date survey_Start_Date,
			Date survey_End_Date, int store_ID, String quarterNumber) {
		super();
		Survey_Id = survey_Id;
		Question = question;
		Answer = answer;
		Num_Of_Clients = num_Of_Clients;
		Rank = rank;
		Survey_Start_Date = survey_Start_Date;
		Survey_End_Date = survey_End_Date;
		Store_ID = store_ID;
		QuarterNumber = quarterNumber;
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

	public Date getSurvey_Start_Date() {
		return Survey_Start_Date;
	}

	public void setSurvey_Start_Date(Date survey_Start_Date) {
		Survey_Start_Date = survey_Start_Date;
	}

	public Date getSurvey_End_Date() {
		return Survey_End_Date;
	}

	public void setSurvey_End_Date(Date survey_End_Date) {
		Survey_End_Date = survey_End_Date;
	}

	@Override
	public String toString() {
		return "Survey [Survey_Id=" + Survey_Id + ", Question=" + Question + ", Answer=" + Answer + ", Num_Of_Clients="
				+ Num_Of_Clients + ", Rank=" + Rank + ", Survey_Start_Date=" + Survey_Start_Date + ", Survey_End_Date="
				+ Survey_End_Date + ", Store_ID=" + Store_ID + ", QuarterNumber=" + QuarterNumber + "]";
	}
}
