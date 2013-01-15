package activemq.demo.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer extends Thread {
    public static void main(String[] args) {
        startConsumer();
        startConsumer();
        startConsumer();
    }

    private static void startConsumer() {
        Consumer consumer = new Consumer();
        consumer.start();
    }

    @Override
    public void run() {
        try {

            final long id = Thread.currentThread().getId();
            System.out.println(id);
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            connection.setExceptionListener(new ExceptionListener() {
                @Override
                public void onException(JMSException exception) {

                }
            });

            // Create a Session
            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createTopic("test.topic");

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);


            Thread.sleep(30 * 1000);
//            // Wait for a message
            Message message = consumer.receive(1000);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Received: " + text+ " Thread id: " + id);
            } else {
                System.out.println("Received: " + message + " Thread id: " + id);
            }

            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }
}
