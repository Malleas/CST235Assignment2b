package beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.validation.constraints.Size;

import com.sun.istack.internal.NotNull;

@Named("user")
@ApplicationScoped
public class User {
@NotNull
@Size(min=5, max=15)
	private String firstName;
@NotNull
@Size(min=5, max=15)
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
