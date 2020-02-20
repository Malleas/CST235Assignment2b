package beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("user")
@ApplicationScoped
public class User {

	private String firstName;
	private String lastName;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	

	public User(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
