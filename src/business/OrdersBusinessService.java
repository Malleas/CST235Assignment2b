package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;

/**
 * Session Bean implementation class OrdersBusinessService
 */
@Alternative
@Stateless
@Local(OrdersBusinessInterface.class)
@LocalBean
public class OrdersBusinessService implements OrdersBusinessInterface {

	
	List<Order> orders;
    /**
     * Default constructor. 
     */
    public OrdersBusinessService() {
    	orders = new ArrayList<Order>();
		orders.add(new Order("B0011", "Orders", 1.29f, 13));
		orders.add(new Order("A0041", "Service", 19.44f, 1));
    }

	/**
     * @see OrdersBusinessInterface#test()
     */
    public void test() {
        System.out.println("Hello from the OrderBusinessService");
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
