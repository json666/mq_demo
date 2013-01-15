package activemq.demo.components;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {
        "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageProducerTest {

    @Autowired
    private MessageProducer messageProducer;

    @Test
    public void testSendMessage() throws Exception {
        messageProducer.sendMessage("test");
    }

    @Test
    public void testGetMessage() throws Exception {
        Thread.sleep(2 * 60 * 1000);
    }

}
