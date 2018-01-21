package com.restws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.db.dao.GcdNumbersDAOImpl;
import com.db.models.GcdNumbers;
import com.utils.CalculateGcd;

@Path("mygcd")
public class RestWebservices {
	
	public static final Logger logger = LoggerFactory.getLogger(RestWebservices.class);
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("elementsList")
	public List<Integer> list()
	{
		//here write the code to fetch from databse
		//Added dependency jar to print json values
		//need to write JMS code here
		//return Arrays.asList(1,2);
		
		//Store in db
		GcdNumbersDAOImpl impl = new GcdNumbersDAOImpl();		
		List<GcdNumbers> gcdNumbers = impl.getGcdNumbers();
		List<Integer> gcdInts = new ArrayList<Integer>();
		
		for(GcdNumbers gcd : gcdNumbers) {
			gcdInts.add(gcd.getNum1());
			gcdInts.add(gcd.getNum2());
		}
		
		return gcdInts ;
	}
	
	@POST
	@Path("setNumbers")
	public String push(GcdNumbers gcd) {
		
		
		Response res;
		logger.info("Executing setNumbers webservice call");
		//Calculate GCD value
		CalculateGcd calGcd = new CalculateGcd(gcd.getNum1(), gcd.getNum2());
		gcd.setGcdValue(calGcd.calGcdValue());		
		
		
		//Store in db
		GcdNumbersDAOImpl impl = new GcdNumbersDAOImpl();		
		String result  = impl.storeNumsAndGcd(gcd);
		
		if(result.equals("success")) {
			String messageToQueue=gcd.getNum1()+":"+gcd.getNum2();
			SendMessageServlet xu = new SendMessageServlet();
			System.out.println("Sending to Message Queue: "+messageToQueue);
			xu.sendMessage(messageToQueue);
			//ReceiveMessageServlet xi = new ReceiveMessageServlet();
			//xi.receiveMessages();
			res = Response.ok().build();
		}else {
			res = Response.serverError().build();
		}			
		
		return res.toString();
	}
	
}
