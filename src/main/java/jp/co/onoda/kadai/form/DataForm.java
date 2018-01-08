package jp.co.onoda.kadai.form;

//DATA
public class DataForm {

	private String eventName;

	private String eventDate;

	private String remarks;

	private String name;

	private String answer;

	private String atdremarks;


	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getatdRemarks() {
		return atdremarks;
	}

	public void setatdRemarks(String atdremarks) {
		this.atdremarks = atdremarks;
	}






	@Override
	public String toString() {
		return "DataForm [eventName=" + eventName + ", eventDate=" + eventDate + ", remarks=" + remarks +"]";
	}






}
