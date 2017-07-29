package com.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsMessageListenerExample {
	
	private static String URL = "tcp://localhost:61616";
	
	public void sendMessage(String name, String city) {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue("TekQueue");

			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new JMSMessageListener());
			connection.start();

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		JmsMessageListenerExample d = new JmsMessageListenerExample();
		d.sendMessage("gautham", "duluth");
	}
}
