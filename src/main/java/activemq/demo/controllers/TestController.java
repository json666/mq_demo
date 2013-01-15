package activemq.demo.controllers;

import activemq.demo.components.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    private MessageProducer messageProducer;

    @RequestMapping("/sendMessage")
    public String sendMessage() {
        messageProducer.sendMessage("Test" + System.currentTimeMillis());
        return "success";
    }
}
