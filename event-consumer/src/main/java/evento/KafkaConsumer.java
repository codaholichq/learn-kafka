package evento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
    private User receivedUser;

    public User getUser() {
        return receivedUser;
    }

    @KafkaListener(
            topics = KafkaConstants.USER_TOPIC,
            groupId = KafkaConstants.GROUP_ID,
            containerFactory = "userListener")
    public void fetch(User user) {
        log.info("Received message => {}", user.toString());
        this.receivedUser = user;
    }
}
