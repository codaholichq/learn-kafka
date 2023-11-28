package evento;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final KafkaProducer producer;

    public UserController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> add(@RequestBody User user) {
        producer.send(user);
        return ResponseEntity.ok(user);
    }
}
