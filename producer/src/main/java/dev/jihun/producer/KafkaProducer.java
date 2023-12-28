package dev.jihun.producer;

import java.util.List;
import java.util.Map;

public interface KafkaProducer {

    /**
     * Topic에 Key가 없는 Message를 발송한다.
     * @param topic Topic Name
     * @param message Key가 없는 Message
     * @return Kafka로 발송 성공 여부
     * */
    boolean send(String topic, String message);

    /**
     * Topic에 Key가 없는 Message 목록을 발송한다.
     * @param topic Topic Name
     * @param messages Key가 없는 Message 목록
     * @return Kafka로 발송 성공 여부 이다. Message 목록 중 한 건이라도 실패하면 false를 Return 한다.
     * */
    boolean send(String topic, List<String> messages);

    /**
     * Topic에 Key가 있는 Message 목록을 발송한다.
     * @param topic Topic Name
     * @param messages Key가 있는 Message 목록
     * @return Kafka로 발송 성공 여부 이다. Message 목록 중 한 건이라도 실패하면 false를 Return 한다.
     * */
    boolean send(String topic, Map<String, Object> messages);

}
