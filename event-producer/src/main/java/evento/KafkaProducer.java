package evento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, Object> template;
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    public KafkaProducer(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    public void send(User user) {
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send(KafkaConstants.USER_TOPIC, user);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Sent message: {} with offset: {}", user.toString(), result.getRecordMetadata().offset());
                } else {
                    log.error("Unable to send message: {} due to: {}", user.toString(), ex.getMessage());
                }
            });

        } catch (Exception ex) {
            log.error("ERROR: {}", ex.getMessage());
        }
    }

}
