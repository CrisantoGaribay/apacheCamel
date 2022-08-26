package com.unosquare.sender.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class KafkaReceiverRouter extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        from("kafka:myKafkaTopic")
                .to("log:received-message-from-active-mq")
                .to("log:received-message-from-kafka");
    }
}

