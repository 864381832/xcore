package com.xwintop.apache.activemq;

import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/**
 * 发送消息方 User: liuwentao Time: 12-6-14 上午11:29
 */
public class MessageSender extends Thread {
	public static void main(String args[]) throws Exception {
		String[] configLocations = new String[] { "test/applicationContext.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
		JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
		Destination destination = (Destination) context.getBean("destination");
		for (int i = 1; i < 10; i++) {
			System.out.println("发送 i=" + i);
			// 消息产生者
			MyMessageCreator myMessageCreator = new MyMessageCreator();
			myMessageCreator.n = i;
			jmsTemplate.send(destination, myMessageCreator);
			sleep(100);// 10秒后发送下一条消息
		}
	}
}
