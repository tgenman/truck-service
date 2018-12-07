package com.mpoznyak.service;

import com.mpoznyak.service.api.MQProducerService;
import com.mpoznyak.service.api.MessageEmitter;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 17:47
 */

@Service
public class MessageEmitterImpl implements MessageEmitter {

    @Autowired
    private MQProducerService mqProducerService;

    private static final Logger logger = Logger.getLogger(MessageEmitterImpl.class);


    @Override
    public void produceMessage(String operation, Long id) {
        try {
            mqProducerService.produceMessage(operation + " , id=" + id);
        } catch (IOException ioe) {
            logger.error("IOException during MQ producing: " + ioe.getMessage());
        } catch (TimeoutException te) {
            logger.error("TimeoutException during MQ producing: " + te.getMessage());
        }
    }

    @Override
    public void produceMessage(String operation) {
        try {
            mqProducerService.produceMessage(operation);
        } catch (IOException ioe) {
            logger.error("IOException during MQ producing: " + ioe.getMessage());
        } catch (TimeoutException te) {
            logger.error("TimeoutException during MQ producing: " + te.getMessage());
        }
    }

}
