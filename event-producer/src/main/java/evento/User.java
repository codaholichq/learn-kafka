package evento;

import java.io.Serializable;

public record User(
        String firstName,
        String lastName,
        String email
) implements Serializable {}