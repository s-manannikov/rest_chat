package chat.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private int id;

    private String name;

    @OneToMany
    @ApiModelProperty(hidden = true)
    private final List<Person> person = new ArrayList<>();

    public void addPerson(Person person) {
        this.person.add(person);
    }
}