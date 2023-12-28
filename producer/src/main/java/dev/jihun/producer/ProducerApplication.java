package dev.jihun.producer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ProducerApplication implements ApplicationRunner {

    private final KafkaProducer kafkaProducer;

    public ProducerApplication(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        kafkaProducer.send("sample", "hello");
        kafkaProducer.send("sample", List.of("1", "2"));
        kafkaProducer.send("sample", Map.of("3", "4"));
    }
}
