package com.mpoznyak.service;

import com.mpoznyak.logging.annotation.Loggable;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 23:13
 */

@Service
public class MQProducerService {

    private static final String EXCHANGE_NAME = "truck-service";
    private static final Logger logger = Logger.getLogger(MQProducerService.class);

    @Loggable
    public void produceMessage(String msg) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(EXCHANGE_NAME, false, false, false, null);
        channel.basicPublish("", EXCHANGE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));

        logger.info(" [ X ] PRODUCE MESSAGE:  \' " + msg + " \'");

        channel.close();
        connection.close();
    }
}
