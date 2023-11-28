package evento;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    private final KafkaProperty property;

    public KafkaConfig(KafkaProperty property) {
        this.property = property;
    }

    public Map<String, Object> config(){
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, property.getBootstrapServers(),
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                JsonDeserializer.TRUSTED_PACKAGES, "evento"
        );
    }

    @Bean
    public ConsumerFactory<String, User> userFactory(){
        return new DefaultKafkaConsumerFactory<>(config());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, User> userListener() {
        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userFactory());
        return factory;
    }
}
