package agenda;

import java.util.Vector;

public class Meeting {
	private String sponsor;
	private Vector<String> participators;
	private Date startDate = new Date();
	private Date endDate = new Date();
	private String title;
	
	public Meeting(String sponsor, 
					Vector<String> participators,
					Date startTime, Date endTime,
					String title) {
		setSponsor(sponsor);
		setParticipators(participators);
		setStartDate(startTime);
		setEndDate(endTime);
		setTitle(title);
	}
	
	public Meeting(Meeting meeting) {
		setSponsor(meeting.getSponsor());
		setParticipators(meeting.getParticipators());
		setStartDate(meeting.getStartDate());
		setEndDate(meeting.getEndDate());
		setTitle(meeting.getTitle());
	}
	
	public Meeting() {
		// TODO Auto-generated constructor stub
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public Vector<String> getParticipators() {
		return participators;
	}

	public void setParticipators(Vector<String> participators) {
		this.participators = participators;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate.assign(startDate);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate.assign(endDate);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void addParticipator(String participator) {
		participators.add(participator);
	}
	
	public void removeParticipator(String participator) {
		participators.remove(participator);
	}
	
	public boolean isParticipator(String userName) {
		return participators.contains(userName);
	}
}
