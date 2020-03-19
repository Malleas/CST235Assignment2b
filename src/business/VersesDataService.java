package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.core.Response;

@Named
@ApplicationScoped
public class VersesDataService {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	// JDBC Driver name and database URL
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

	// DB Credentials
	static final String USER = "postgres";
	static final String PASS = "password1";

	public VersesDataService() {
		// TODO Auto-generated constructor stub

	}

	public Response getVerse(String inString) throws Exception {
		String message = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "Select * from testapp.verse";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String bookName = rs.getString("bookName");
				int chapter = rs.getInt("chapter");
				int verseNumber = rs.getInt("verseNumber");
				String verse = rs.getString("verseText");
				String[] verseArray = verse.split(" ");

				for (int i = 0; i < verseArray.length; i++) {
					if (verseArray[i].toLowerCase().equals(inString.toLowerCase())) {
						
						int wordLocation = i + 1;
						message = "The first occurrence of the word " + inString + "\n" + "Is in the book of " + bookName
								+ " Chapter " + chapter + " Verse " + verseNumber + " and is the " + wordLocation
								+ " word in that verse.";
					}

				}

			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return Response.status(200).entity(message).build();

	}

}
