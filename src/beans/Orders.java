package beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;



@Named
@ApplicationScoped
public class Orders {
	
	private ArrayList<Order> orders;
	
	
	

	public Orders(List<Order> orders) {
		super();
		orders = new ArrayList<Order>();
		this.orders = (ArrayList<Order>) orders;
	}

	public Orders() {
		orders = new ArrayList<Order>();
		orders.add(new Order("B0001", "Things", 1.99f, 13));
		orders.add(new Order("A0001", "Stuff", 19.99f, 1));
		System.out.println(orders);
		for(Order order: orders) {
			System.out.println(order.getOrderNumber());
			System.out.println(order.getProductName());
			System.out.println(order.getPrice());
			System.out.println(order.getQuantity());
		}
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = (ArrayList<Order>) orders;
	}

	

}
