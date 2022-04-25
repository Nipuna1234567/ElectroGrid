package model;





import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill
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
public String insertBill(String cusbID, String paymentID, String accountNo,  String bDate, String ppUnit, String usedUnit, String tbAmount)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = "insert into bill (`billID`,`cusbID`,`paymentID`,`accountNo`,`bDate`,`ppUnit`,`usedUnit`,`tbAmount`)"
 		+ "values (?, ?, ?, ?, ?, ?, ?,?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 
 // binding values
 
 	preparedStmt.setInt(1, 0);
 	preparedStmt.setInt(2, Integer.parseInt(cusbID));
	preparedStmt.setInt(3, Integer.parseInt(paymentID));
	preparedStmt.setInt(4, Integer.parseInt(accountNo));
	preparedStmt.setString(5, bDate);
	preparedStmt.setInt(6, Integer.parseInt(ppUnit));
	preparedStmt.setInt(7, Integer.parseInt(usedUnit));
	preparedStmt.setInt(8, Integer.parseInt(tbAmount));
	
	
 // execute the statement
 
 preparedStmt.execute();
 con.close();
 output = "Bill Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the Bill.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String readBills()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 
 // Prepare the html table to be displayed
 
 output = "<table border='1'><tr><th>Customer ID</th><th>Payment ID</th>" +
			 "<th>Account No</th>" +
			 "<th>Issued Date</th>" +
			 "<th>Price Per Unit</th>" +
			 "<th>Used Unit</th>" +
			 "<th>Total Amount</th>" +
			 "<th>Update</th><th>Delete</th></tr>";

 String query = "select * from bill";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 
 // iterate through the rows in the result set
 while (rs.next())
 {

	 String billID = Integer.toString(rs.getInt("billID"));
	 String cusbID = Integer.toString(rs.getInt("cusbID"));
	 String paymentID = Integer.toString(rs.getInt("paymentID"));
	 String accountNo = Integer.toString(rs.getInt("accountNo"));
	 String bDate = rs.getString("bDate");
	 String ppUnit = Integer.toString(rs.getInt("ppUnit"));
	 String usedUnit = rs.getString("usedUnit");
	 String tbAmount = Integer.toString(rs.getInt("tbAmount"));
 
 
 // Add into the html table
 output += "<tr><td>" + cusbID + "</td>";
 output += "<td>" + paymentID + "</td>";
 output += "<td>" + accountNo + "</td>";
 output += "<td>" + bDate + "</td>";
 output += "<td>" + ppUnit + "</td>";
 output += "<td>" + usedUnit + "</td>";
 output += "<td>" + tbAmount + "</td>";
 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"
 + "<input name='btnRemove' type='submit' value='Delete' class='btn btn-danger'>"
 + "<input name='billID' type='hidden' value='" + billID
 + "'>" + "</form></td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the Bill.";
 System.err.println(e.getMessage());
 }
 return output;
 }

public String updateBill(String billID, String cusbID, String paymentID, String accountNo,  String bDate, String ppUnit, String usedUnit, String tbAmount)

{
String output = "";
try

{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE bill SET cusbID=?,paymentID=?,accountNo=?,bDate=?,ppUnit=?,usedUnit=?,tbAmount=? WHERE billID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values

preparedStmt.setInt(1, Integer.parseInt(cusbID));
preparedStmt.setInt(2, Integer.parseInt(paymentID));
preparedStmt.setInt(3, Integer.parseInt(accountNo));
preparedStmt.setString(4, bDate);
preparedStmt.setInt(5, Integer.parseInt(ppUnit));
preparedStmt.setInt(6, Integer.parseInt(usedUnit));
preparedStmt.setInt(7, Integer.parseInt(tbAmount));
preparedStmt.setInt(8, Integer.parseInt(billID));
// execute the statement
preparedStmt.execute();
con.close();
output = "Updated successfully";
}
catch (Exception e)
{
output = "Error while updating the Bill.";
System.err.println(e.getMessage());
}
return output;
}
	public String deleteBill(String billID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from bill where billID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(billID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the Bill.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	//Search 
	public String viewProfile(String billID) {


		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = "<table border=\"1\"><tr><th>Bill ID</th><th>Customer ID</th><th>Payment ID</th><th>Account No</th><th>Issued Date</th><th>Price Per Unit</th><th>Used Unit</th><th>Total Amount</th><th>Update</th><th>Delete</th></tr>";

			String query = "select *  from bill where billID=' " + billID + "'" ;


			Statement stmt = con.createStatement();


			ResultSet rs = stmt.executeQuery(query);

			
			 // iterate through the rows in the result set
			 while (rs.next())
			 {

				 String billID1 = Integer.toString(rs.getInt("billID"));
				 String cusbID = Integer.toString(rs.getInt("cusbID"));
				 String paymentID = Integer.toString(rs.getInt("paymentID"));
				 String accountNo = Integer.toString(rs.getInt("accountNo"));
				 String bDate = rs.getString("bDate");
				 String ppUnit = Integer.toString(rs.getInt("ppUnit"));
				 String usedUnit = rs.getString("usedUnit");
				 String tbAmount = Integer.toString(rs.getInt("tbAmount"));
			 
			 
			 // Add into the html table
			 output += "<tr><td>" + billID + "</td>";
			 output += "<td>" + cusbID + "</td>";
			 output += "<td>" + paymentID + "</td>";
			 output += "<td>" + accountNo + "</td>";
			 output += "<td>" + bDate + "</td>";
			 output += "<td>" + ppUnit + "</td>";
			 output += "<td>" + usedUnit + "</td>";
			 output += "<td>" + tbAmount + "</td>";
			 
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='items.jsp'>"
			 + "<input name='btnRemove' type='submit' value='Delete' class='btn btn-danger'>"
			 + "<input name='billID' type='hidden' value='" + billID
			 + "'>" + "</form></td></tr>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
			 }
			 catch (Exception e)
			 {
			 output = "Error while reading the Bill.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
	}