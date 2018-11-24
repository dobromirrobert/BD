/**
 * Instantiere cu 
 *	BD db = new BD("localhost", "3306", "lant_policlinici", "adminLantPoli", "KbKtB6pw#5SVJ8hH");
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author 
 *
 */
public class BD {
	private Connection dbHandle;
	
	private String dbhost;
	private String dbport;
	private String dbname;
	private String dbuser;
	private String dbpass;
	
	public BD(String dbhost, String dbport, String dbname,  String dbuser, String dbpass) {
		this.dbhost = dbhost;
		this.dbport = dbport;
		this.dbname = dbname;
		this.dbuser = dbuser;
		this.dbpass = dbpass;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e);
			System.exit(1);
		}

		dbHandle = null;
		try {
			dbHandle = DriverManager.getConnection(
					"jdbc:mysql://" + this.getHost() + ":" + this.getPort() + "/" + this.getBD() + "?useSSL=false", 
					this.getUsername(), 
					this.getPassword()
				);
		}
		catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
			System.exit(1);
		}
	}
	
	public String getHost() {
		return dbhost;
	}
	
	public String getPort() {
		return dbport;
	}

	public String getBD() {
		return dbname;
	}
	
	public String getUsername() {
		return dbuser;
	}
	
	public String getPassword() {
		return dbpass;
	}	
	
	public ArrayList<String[]> doQuery(String queryStr) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		
		try {
			Statement stmt = dbHandle.createStatement();
			ResultSet rst = stmt.executeQuery(queryStr);
			ResultSetMetaData rsmd = rst.getMetaData();

			int colCount = rsmd.getColumnCount();
			int rowCount = 0;
			
			String[] row = new String[colCount];
			
			while (rst.next()) {
				for (int i = 1; i <= colCount; ++i) {	
					Object obj = rst.getObject(i);
					
					if (obj != null)
						row[i - 1] = obj.toString();
					else
						row[i - 1] = null;
				}
				
				result.add(row);
				
				rowCount++;
			}
			
			if (rowCount == 0)
				result = null;

			rst.close();
		} catch (SQLException ex) {
			System.out.println("eroare" + ex);
		}
		
		return result;
	}
	
	public int doUpdate(String updateStr)	{
		int rowCount = 0;
		try {
			Statement stmt = dbHandle.createStatement();
			
			rowCount = stmt.executeUpdate(updateStr);
			
			stmt.close();
		}
		catch (SQLException e) {	
			System.out.println("Operation failed: " + e);
		}
		
		return rowCount;
	}
}
