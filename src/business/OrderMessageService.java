package business;

import java.util.logging.Level;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import beans.Order;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.*;

/**
 * Message-Driven Bean implementation class for: OrderMessageService
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/Order"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "java:/jms/queue/Order")

public class OrderMessageService implements MessageListener {


	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Inject 
	private OrdersDataService ordersDataService;

	/**
	 * Default constructor.
	 */
	public OrderMessageService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		TextMessage textMessage = null;
		ObjectMessage objectMessage = null;
		try {
			if (message instanceof TextMessage) {
				textMessage = (TextMessage) message;
				LOGGER.log(Level.INFO, "MESSAGE BEAN: Message Recieved: " + textMessage.getText());
			} 
			else if(message instanceof ObjectMessage) {
				objectMessage = (ObjectMessage) message;
				Order order =  (Order) objectMessage.getObject();
				ordersDataService.addOrder(order);
			}
			else {
				LOGGER.log(Level.WARNING, "Message of wrong type: " + message);
			}

			
		} catch (Exception e) {
			System.out.println("Failure: " + e);
			e.printStackTrace();
		}

	}

}
