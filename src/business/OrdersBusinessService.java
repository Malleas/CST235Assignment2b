package business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.http.HttpSession;
import javax.jms.Connection;

import beans.Order;
import beans.User;

/**
 * Session Bean implementation class OrdersBusinessService
 */
@Alternative
@Stateless
@Local(OrdersBusinessInterface.class)
@LocalBean
public class OrdersBusinessService implements OrdersBusinessInterface {
	
	@Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(mappedName="java:/jms/queue/Order")
	private Queue queue;

	@Inject
	private User user;
	
	ObjectMessage message2;
	

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

	@Override
	public void sendOrder(Order order) {
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(queue);
			TextMessage message1 = session.createTextMessage();
			message1.setText("This is a test message");
			messageProducer.send(message1);
			ObjectMessage message2 = session.createObjectMessage();
			message2.setObject(order);
			messageProducer.send(message2);
			connection.close();
			
		}catch(JMSException e) {
			System.out.println("Failure: " + e);
			e.printStackTrace();
		}
		
	}
	
	public void setSessionVariables(ActionEvent event) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(session != null) {
			session.setAttribute("firstName", user.getFirstName());
			session.setAttribute("lastName", user.getLastName());
		}
	}

}
