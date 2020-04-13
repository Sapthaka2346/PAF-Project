package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Item {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertItem( String H_Name, String H_Address, String H_City,String H_phonenumber,String H_Desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into reg_Hospital(`Hospital_ID`,`H_Name`,`H_Address`,`H_City`,`H_phonenumber`,`H_Desc`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, H_Name);
			preparedStmt.setString(3, H_Address);
			preparedStmt.setString(4, H_City);
			preparedStmt.setString(5, H_phonenumber);
			preparedStmt.setString(6, H_Desc);
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

	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Hospital_ID</th><th>H_Name</th><th>H_Address</th><th>H_City<th>H_phonenumber</th><th>H_Desc</th></th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from reg_hospital";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Hospital_ID = Integer.toString(rs.getInt("Hospital_ID"));
				String H_Name = rs.getString("H_Name");
				String H_Address = rs.getString("H_Address");
				String H_City = rs.getString("H_City");
				String H_phonenumber = Integer.toString(rs.getInt("H_phonenumber"));
				String H_Desc = rs.getString("H_Desc");
				// Add into the html table
				output += "<tr><td>" + Hospital_ID + "</td>";
				output += "<td>" + H_Name + "</td>";
				output += "<td>" + H_Address + "</td>";
				output += "<td>" + H_City + "</td>";
				output += "<td>" + H_phonenumber + "</td>";
				output += "<td>" + H_Desc + "</td>";
				
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + Hospital_ID + "\">" + "</form></td></tr>";
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

	public String updateItem( String Hospital_ID ,String H_Name, String H_Address, String H_City,String H_phonenumber,String H_Desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE reg_hospital SET H_Name=?,H_Address=?,H_City=?,H_phonenumber=?,H_Desc=? WHERE Hospital_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, H_Name);
			preparedStmt.setString(2, H_Address);
			preparedStmt.setString(3, H_City);
			preparedStmt.setString(4, H_phonenumber);
			preparedStmt.setString(5, H_Desc);
			preparedStmt.setInt(6,Integer.parseInt( Hospital_ID));
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

	public String deleteItem(String Hospital_ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from reg_hospital where Hospital_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Hospital_ID));
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