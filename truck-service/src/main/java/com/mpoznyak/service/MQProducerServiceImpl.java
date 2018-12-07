package com.mpoznyak.service;

import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.service.api.MQProducerService;
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
public class MQProducerServiceImpl implements MQProducerService {

    private static final String EXCHANGE_NAME = "truck-service";
    private static final Logger logger = Logger.getLogger(MQProducerServiceImpl.class);

    @Override
    @Loggable
    public void produceMessage(String msg) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("rabbitmq");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(EXCHANGE_NAME, false, false, false, null);
            channel.basicPublish("", EXCHANGE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));

            logger.info(" [ X ] PRODUCE MESSAGE:  \' " + msg + " \'");
        }
    }
}
