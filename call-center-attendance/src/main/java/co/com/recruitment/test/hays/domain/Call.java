package co.com.recruitment.test.hays.domain;
/**
 * Entity that it encapsulates the logic about one call
 * @author Andres
 *
 */
public class Call {
	
	private String number;		
	
	public Call(String number) {
		this.number = number;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}


}
