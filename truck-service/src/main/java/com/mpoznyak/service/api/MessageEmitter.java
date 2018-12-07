package com.mpoznyak.service.api;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 17:48
 */
public interface MessageEmitter {

    void produceMessage(String operationName, Long id);
    void produceMessage(String operationName);
}
