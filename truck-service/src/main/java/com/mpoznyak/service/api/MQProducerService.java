package com.mpoznyak.service.api;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 17:22
 */

public interface MQProducerService {

    void produceMessage(String msg) throws IOException, TimeoutException;
}
