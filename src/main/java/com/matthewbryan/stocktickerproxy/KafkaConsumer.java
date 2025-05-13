package com.matthewbryan.stocktickerproxy;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaConsumer {

    private AppSocketServer appSocketServer;
    public KafkaConsumer() {
        // Constructor
        //this.appSocketServer = appSocketServer;
        System.out.println("KafkaConsumer initialized");
    }

 
}