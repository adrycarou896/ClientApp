package dpigUser.rules;

import java.util.Date;

public class Event {
	
	private String person; 
	private Date accomplishedDate;
	private String action;
	private int hall;
	
	
	public Event(String person, Date accomplishedDate, String action, int hall) { 
		this.person = person;
		this.accomplishedDate = accomplishedDate;
		this.action = action;
		this.hall = hall;
	}
	
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public Date getAccomplishedDate() {
		return accomplishedDate;
	}
	public void setAccomplishedDate(Date accomplishedDate) {
		this.accomplishedDate = accomplishedDate;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getHall() {
		return hall;
	}
	public void setHall(int hall) {
		this.hall = hall;
	}
	
	
	
}
