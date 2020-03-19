package business;


import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Order;

@RequestScoped
@Path("/orders")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })

public class OrdersRestService {

	@Inject OrdersBusinessInterface ordersBusinessInterface;
	@Inject OrdersDataService ordersDataService;
	
	@POST
	@Path("/addOrder")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addOrder() throws Exception {
		Response message = ordersDataService.addOrder(new Order("P0011", "POST ORDER", 14.99f, 143));
		return message;
	}
	
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
