package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;

/**
 * Session Bean implementation class AnotherOrdersBusinessService
 */
@Alternative
@Stateless
@Local(OrdersBusinessInterface.class)
@LocalBean
public class AnotherOrdersBusinessService implements OrdersBusinessInterface {

	
	List<Order> orders;
    /**
     * Default constructor. 
     */
    public AnotherOrdersBusinessService() {
    	orders = new ArrayList<Order>();
		orders.add(new Order("B0001", "Another", 1.99f, 13));
		orders.add(new Order("A0001", "OrderService", 19.99f, 1));
		System.out.println(orders);
		for(Order order: orders) {
			System.out.println(order.getOrderNumber());
			System.out.println(order.getProductName());
			System.out.println(order.getPrice());
			System.out.println(order.getQuantity());
		}
    }

	/**
     * @see OrdersBusinessInterface#test()
     */
    public void test() {
        System.out.println("Hello from the AnotherOrdersBusinessService");
    }

	@Override
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
		this.orders = (ArrayList<Order>) orders;
		
	}

}
