package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor {

	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf?useTimezone=true&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertMItem( String Rdoctor_name, String Rdoctor_address, String Rdoctor_specilaization,String Rdoctor_email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into reg_doctor(`Rdoctor_ID`,`Rdoctor_name`,`Rdoctor_address`,`Rdoctor_specilaization`,`Rdoctor_email`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement  preparedStmt = con.prepareStatement(query);
			
			// binding values
			
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Rdoctor_name);
			preparedStmt.setString(3, Rdoctor_address);
			preparedStmt.setString(4, Rdoctor_specilaization);
			preparedStmt.setString(5, Rdoctor_email);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readMItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Rdoctor_ID</th><th>Rdoctor_name</th><th>Rdoctor_address</th><th>Rdoctor_specilaization<th>Rdoctor_email</th></th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from reg_doctor";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Rdoctor_ID = Integer.toString(rs.getInt("Rdoctor_ID"));
				String Rdoctor_name = rs.getString("Rdoctor_name");
				String Rdoctor_address = rs.getString("Rdoctor_address");
				String Rdoctor_specilaization = rs.getString("Rdoctor_specilaization");
				String Rdoctor_email = rs.getString("Rdoctor_email");

				
				// Add into the html table
				output += "<tr><td>" + Rdoctor_ID + "</td>";
				output += "<td>" + Rdoctor_name + "</td>";
				output += "<td>" + Rdoctor_address + "</td>";
				output += "<td>" + Rdoctor_specilaization + "</td>";
				output += "<td>" + Rdoctor_email + "</td>";
				
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + Rdoctor_ID + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateMItem( String Rdoctor_ID ,String Rdoctor_name, String Rdoctor_address, String Rdoctor_specilaization,String Rdoctor_email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE reg_doctor SET Rdoctor_name=?,Rdoctor_address=?,Rdoctor_specilaization=?,Rdoctor_email=? WHERE Rdoctor_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, Rdoctor_name);
			preparedStmt.setString(2, Rdoctor_address);
			preparedStmt.setString(3, Rdoctor_specilaization);
			preparedStmt.setString(4, Rdoctor_email);
			preparedStmt.setInt(5,Integer.parseInt( Rdoctor_ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteMItem(String Rdoctor_ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from reg_doctor where Rdoctor_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Rdoctor_ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
	
	
	

