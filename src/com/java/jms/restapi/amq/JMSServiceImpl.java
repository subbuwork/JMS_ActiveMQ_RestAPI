package com.java.jms.restapi.amq;

import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSServiceImpl implements JMSSevice {
	 Logger logger = Logger.getLogger(JMSServiceImpl.class.getName());
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static String queueName = "test";
	ConnectionFactory conf=null;
	Connection conn =null;
	Session session =null;
	Destination destination =null;
	@Override
	public String getMessage() {
		//logger.entering();
		System.out.println("entering::JMSServiceImpl:::::,getMessage()");
		String message = null;
		try {
			//Creating connection factory...
			 conf = new ActiveMQConnectionFactory(url);
			 conn = conf.createConnection();
			 conn.start();
			
			 session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			 destination = session.createQueue(queueName);
			
			MessageConsumer consumer = session.createConsumer(destination);
			
			Message msg = consumer.receive();
			
			if(msg instanceof TextMessage){
				TextMessage tMessage = (TextMessage)msg;
				message = tMessage.getText();
				//logger.info("Returning message from active mq:::::"+message);
				System.out.println("Returning message from active mq:::::"+message);
			}
			
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//logger.exiting("JMSServiceImpl.class",getMessage());
		System.out.println("exiting::JMSServiceImpl:::::,getMessage()");
		
		return message;
	}

}
