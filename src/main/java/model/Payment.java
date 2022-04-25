package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment
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

public String insertPayment(String cardName, String cardNo, String expDate, String cvv,String cusID)



 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = "insert into payment (`paymentID`,`cardName`,`cardNo`,`expDate`,`cvv`,`cusID`)"
 		+ "values (?, ?, ?, ?, ?,?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 
 // binding values
 
 	preparedStmt.setInt(1, 0);
	preparedStmt.setString(2, cardName);
	preparedStmt.setInt(3, Integer.parseInt(cardNo));
	preparedStmt.setString(4, expDate);
	preparedStmt.setInt(5, Integer.parseInt(cvv));
	preparedStmt.setInt(6, Integer.parseInt(cusID));
	
 // execute the statement
 
 preparedStmt.execute();
 con.close();
 output = "Payment Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the Payment.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String readPayments()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 
 // Prepare the html table to be displayed
 
 output = "<table border='1'><tr><th>Card Type</th><th>Card Number</th>" +
			 "<th>Expire Date</th>" +
			 "<th>CVV</th>" +
			 "<th>Customer ID</th>" +
			 "<th>Update</th><th>Delete</th></tr>";

 String query = "select * from payment";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 
 // iterate through the rows in the result set
 while (rs.next())
 {
 String paymentID = Integer.toString(rs.getInt("paymentID"));
 String cardName = rs.getString("cardName");
 String cardNo = Integer.toString(rs.getInt("cardNo"));
 String expDate = rs.getString("expDate");
 String cvv = Integer.toString(rs.getInt("cvv"));
 String cusID = Integer.toString(rs.getInt("cusID"));
 
 
 // Add into the html table
 output += "<tr><td>" + cardName + "</td>";
 output += "<td>" + cardNo + "</td>";
 output += "<td>" + expDate + "</td>";
 output += "<td>" + cvv + "</td>";
 output += "<td>" + cusID + "</td>";
 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"
 + "<input name='btnRemove' type='submit' value='Delete' class='btn btn-danger'>"
 + "<input name='paymentID' type='hidden' value='" + paymentID
 + "'>" + "</form></td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the Payment.";
 System.err.println(e.getMessage());
 }
 return output;
 }

public String updatePayment(String paymentID, String cardName, String cardNo, String expDate, String cvv,String cusID)

{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE payment SET cardName=?,cardNo=?,expDate=?,cvv=?,cusID=? WHERE paymentID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values

preparedStmt.setString(1, cardName);
preparedStmt.setInt(2, Integer.parseInt(cardNo));
preparedStmt.setString(3, expDate);
preparedStmt.setInt(4, Integer.parseInt(cvv));
preparedStmt.setInt(5, Integer.parseInt(cusID));
preparedStmt.setInt(6, Integer.parseInt(paymentID));
// execute the statement
preparedStmt.execute();
con.close();
output = "Updated successfully";
}
catch (Exception e)
{
output = "Error while updating the Payment.";
System.err.println(e.getMessage());
}
return output;
}
	public String deletePayment(String paymentID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from payment where paymentID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(paymentID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the Payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	}