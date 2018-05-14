package co.com.recruitment.test.hays.domain;

public class Call {
	
	private String number;
	private String username;
	private int timeToSolveIssue;
	
		
	public int getTimeToSolveIssue() {
		return timeToSolveIssue;
	}
	public void setTimeToSolveIssue(int timeToSolveIssue) {
		this.timeToSolveIssue = timeToSolveIssue;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	

}
