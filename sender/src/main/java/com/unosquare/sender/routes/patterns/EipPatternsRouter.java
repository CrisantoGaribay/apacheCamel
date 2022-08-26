package com.unosquare.sender.routes.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class EipPatternsRouter extends RouteBuilder {

    @Autowired
    private SplitterComponent splitterComponent;

    @Autowired
    private DynamicRouterBean dynamicRouterBean;

    @Override
    public void configure() throws Exception {
        //Pipeline
        //Content based routing -- choice()
        //Multicast

        //MULTICAST PATTERN
//        from("timer:multicast?period=10000")
//                .multicast()
//                .to("log:something1", "log:something2", "log:something3");

//        from("file:files/csv")
//                .unmarshal().csv()
//                .split(body())
//                .to("activemq:split-queue");


        //SPLITTER
        //message, Message2, Message3
//        from("file:files/csv")
//                .convertBodyTo(String.class)
////                .split(body(), ",")
//                .split(method(splitterComponent))
//                .to("activemq:split-queue");

        //AGGREGATE
        //Messages => aggregate => endpoint
        //to, 3
//        from("file:files/aggregate-json")
//                .convertBodyTo(String.class)
//                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
//                .aggregate(simple("${body.to}"), new ArrayListAggregationStrategy())
//                .completionSize(3)
////                .completionTimeout(HIGHEST)
//                .to("log:aggregate-json");


        //ROUTING SLIP
        String routingSlip = "direct:endpoint1,direct:endpoint2";

//        from("timer:routingSlip?period=10000")
//                .transform().constant("My Message is Hardcoded")
//                .routingSlip(simple(routingSlip));

        //DYNAMIC ROUTING
        from("timer:routingSlip?period={{timePeriod}}")
                .transform().constant("My Message is Hardcoded")
                .dynamicRouter(method(dynamicRouterBean));

        from("direct:endpoint1")
                .to("{{endpoint-for-login}}");
        from("direct:endpoint2")
                .to("log:directendpoint2");
        from("direct:endpoint3")
                .to("log:directendpoint3");


    }
}

@Component
class SplitterComponent {
    public List<String> splitInput(String body) {

        return List.of("ABC", "DFG", "123");
    }
}
