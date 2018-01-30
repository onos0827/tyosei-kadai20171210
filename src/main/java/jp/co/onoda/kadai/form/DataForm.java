package jp.co.onoda.kadai.form;

//DATA
public class DataForm {

	private String eventId;

	private String eventName;

	private String eventDate;

	private String remarks;




	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}


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





	@Override
	public String toString() {
		return "DataForm [eventId=" + eventId +", eventName=" + eventName + ", eventDate=" + eventDate + ", remarks=" + remarks +"]";
	}






}
