package com.java.jms.restapi.amq;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/JMA-AQ-RestService")
public class JMS_AQ_RestService {
 //  Logger logger = Logger.getLogger(JMS_AQ_RestService.class.getName());
   JMSSevice jmsService = new JMSServiceImpl();
   @GET
   @Path("/getMessage")
   @Produces(MediaType.TEXT_PLAIN)
	public Response getMessage(){
		//logger.entering("JMS_AQ_RestService",getMessage());
		String message = null;
		message = jmsService.getMessage();
		//logger.exiting("JMS_AQ_RestService", getMessage());
		return Response.ok().entity(message).build();
	}

}
