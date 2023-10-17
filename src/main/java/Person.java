import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    private String id;
    private String name;
    private int age;

    // Getters and Setters
}
