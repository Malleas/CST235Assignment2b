package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.jms.ObjectMessage;

import beans.Order;
import beans.User;
import business.MyTimerService;
import business.OrdersBusinessInterface;
import business.OrdersBusinessService;

@ManagedBean
@ViewScoped

public class FormController {

	@Inject
	OrdersBusinessInterface ordersBusinessInterface;
	@EJB
	MyTimerService timer;

	public String onSendOrder() {
		ordersBusinessInterface.sendOrder(new Order("Test", "Some Test Item", 1000.99f, 42));

		return "OrderResponse.xhtml";
	}

	public String onLogOff() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "TestResponse.xhtml?faces-redirect=true";
	}


//	public void onSubmit(User user) throws Exception {
//		ordersBusinessInterface.test();
//		// Ítimer.setTimer(10000);
//		getAllOrders();
//		insertOrder();
//		getAllOrders();
//		FacesContext.getCurrentInstance().getExternalContext().redirect("TestResponse.xhtml");
//	}

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

	private void getAllOrders() throws Exception {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String userName = "postgres";
		String password = "password1";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Connection Successful...");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM testapp.ORDERS";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("ID");
				String productName = rs.getString("PRODUCT_NAME");
				float price = rs.getFloat("PRICE");
				System.out.println("The order ID is: " + id + " and the product name is: " + productName
						+ " with a price of: " + price);
			}
			rs.close();

		} catch (Exception e) {
			System.out.println("Failure " + e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	private void insertOrder() throws Exception {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String userName = "postgres";
		String password = "password1";
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Connection Successful, Inserting new data...");
			stmt = conn.createStatement();
			String sql = "INSERT INTO testapp.Orders(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES('1122334455', 'This was inserted new', 25.00, 100);";
			stmt.executeQuery(sql);
		} catch (Exception e) {
			System.out.println("Failure " + e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	

}
