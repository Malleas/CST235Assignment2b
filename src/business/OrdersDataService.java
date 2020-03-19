package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.jms.ObjectMessage;
import javax.ws.rs.core.Response;

import beans.Order;

@Named
@ApplicationScoped
public class OrdersDataService {

	public OrdersDataService() {
		// TODO Auto-generated constructor stub
	}
	
	public Response addOrder(Order order) throws Exception {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String userName = "postgres";
		String password = "password1";
		Connection conn = null;
		PreparedStatement ps = null;
		System.out.println("This is the message: " +  order);
		String message = null;
		
		try {
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Connection Successfull...");
			String sql = "INSERT INTO testapp.Orders(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES(?,?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, order.getOrderNumber());
			ps.setString(2, order.getProductName());
			ps.setFloat(3, order.getPrice());
			ps.setInt(4, order.getQuantity());
			ps.executeUpdate();
			ps.close();
			message = "Order added...";
			
		}catch(Exception e) {
			System.out.println("Failure: " + e);
			e.printStackTrace();
			message = "Order not added, there was an error...";
		}finally {
			if(conn != null) {
				conn.close();
			}
		}
		if(message.equals("Order added...")) {
			return Response
					.status(Response.Status.OK)
					.entity(message)
					.build();
		}else {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(message)
					.build();
		}
		
		
	}

}
