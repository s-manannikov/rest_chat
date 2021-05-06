package chat.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private int id;

    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @ApiModelProperty(hidden = true)
    private Role role;
}
