package data;

import bean.DataStateBean;
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
    private DataStateListener listener = DataStateListener.getInstance();

    public void start() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(EXCHANGE_NAME, false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException{
                String message = new String(body, StandardCharsets.UTF_8);
                logger.info(" [x] Received " + message);
                if (message.contains("Created") || message.contains("Delete")
                        || message.contains("Updated") || message.contains("Update")
                        ||message.contains("Create")) {

                    logger.info("DATA RECEIVED");
                    listener.dataIsNotActual();
                }
            }
        };
        channel.basicConsume(EXCHANGE_NAME, true, consumer);
    }

    public void stop() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
