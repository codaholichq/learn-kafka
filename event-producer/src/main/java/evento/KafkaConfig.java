package evento;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    private final KafkaProperty property;

    public KafkaConfig(KafkaProperty property) {
        this.property = property;
    }

    @Bean
    public NewTopic userTopic() {
        return TopicBuilder.name(KafkaConstants.USER_TOPIC).build();
    }

    public Map<String, Object> config(){
        return Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, property.getBootstrapServers(),
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
        );
    }

    @Bean
    public ProducerFactory<String, User> producerFactory(){
        return new DefaultKafkaProducerFactory<>(config());
    }

    @Bean
    public KafkaTemplate<String, User> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
