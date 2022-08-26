package com.unosquare.sender.routes.patterns;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DynamicRouterBean {

    Logger logger = LoggerFactory.getLogger(DynamicRouterBean.class);

    int invocations;

    public String decideTheNextEndpoint(
            @ExchangeProperties Map<String, String> properties,
            @Headers Map<String, String> headers,
            @Body String body
            ) {

        logger.info("PROPERTIES: {}, HEADERS: {}, BODY: {}", properties, headers, body);

        invocations++;
        if (invocations % 3 == 0)
            return "direct:endpoint1";
        if (invocations % 3 == 1)
            return "direct:endpoint2,direct:endpoint3";

        return null;

    }
}
