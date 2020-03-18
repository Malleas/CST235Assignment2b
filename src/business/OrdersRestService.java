package business;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.Order;

@RequestScoped
@Path("/orders")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })

public class OrdersRestService {

	@Inject OrdersBusinessInterface ordersBusinessInterface;
	
	@GET
	@Path("/getjson")
	@Produces(MediaType.APPLICATION_JSON)

	public List<Order> getOrdersAsJson(){
		return ordersBusinessInterface.getOrders();
		
	}
	
	@GET
	@Path("/getxml")
	@Produces(MediaType.APPLICATION_XML)
	public List<Order> getOrdersAsXml(){
		return ordersBusinessInterface.getOrders();
		
	}
}
