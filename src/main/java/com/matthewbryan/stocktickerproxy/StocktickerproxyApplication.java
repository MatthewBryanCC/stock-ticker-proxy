package com.matthewbryan.stocktickerproxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class StocktickerproxyApplication {
	private static AppSocketServer appSocketServer;
	public static void main(String[] args) {
		SpringApplication.run(StocktickerproxyApplication.class, args);
		appSocketServer = new AppSocketServer();
	}

	@KafkaListener(topics = "stock-ticker-topic")
    public void listen(String message) {
        //System.out.println("Received message: " + message);
        appSocketServer.EmitMessage(message);
    }
}
