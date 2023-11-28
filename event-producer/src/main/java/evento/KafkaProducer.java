package evento;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, User> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(User user) {
        kafkaTemplate.send(KafkaConstants.USER_TOPIC, user);
//        var message = MessageBuilder
//                .withPayload(user)
//                .setHeader(KafkaHeaders.TOPIC, KafkaConstants.USER_TOPIC)
//                .build();
//        kafkaTemplate.send(message);
    }

}
