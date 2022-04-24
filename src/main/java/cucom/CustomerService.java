package cucom;

import cumodel.Customer;










//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


			@Path("/Customers")
public class CustomerService 
{
Customer cusObj = new Customer();
			@GET
			@Path("/")
			@Produces(MediaType.TEXT_HTML)
public String readCustomers()
{
return cusObj.readCustomers();
}
			@POST
			@Path("/")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces(MediaType.TEXT_PLAIN)
			public String insertItem(@FormParam("cusName") String cusName,
			 @FormParam("cusAddress") String cusAddress,
			 @FormParam("cusEmail") String cusEmail,
			 @FormParam("cusPhone") String cusPhone)
			 
			{
			 String output = cusObj.insertCustomer(cusName, cusAddress, cusEmail, cusPhone);
			return output;
			}

			@PUT
			@Path("/")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String updateCustomer(String CustomerData)
			{
			//Convert the input string to a JSON object
			JsonObject customerObject = new JsonParser().parse(CustomerData).getAsJsonObject();
			//Read the values from the JSON object
			String cusID = customerObject.get("cusID").getAsString();
			String cusName = customerObject.get("cusName").getAsString();
			String cusAddress = customerObject.get("cusAddress").getAsString();
			String cusEmail = customerObject.get("cusEmail").getAsString();
			String cusPhone = customerObject.get("cusPhone").getAsString();
			String output = cusObj.updateCustomer(cusID, cusName, cusAddress, cusEmail, cusPhone);
			return output;
			}

			@DELETE
			@Path("/")
			@Consumes(MediaType.APPLICATION_XML)
			@Produces(MediaType.TEXT_PLAIN)
			public String deleteCustomer(String CustomerData)
			{
			//Convert the input string to an XML document
			 Document doc = Jsoup.parse(CustomerData, "", Parser.xmlParser());

			//Read the value from the element <itemID>
			 String cusID = doc.select("cusID").text();
			 String output = cusObj.deleteCustomer(cusID);
			return output;
			}

}