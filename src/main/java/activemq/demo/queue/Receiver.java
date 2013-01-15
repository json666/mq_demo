package activemq.demo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

public class Receiver {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        Connection connection = connectionFactory.createConnection();
        connection.start();

        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("test.queue");

        MessageConsumer consumer = session.createConsumer(destination);

//        receiveMessageByListener(session, consumer);
        getMessages(session, consumer);

        session.close();
        connection.close();
    }

    private static void getMessages(Session session, MessageConsumer consumer) throws JMSException {
        int i = 0;
        while (i < 3) {
            i++;
            Message message = consumer.receive();
            session.commit();
            printMessage(message);
        }
    }

    private static void receiveMessageByListener(final Session session, MessageConsumer consumer) throws Exception {
        consumer.setMessageListener(new MessageListener() {

            public void onMessage(Message msg) {
                try {
                    printMessage(msg);
                    session.commit();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(30000);
    }

    private static void printMessage(Message msg) throws JMSException {
        System.out.println("JMS Header Message Type：" + msg.getJMSType());
        System.out.println("JMS Message Property：" + new Date(msg.getLongProperty("test_property")));
        System.out.println("JMS Message Body：" + ((TextMessage) msg).getText());
    }
}
