package com.topics;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import com.test.Student;

public class StudentNameListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage ObjectMessage = (ObjectMessage) message;
				Student s = (Student) ObjectMessage.getObject();
				System.out.println(s.getName());
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
