package dpigUser.rules;

import java.util.Date;

public class Alert {
	
	private Event event;
	private Date dateAlert;
	
	public Alert(Event event, Date dateAlert) {
		this.event = event;
		this.dateAlert = dateAlert;
	}
	
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Date getDateAlert() {
		return dateAlert;
	}
	public void setDateAlert(Date dateAlert) {
		this.dateAlert = dateAlert;
	}
	
	
	
	
}
