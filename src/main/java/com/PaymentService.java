package com;



//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Payment;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


			@Path("/Payments")
public class PaymentService 
{
Payment payObj = new Payment();
			@GET
			@Path("/")
			@Produces(MediaType.TEXT_HTML)
public String readItems()
{
return payObj.readPayments();
}
			@POST
			@Path("/")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces(MediaType.TEXT_PLAIN)
			public String insertPayment(@FormParam("cardName") String cardName,
			 @FormParam("cardNo") String cardNo,
			 @FormParam("expDate") String expDate,
			 @FormParam("cvv") String cvv,
			 @FormParam("cusID") String cusID)
			{
			 String output = payObj.insertPayment(cusID, cardName, cardNo, expDate, cvv, cusID);
			return output;
			}

			@PUT
			@Path("/")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String updatePayment(String PaymentData)
			{
			//Convert the input string to a JSON object
			JsonObject paymentObject = new JsonParser().parse(PaymentData).getAsJsonObject();
			//Read the values from the JSON object
			String paymentID = paymentObject.get("paymentID").getAsString();
			String cardName = paymentObject.get("cardName").getAsString();
			String cardNo = paymentObject.get("cardNo").getAsString();
			String expDate = paymentObject.get("expDate").getAsString();
			String cvv = paymentObject.get("cvv").getAsString();
			String cusID = paymentObject.get("cusID").getAsString();
			String output = payObj.updatePayment(paymentID,cardName, cardNo, expDate, cvv, cusID);
			return output;
			}

			@DELETE
			@Path("/")
			@Consumes(MediaType.APPLICATION_XML)
			@Produces(MediaType.TEXT_PLAIN)
			public String deletePayment(String PaymentData)
			{
			//Convert the input string to an XML document
			 Document doc = Jsoup.parse(PaymentData, "", Parser.xmlParser());

			//Read the value from the element <itemID>
			 String paymentID = doc.select("paymentID").text();
			 String output = payObj.deletePayment(paymentID);
			return output;
			}

}
