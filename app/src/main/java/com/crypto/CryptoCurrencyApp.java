package com.crypto;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class CryptoCurrencyApp {

    public static void main(String[] args) {
        SpringApplication.run(CryptoCurrencyApp.class, args);

        final var topicName = "kafka-topic";
        final var bootstrapServers = "localhost:9092";

        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", bootstrapServers);
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);

        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, "key", "wartość");

        producer.send(record);

        producer.flush();
        producer.close();
    }
}