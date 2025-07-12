package com.tiendita.tienditapromoskafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableKafka
@EnableTransactionManagement
public class TienditapromoskafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TienditapromoskafkaApplication.class, args);
	}

}
