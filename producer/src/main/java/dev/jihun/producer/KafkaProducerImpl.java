package dev.jihun.producer;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Component
public class KafkaProducerImpl implements KafkaProducer {

    private final StreamBridge streamBridge;

    public KafkaProducerImpl(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    public boolean send(final String topic, final String message) {
        Message<?> m = MessageBuilder.withPayload(message)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();

        return streamBridge.send(topic, m);
    }

    @Override
    public boolean send(final String topic, final List<String> messages) {
        Message<?> m = MessageBuilder.withPayload(messages)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();

        return streamBridge.send(topic, m);
    }

    @Override
    public boolean send(final String topic, final Map<String, Object> messages) {
        boolean allMessagesSent = true;

        for (Map.Entry<String, Object> entry : messages.entrySet()) {
            byte[] keyBytes = entry.getKey().getBytes(StandardCharsets.UTF_8);

            Message<?> message = MessageBuilder.withPayload(entry.getValue())
                    .setHeader(KafkaHeaders.KEY, keyBytes)
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                    .build();

            boolean sent = streamBridge.send(topic, message);

            if (!sent) {
                allMessagesSent = false;
            }
        }
        return allMessagesSent;
    }

}
