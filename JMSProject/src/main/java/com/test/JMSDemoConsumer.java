package com.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSDemoConsumer {

	private static String URL = "tcp://localhost:61616";

	public void getMessage() {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue("TekQueue");

			MessageConsumer consumer = session.createConsumer(destination);

			Message message = consumer.receive();

			if (message instanceof ObjectMessage) {
				ObjectMessage ObjectMessage = (ObjectMessage) message;
				Student s = (Student) ObjectMessage.getObject();
				System.out.println(s);
			}
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		JMSDemoConsumer d = new JMSDemoConsumer();
		d.getMessage();
	}

}
