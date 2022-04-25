package com;




//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Complain;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


			@Path("/Complains")
public class ComplainService 
{
Complain cmpObj = new Complain();
			@GET
			@Path("/")
			@Produces(MediaType.TEXT_HTML)
public String readComplain()
{
return cmpObj.readComplain();
}
			@POST
			@Path("/")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces(MediaType.TEXT_PLAIN)
			public String insertComplain(@FormParam("cuscmID") String cuscmID,
			 @FormParam("accountNo") String accountNo,
			 @FormParam("cDate") String cDate,
			 @FormParam("descri") String descri)
			 
			 
			{
			 String output = cmpObj.insertComplain(cuscmID, accountNo, cDate, descri);
			return output;
			}

			@PUT
			@Path("/")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String updateBill(String ComplainData)
			{
			//Convert the input string to a JSON object
			JsonObject cpmplainObject = new JsonParser().parse(ComplainData).getAsJsonObject();
			//Read the values from the JSON object
			String complainID = cpmplainObject.get("complainID").getAsString();
			String cuscmID = cpmplainObject.get("cuscmID").getAsString();
			String accountNo = cpmplainObject.get("accountNo").getAsString();
			String cDate = cpmplainObject.get("cDate").getAsString();
			String descri = cpmplainObject.get("descri").getAsString();
		    String output = cmpObj.updateComplain(complainID, cuscmID, accountNo, cDate, descri);
			return output;
			}

			@DELETE
			@Path("/")
			@Consumes(MediaType.APPLICATION_XML)
			@Produces(MediaType.TEXT_PLAIN)
			public String deleteComplain(String ComplainData)
			{
			//Convert the input string to an XML document
			 Document doc = Jsoup.parse(ComplainData, "", Parser.xmlParser());

			//Read the value from the element <itemID>
			 String complainID = doc.select("complainID").text();
			 String output = cmpObj.deleteComplain(complainID);
			return output;
			}
			
			// Search
			@GET
			@Path("/profile/{complainID}")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			//@Produces(MediaType.TEXT_HTML)
			public String readprofile(@PathParam("complainID") String complainID) {
		
				return cmpObj.viewProfile(complainID);
			}

}