/**
 * @author S.H.B Dasanayake
 *         IT19778754
 * 
 */

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee
    {
	//A common method to connect to the DB
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
	//Employee Insert
	public String insertEmployee(String employeeName, String employeeEmail, String empAge,  String phone, String nic)
				 {
				 String output = "";
						 try
						 {
						 Connection con = connect();
						 if (con == null)
						 {return "Error while connecting to the database for inserting."; }
						 // create a prepared statement
						 String query = "insert into employees (`employeeNumber`,`employeeName`,`employeeEmail`,`empAge`,`phone`,`nic`)"
						 		+ "values (?, ?, ?, ?, ?,?)";
						 PreparedStatement preparedStmt = con.prepareStatement(query);
						 
						 // binding values
						 
						 	preparedStmt.setInt(1, 0);
							preparedStmt.setString(2, employeeName);
							preparedStmt.setString(3, employeeEmail);
							preparedStmt.setInt(4, Integer.parseInt(empAge));
							preparedStmt.setInt(5, Integer.parseInt(phone));
							preparedStmt.setString(6, nic);
							
						 // execute the statement
						 
						 preparedStmt.execute();
						 con.close();
						 output = "Employee Inserted successfully";
						 }
						 catch (Exception e)
						 {
						 output = "Error while inserting the Employee.";
						 System.err.println(e.getMessage());
						 }
						 return output;
		 }
	//View Employee Details
	public String readEmployees()
						 {
						 String output = "";
						 try
						 {
						 Connection con = connect();
						 if (con == null)
						 {return "Error while connecting to the database for reading."; }
						 
						 	// Prepare the html table to be displayed
						 
							 output = "<table border='1'><tr><th>Employee Name</th><th>Email</th>" +
										 "<th>Employee Age</th>" +
										 "<th>Phone Number</th>" +
										 "<th>NIC</th>" +
										 "<th>Update</th><th>Delete</th></tr>";
							
							 String query = "select * from employees";
							 Statement stmt = con.createStatement();
							 ResultSet rs = stmt.executeQuery(query);
						 
						 // iterate through the rows in the result set
						 while (rs.next())
						 {
							 String employeeNumber = Integer.toString(rs.getInt("employeeNumber"));
							 String employeeName = rs.getString("employeeName");
							 String employeeEmail = rs.getString("employeeEmail");
							 String empAge = Integer.toString(rs.getInt("empAge"));
							 String phone = Integer.toString(rs.getInt("phone"));
							 String nic = rs.getString("nic");
							 
							 // Add into the html table
							 output += "<tr><td>" + employeeName + "</td>";
							 output += "<td>" + employeeEmail + "</td>";
							 output += "<td>" + empAge + "</td>";
							 output += "<td>" + phone + "</td>";
							 output += "<td>" + nic + "</td>";
							 
							 // buttons
							 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							 + "<td><form method='post' action='items.jsp'>"
							 + "<input name='btnRemove' type='submit' value='Delete' class='btn btn-danger'>"
							 + "<input name='employeeNumber' type='hidden' value='" + employeeNumber
							 + "'>" + "</form></td></tr>";
						 }
							 con.close();
							 // Complete the html table
							 output += "</table>";
						 }
						 catch (Exception e)
						 {
							 output = "Error while reading the Employee.";
							 System.err.println(e.getMessage());
						 }
						 return output;
						 }
    //Employee Details Update
	public String updateEmployee(String employeeNumber, String employeeName, String employeeEmail, String empAge,  String phone, String nic)

						{
						String output = "";
						try
						{
						Connection con = connect();
						if (con == null)
						{return "Error while connecting to the database for updating."; }
							// create a prepared statement
							String query = "UPDATE employees SET employeeName=?,employeeEmail=?,empAge=?,phone=?,nic=? WHERE employeeNumber=?";
							PreparedStatement preparedStmt = con.prepareStatement(query);
							// binding values
							preparedStmt.setString(1, employeeName);
							preparedStmt.setString(2, employeeEmail);
							preparedStmt.setInt(3, Integer.parseInt(empAge));
							preparedStmt.setInt(4, Integer.parseInt(phone));
							preparedStmt.setString(5, nic);
							preparedStmt.setInt(6, Integer.parseInt(employeeNumber));
							// execute the statement
							preparedStmt.execute();
							con.close();
							output = "Employee DetailsUpdated successfully";
						}
						catch (Exception e)
						{
							output = "Error while updating the Employee.";
							System.err.println(e.getMessage());
						}
						return output;
						}
	//Employee Details Delete
	public String deleteEmployee(String employeeNumber)
						 {
						 String output = "";
						 try
						 {
						 Connection con = connect();
						 if (con == null)
						 {return "Error while connecting to the database for deleting."; }
							 // create a prepared statement
							 String query = "delete from employees where employeeNumber=?";
							 PreparedStatement preparedStmt = con.prepareStatement(query);
							 // binding values
							 preparedStmt.setInt(1, Integer.parseInt(employeeNumber));
							 // execute the statement
							 preparedStmt.execute();
							 con.close();
							 output = "Employee Deleted successfully";
						 }
						 catch (Exception e)
						 {
							 output = "Error while deleting the Employee.";
							 System.err.println(e.getMessage());
						 }
						 return output;
						 }
	
	//Search profile Details
	public String viewProfile(String employeeNumber) {


		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = "<table border='1'><tr><th>Employee Number</th><th>Employee Name</th>"
					+ "<th>Email</th>"
					+ "<th>Employee Age</th>"
					+ "<th>Phone Number</th>"
					+ "<th>NIC</th>"
					+ "<th>Update</th><th>Delete</th></tr>";

			
			String query = "select *  from employees where employeeNumber=' " + employeeNumber + "'" ;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			 while (rs.next())
			 {
				 String employeeNumber1 = Integer.toString(rs.getInt("employeeNumber"));
				 String employeeName = rs.getString("employeeName");
				 String employeeEmail = rs.getString("employeeEmail");
				 String empAge = Integer.toString(rs.getInt("empAge"));
				 String phone = Integer.toString(rs.getInt("phone"));
				 String nic = rs.getString("nic");
				 
				 // Add into the html table
				 output += "<tr><td>" + employeeNumber + "</td>";
				 output += "<td>" + employeeName + "</td>";
				 output += "<td>" + employeeEmail + "</td>";
				 output += "<td>" + empAge + "</td>";
				 output += "<td>" + phone + "</td>";
				 output += "<td>" + nic + "</td>";
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='items.jsp'>"
				 + "<input name='btnRemove' type='submit' value='Delete' class='btn btn-danger'>"
				 + "<input name='employeeNumber' type='hidden' value='" + employeeNumber
				 + "'>" + "</form></td></tr>";
			 }
				 con.close();
				 // Complete the html table
				 output += "</table>";
			 }
			 catch (Exception e)
			 {
				 output = "Error while reading the Employee.";
				 System.err.println(e.getMessage());
			 }
			 return output;
		}

	
}