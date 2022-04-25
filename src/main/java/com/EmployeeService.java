/**
 * @author S.H.B Dasanayake
 *         IT19778754
 * 
 */

package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Employee;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


			@Path("/Employees")
public class EmployeeService 
{
Employee empObj = new Employee();

	//View Employee Details
			@GET
			@Path("/")
			@Produces(MediaType.TEXT_HTML)
			public String readEmployees()
			{
			return empObj.readEmployees();
			}
			
	//Insert Employee
			@POST
			@Path("/")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces(MediaType.TEXT_PLAIN)
			public String insertItem(@FormParam("employeeName") String employeeName,
			 @FormParam("employeeEmail") String employeeEmail,
			 @FormParam("empAge") String empAge,
			 @FormParam("phone") String phone,
			 @FormParam("nic") String nic)
			{
			 String output = empObj.insertEmployee(employeeName, employeeEmail, empAge, phone, nic);
			return output;
			}
			
	//Update Employee Details
			@PUT
			@Path("/")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String updateEmployee(String EmployeeData)
			{
			//Convert the input string to a JSON object
			JsonObject employeeObject = new JsonParser().parse(EmployeeData).getAsJsonObject();
			//Read the values from the JSON object
			String employeeNumber = employeeObject.get("employeeNumber").getAsString();
			String employeeName = employeeObject.get("employeeName").getAsString();
			String employeeEmail = employeeObject.get("employeeEmail").getAsString();
			String empAge = employeeObject.get("empAge").getAsString();
			String phone = employeeObject.get("phone").getAsString();
			String nic = employeeObject.get("nic").getAsString();
			String output = empObj.updateEmployee(employeeNumber,employeeName, employeeEmail, empAge, phone, nic);
			return output;
			}
			
	//Delete Employee
			@DELETE
			@Path("/")
			@Consumes(MediaType.APPLICATION_XML)
			@Produces(MediaType.TEXT_PLAIN)
			public String deleteEmployee(String EmployeeData)
			{
			//Convert the input string to an XML document
			 Document doc = Jsoup.parse(EmployeeData, "", Parser.xmlParser());

			//Read the value from the element <itemID>
			 String employeeNumber = doc.select("employeeNumber").text();
			 String output = empObj.deleteEmployee(employeeNumber);
			return output;
			}
			
	// Search profile details
			@GET
			@Path("/profile/{employeeNumber}")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			//@Produces(MediaType.TEXT_HTML)
			public String readprofile(@PathParam("employeeNumber") String employeeNumber) {

				return empObj.viewProfile(employeeNumber);


			}


}