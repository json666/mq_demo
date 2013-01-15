package activemq.demo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

public class Sender {
    public static void main(String[] args) throws JMSException, InterruptedException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("test.queue");

        MessageProducer producer = session.createProducer(destination);
        for(int i=0; i<3; i++) {
            Message message = session.createTextMessage("Test message text of Message Body");
            message.setJMSType("message type of Message header");
            long currentTime = new Date().getTime();
            System.out.println("The currentTime value is:" + new Date(currentTime));
            message.setLongProperty("test_property", currentTime);


            Thread.sleep(1000);
            producer.send(message);
        }
        session.commit();
        session.close();
        connection.close();
    }

}
