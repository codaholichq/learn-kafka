package evento;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("spring.kafka.consumer")
public class KafkaProperty {
    private String bootstrapServers;
    private String groupId;
    private String clientId;
}
