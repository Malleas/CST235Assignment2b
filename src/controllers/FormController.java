package controllers;
import java.io.IOException;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import beans.User;

@ManagedBean
@ViewScoped

public class FormController {
	
	
	public static void onSubmit(User user) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("TestResponse.xhtml");
	}
	
	public String onFlash(User user) throws IOException {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		System.out.println("firstname " + user.getFirstName() + " lastname " + user.getLastName());
		flash.put("firstName", user.getFirstName());
		flash.put("lastName", user.getLastName());
		return "TestResponse2?faces-redirect=true";
	}

}
