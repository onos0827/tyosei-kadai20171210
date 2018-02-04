package jp.co.onoda.kadai.form;

import java.util.List;

//DATA
public class DataForm2 {



	private String eventID;

	private String userID;

	private String userName;

	private String answer;

	private String userRemarks;

	private List<String> Radiobutton;

	private String[] eventDate;




	public String[] geteventDate() {
		return eventDate;
	}

	public void seteventDate(String[] eventDate) {
		this.eventDate = eventDate;
	}

	public String geteventID() {
		return eventID;
	}

	public void seteventID(String eventID) {
		this.eventID = eventID;
	}

	public String getuserID() {
		return userID;
	}

	public void setuserID(String userID) {
		this.userID = userID;
	}


	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getuserRemarks() {
		return userRemarks;
	}

	public void setuserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}

	public List<String> getRadiobutton() {
		return Radiobutton;
	}

	public void setRadiobutton(List<String> Radiobutton) {
		this.Radiobutton = Radiobutton;
	}

}







