package ala.ddin.yagu.entity;

import ala.ddin.yagu.entity.tmp.AbsUUIDEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "cards")
@EqualsAndHashCode(callSuper = true)
public class Card extends AbsUUIDEntity {
    @Column(nullable = false)
    private Long cardNumber;
    @Column(nullable = false)
    private Integer validDate;
    private Integer cvv;
    @ManyToOne
    private User user;
}
