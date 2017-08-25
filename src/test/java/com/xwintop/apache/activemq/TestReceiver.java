package com.xwintop.apache.activemq;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @ClassName: TestReceiver
 * @Description: 测试消息接收
 * @author: xufeng
 * @date: 2017年5月15日 下午2:49:32
 */
public class TestReceiver {
	public static void main(String[] args) {
		// ConnectionFactory ：连接工厂，JMS 用它创建连接
		ConnectionFactory connectionFactory;
		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection connection = null;
		// Session： 一个发送或接收消息的线程
		Session session;
		// Destination ：消息的目的地;消息发送给谁.
		Destination destination;
		// 消费者，消息接收者
		MessageConsumer consumer;
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			// 获取操作连接
//			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createQueue("testQueue");
			consumer = session.createConsumer(destination);
//			consumer.setMessageListener(new MessageListener() {// 有事务限制
//				@Override
//				public void onMessage(Message message) {
//					try {
//						System.out.println(message.getJMSMessageID());
//						System.out.println(message.getJMSTimestamp());
//						if(message instanceof TextMessage){
//							System.out.println("TextMessage");
//						}else if(message instanceof ObjectMessage){
//							System.out.println("ObjectMessage");
//						}else if(message instanceof BytesMessage){
//							System.out.println("BytesMessage");
//						}else if(message instanceof MapMessage){
//							System.out.println("MapMessage");
//						}else if(message instanceof StreamMessage){
//							System.out.println("StreamMessage");
//						}
//						TextMessage textMessage = (TextMessage) message;
////						textMessage.acknowledge();
//						System.out.println(textMessage.getText());
//					} catch (JMSException e1) {
//						e1.printStackTrace();
//					}
////					try {
////						session.commit();
////					} catch (JMSException e) {
////						e.printStackTrace();
////					}
//				}
//			});
//			Thread.sleep(5000000);
//			另外一种接受方式 
			while (true) {
				// 设置接收者接收消息的时间，为了便于测试，这里谁定为100s
				TextMessage message = (TextMessage) consumer.receive(100000);
				if (null != message) {
					System.out.println("收到消息Id:"+message.getJMSMessageID()+",内容:" + message.getText());
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}
	}
}
