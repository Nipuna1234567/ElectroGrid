package model;



import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ electrogrid", "paf", "12345678");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertCustomer(String cusName, String cusAddress, String cusEmail,  String cusPhone)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = "insert into customer (`cusID`,`cusName`,`cusAddress`,`cusEmail`,`cusPhone`)"
 		+ "values (?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 
 // binding values
 
 	preparedStmt.setInt(1, 0);
	preparedStmt.setString(2, cusName);
	preparedStmt.setString(3, cusAddress);
	preparedStmt.setString(4, cusEmail);
	preparedStmt.setInt(5, Integer.parseInt(cusPhone));
	
	
 // execute the statement
 
 preparedStmt.execute();
 con.close();
 output = "Customer Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the Customer.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String readCustomers()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 
 // Prepare the html table to be displayed
 
 output = "<table border='1'><tr><th>Customer Name</th><th>Customer Address</th>" +
			 "<th>Customer Email</th>" +
			 "<th>Phone Number</th>" +
			 "<th>Update</th><th>Delete</th></tr>";

 String query = "select * from customer";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 
 // iterate through the rows in the result set
 while (rs.next())
 {
 String cusID = Integer.toString(rs.getInt("cusID"));
 String cusName = rs.getString("cusName");
 String cusAddress = rs.getString("cusAddress");
 String cusEmail = rs.getString("cusEmail");
 String cusPhone = Integer.toString(rs.getInt("cusPhone"));
 
 
 // Add into the html table
 output += "<tr><td>" + cusName + "</td>";
 output += "<td>" + cusAddress + "</td>";
 output += "<td>" + cusEmail + "</td>";
 output += "<td>" + cusPhone + "</td>";
 
 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"
 + "<input name='btnRemove' type='submit' value='Delete' class='btn btn-danger'>"
 + "<input name='cusID' type='hidden' value='" + cusID
 + "'>" + "</form></td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the Customer.";
 System.err.println(e.getMessage());
 }
 return output;
 }

public String updateCustomer(String cusID, String cusName, String cusAddress, String cusEmail,  String cusPhone)

{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE customer SET cusName=?,cusAddress=?,cusEmail=?,cusPhone=? WHERE cusID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values

preparedStmt.setString(1, cusName);
preparedStmt.setString(2, cusAddress);
preparedStmt.setString(3, cusEmail);
preparedStmt.setInt(4, Integer.parseInt(cusPhone));
preparedStmt.setInt(5, Integer.parseInt(cusID));

// execute the statement
preparedStmt.execute();
con.close();
output = "Updated successfully";
}
catch (Exception e)
{
output = "Error while updating the Customer.";
System.err.println(e.getMessage());
}
return output;
}
	public String deleteCustomer(String cusID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from customer where cusID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(cusID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the Customer.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	}