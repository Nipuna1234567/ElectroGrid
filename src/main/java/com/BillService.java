package com;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Bill;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


			@Path("/Bills")
public class BillService 
{
Bill biObj = new Bill();
			@GET
			@Path("/")
			@Produces(MediaType.TEXT_HTML)
public String readBills()
{
return biObj.readBills();
}
			@POST
			@Path("/")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces(MediaType.TEXT_PLAIN)
			public String insertBill(@FormParam("cusbID") String cusbID,
			 @FormParam("paymentID") String paymentID,
			 @FormParam("accountNo") String accountNo,
			 @FormParam("bDate") String bDate,
			 @FormParam("ppUnit") String ppUnit,
			 @FormParam("usedUnit") String usedUnit,
			 @FormParam("tbAmount") String tbAmount)
			 
			{
			 String output = biObj.insertBill(cusbID, paymentID, accountNo, bDate, ppUnit, usedUnit, tbAmount);
			return output;
			}

			@PUT
			@Path("/")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String updateBill(String BillData)
			{
			//Convert the input string to a JSON object
			JsonObject billObject = new JsonParser().parse(BillData).getAsJsonObject();
			//Read the values from the JSON object
			String billID = billObject.get("billID").getAsString();
			String cusbID = billObject.get("cusbID").getAsString();
			String paymentID = billObject.get("paymentID").getAsString();
			String accountNo = billObject.get("accountNo").getAsString();
			String bDate = billObject.get("bDate").getAsString();
			String ppUnit = billObject.get("ppUnit").getAsString();
			String usedUnit = billObject.get("usedUnit").getAsString();
			String tbAmount = billObject.get("tbAmount").getAsString();
			String output = biObj.updateBill(billID, cusbID, paymentID, accountNo, bDate, ppUnit, usedUnit, tbAmount);
			return output;
			}

			@DELETE
			@Path("/")
			@Consumes(MediaType.APPLICATION_XML)
			@Produces(MediaType.TEXT_PLAIN)
			public String deleteBill(String BillData)
			{
			//Convert the input string to an XML document
			 Document doc = Jsoup.parse(BillData, "", Parser.xmlParser());

			//Read the value from the element <itemID>
			 String billID = doc.select("billID").text();
			 String output = biObj.deleteBill(billID);
			return output;
			}

}