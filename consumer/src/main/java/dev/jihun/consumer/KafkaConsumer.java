package dev.jihun.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    // application.yml에 정의한 Consumer의 바인딩 이름과 일치해야한다.
    @Bean
    public Consumer<String> sample() {
        return messages -> {
            try {
                log.info("Message Receive. message : {}", messages);
            } catch (Exception e) {
                log.error("Sample Topic Message Receive Fail");
                throw new SampleTopicMessageReceiveFailException("Message Receive Fail", e);
            }
        };
    }
}
