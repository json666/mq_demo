package activemq.demo.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

@Component
public class MessageProducer {
    @Autowired
    @Qualifier("myJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("myJmsTemplate2")
    private JmsTemplate jmsTemplate2;

    public void sendMessage(final String message) {
        sendMessage1(message);
        this.jmsTemplate2.convertAndSend(message);
    }

    private void sendMessage1(String message) {
        this.jmsTemplate.convertAndSend(message);
//        throw new RuntimeException("a");
    }

}