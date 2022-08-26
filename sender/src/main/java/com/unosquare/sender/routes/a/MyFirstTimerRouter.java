package com.unosquare.sender.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessingComponent loggingProcessingComponent;

    @Override
    public void configure() throws Exception {

        from("timer:first-endpoint") //timer endpoint
                .log("${body}")
                .transform().constant("My constant message") //change the body
                .log("${body}")
//                .transform().constant("Time now is " + LocalDateTime.now())
//                .bean("getCurrentTimeBean")
                .bean(getCurrentTimeBean) //change the body
                .log("${body}")
                .bean(loggingProcessingComponent)//change the body
                .log("${body}")
                .process(new SimpleLoggingProcessor()) // not changes the body
                .to("log:first-timer");
    }
}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time now is " + LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessingComponent {

    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
    public void process(String message) {
        logger.info("SimpleLoggingProcessingComponent {}", message);
    }
}
