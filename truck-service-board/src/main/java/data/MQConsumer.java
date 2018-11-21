package data;

import bean.BoardBean;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import javax.ejb.EJB;
import logging.annotation.Loggable;
import org.apache.log4j.Logger;

/**
 * Created by Max Poznyak
 * on 11/21/18  at 03:17
 */

public class MQConsumer {

    private static final String EXCHANGE_NAME = "truck-service";
    private static final Logger logger = Logger.getLogger(MQConsumer.class);

    private Channel channel;
    private Connection connection;
    private DataLoader dataLoader = new DataLoader();
    private DataAggregator dataAggregator = new DataAggregator();

    @Loggable
    public void start() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(EXCHANGE_NAME, false, false, false, null);
        System.out.println("Receive message");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException{
                String message = new String(body, StandardCharsets.UTF_8);
                logger.info(" [x] Received '" + message + "'");
                if (message.contains("Create") || message.contains("Delete") || message.contains("Update")) {

                    dataAggregator.setDrivers(dataLoader.getDrivers());
                    dataAggregator.setTrucks(dataLoader.getTrucks());
                    dataAggregator.setOrders(dataLoader.getOrders());
                }
            }
        };
        channel.basicConsume(EXCHANGE_NAME, true, consumer);
    }

    @Loggable
    public void stop() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
