package ala.ddin.yagu.entity;

import ala.ddin.yagu.entity.tmp.AbsUUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper = true)
public class Expenses extends AbsUUIDEntity {
    @JoinColumn(nullable = false)
    @ManyToOne
    private Card card;
    @Column(nullable = false)
    private Long value;
}
