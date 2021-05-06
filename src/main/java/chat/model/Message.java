package chat.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private int id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @ApiModelProperty(hidden = true)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @ApiModelProperty(hidden = true)
    private Room room;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(hidden = true)
    private Date created = new Date(System.currentTimeMillis());
}
