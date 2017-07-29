package com.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSDemoProducer {

	private static String URL = "tcp://localhost:61616";

	public void sendMessage(String name, String city) {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue("TekQueue");

			MessageProducer producer = session.createProducer(destination);

			Student s = new Student(name, city);

			ObjectMessage message = session.createObjectMessage(s);

			producer.send(message);

			connection.close();

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		JMSDemoProducer d = new JMSDemoProducer();
		d.sendMessage("gautham", "duluth");
	}

}
