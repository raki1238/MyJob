package com.restws;

import java.io.IOException;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

@Path("sendMessage")
public class SendMessageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4697247632239344363L;
	public static final Logger logger = LoggerFactory.getLogger(SendMessageServlet.class);

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
    	System.out.println("doGet() called");
    	String parameter = getTextParameter(httpServletRequest);
        sendMessage(parameter);
        writeResponse(httpServletResponse, parameter);
    }

    public void writeResponse(HttpServletResponse httpServletResponse,
			String parameter) throws IOException {
		httpServletResponse.setContentType("text/plain");
        httpServletResponse.getWriter().write(String.format("Sent message with content '%s'.", parameter));
	}

    public String getTextParameter(HttpServletRequest httpServletRequest) {
		String parameter = httpServletRequest.getParameter("text");
    	if(Strings.isNullOrEmpty(parameter)) {
    		parameter = (new Date()).toString();
    	}
		return parameter;
	}

    public void sendMessage(String text) {
        try {
            InitialContext initCtx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) initCtx.lookup("java:comp/env/jms/ConnectionFactory");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer((Destination) initCtx.lookup("java:comp/env/jms/queue/MyQueue"));

            TextMessage testMessage = session.createTextMessage();
            testMessage.setText(text);
            testMessage.setStringProperty("aKey", "someRandomTestValue");
            producer.send(testMessage);
            System.out.println("Successfully sent message.");
        } catch (Exception e) {
            System.out.println("Sending JMS message failed: "+e.getMessage());
        }
    }
}
