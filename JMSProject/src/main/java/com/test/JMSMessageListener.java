package com.test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

public class JMSMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage ObjectMessage = (ObjectMessage) message;
				Student s = (Student) ObjectMessage.getObject();
				System.out.println(s);
			}
			else if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.println(textMessage.getText());
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
