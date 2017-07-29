package com.topics;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.test.JMSMessageListener;
import com.test.Student;

public class JmsTopicExample {
	
	private static String URL = "tcp://localhost:61616";
	
	public void sendMessage(String name, String city) {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
			Connection connection = connectionFactory.createConnection();
			
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Topic topic = session.createTopic("TekTopic");

			MessageConsumer consumer1 = session.createConsumer(topic);
			consumer1.setMessageListener(new StudentNameListener());
			
			MessageConsumer consumer2 = session.createConsumer(topic);
			consumer2.setMessageListener(new StudentCityListener());
			
			connection.start();
			
			
			MessageProducer producer = session.createProducer(topic);

			Student s = new Student(name, city);

			ObjectMessage message = session.createObjectMessage(s);

			producer.send(message);

			
			Thread.sleep(10000);
			
			session.close();
			

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		JmsTopicExample d = new JmsTopicExample();
		d.sendMessage("gautham", "duluth");
	}
}
