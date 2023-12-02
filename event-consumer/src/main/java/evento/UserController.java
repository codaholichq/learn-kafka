package evento;

import evento.model.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final KafkaConsumer consumer;

    public UserController(KafkaConsumer consumer) {
        this.consumer = consumer;
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getAll() {
        var user = consumer.getUser();
        return ResponseEntity.ok(user);
    }
}
