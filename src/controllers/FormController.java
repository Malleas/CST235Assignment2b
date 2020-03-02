package controllers;
import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import beans.User;
import business.MyTimerService;
import business.OrdersBusinessInterface;

@ManagedBean
@ViewScoped

public class FormController {
	
	@Inject OrdersBusinessInterface ordersBusinessInterface;
	@EJB MyTimerService timer;
	
	
	public void onSubmit(User user) throws IOException {
		ordersBusinessInterface.test();
		timer.setTimer(10000);
		FacesContext.getCurrentInstance().getExternalContext().redirect("TestResponse.xhtml");
	}
	
	public String onFlash(User user) throws IOException {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		System.out.println("firstname " + user.getFirstName() + " lastname " + user.getLastName());
		flash.put("firstName", user.getFirstName());
		flash.put("lastName", user.getLastName());
		return "TestResponse2?faces-redirect=true";
	}

	public OrdersBusinessInterface getService() {
		return ordersBusinessInterface;
	}

	
}
