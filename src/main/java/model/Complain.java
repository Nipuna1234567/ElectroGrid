package model;







import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class Complain
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
public String insertComplain(String cuscmID, String accountNo, String cDate,String descri )
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = "insert into complain (`complainID`,`cuscmID`,`accountNo`,`cDate`,`descri`)"
 		+ "values (?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 
 // binding values
 
 	preparedStmt.setInt(1, 0);
 	preparedStmt.setInt(2, Integer.parseInt(cuscmID));
	preparedStmt.setInt(3, Integer.parseInt(accountNo));
	preparedStmt.setString(4, cDate);
	preparedStmt.setString(5, descri);
	
	
 // execute the statement
 
 preparedStmt.execute();
 con.close();
 output = "Complain Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the Complain.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String readComplain()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 
 // Prepare the html table to be displayed
 
 output = "<table border='1'><tr><th>Customer ID</th><th>Account No</th>" +
			 "<th>Date</th>" +
			 "<th>Description </th>" +
			 "<th>Update</th><th>Delete</th></tr>";

 String query = "select * from complain";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 
 // iterate through the rows in the result set
 while (rs.next())
 {

	 String complainID = Integer.toString(rs.getInt("complainID"));
	 String cuscmID = Integer.toString(rs.getInt("cuscmID"));
	 String accountNo = Integer.toString(rs.getInt("accountNo"));
	 String cDate = rs.getString("cDate");
	 String descri = rs.getString("descri");

 
 
 // Add into the html table
 output += "<tr><td>" + cuscmID + "</td>";
 output += "<td>" + accountNo + "</td>";
 output += "<td>" + cDate + "</td>";
 output += "<td>" + descri + "</td>";

 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"
 + "<input name='btnRemove' type='submit' value='Delete' class='btn btn-danger'>"
 + "<input name='complainID' type='hidden' value='" + complainID
 + "'>" + "</form></td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the Complain.";
 System.err.println(e.getMessage());
 }
 return output;
 }

public String updateComplain(String complainID, String cuscmID, String accountNo, String cDate,  String descri )

{
String output = "";
try

{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE complain SET cuscmID=?,accountNo=?,cDate=?,descri=? WHERE complainID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values

preparedStmt.setInt(1, Integer.parseInt(cuscmID));;
preparedStmt.setInt(2, Integer.parseInt(accountNo));
preparedStmt.setString(3, cDate);
preparedStmt.setString(4, descri);
preparedStmt.setInt(5, Integer.parseInt(complainID));
// execute the statement
preparedStmt.execute();
con.close();
output = "Updated successfully";
}
catch (Exception e)
{
output = "Error while updating the Complain.";
System.err.println(e.getMessage());
}
return output;
}
	public String deleteComplain(String complainID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from complain where complainID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(complainID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the complain.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	//Search 
	public String viewProfile(String complainID) {


		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = "<table border=\"1\"><tr><th>Complain ID</th><th>Customer ID</th><th>Account No</th><th>Date</th><th>Description</th><th>Update</th><th>Delete</th></tr>";

			String query = "select *  from complain where complainID=' " + complainID + "'" ;


			Statement stmt = con.createStatement();


			ResultSet rs = stmt.executeQuery(query);

			 // iterate through the rows in the result set
			 while (rs.next())
			 {

				 String complainID1 = Integer.toString(rs.getInt("complainID"));
				 String cuscmID = Integer.toString(rs.getInt("cuscmID"));
				 String accountNo = Integer.toString(rs.getInt("accountNo"));
				 String cDate = rs.getString("cDate");
				 String descri = rs.getString("descri");

			 
			 
			 // Add into the html table
			 output += "<tr><td>" + complainID + "</td>";
			 output += "<td>" + accountNo + "</td>";
			 output += "<td>" + accountNo + "</td>";
			 output += "<td>" + cDate + "</td>";
			 output += "<td>" + descri + "</td>";

			 
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='items.jsp'>"
			 + "<input name='btnRemove' type='submit' value='Delete' class='btn btn-danger'>"
			 + "<input name='complainID' type='hidden' value='" + complainID
			 + "'>" + "</form></td></tr>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
			 }
			 catch (Exception e)
			 {
			 output = "Error while reading the Complain.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
	
		

}