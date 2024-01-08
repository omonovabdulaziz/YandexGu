package ala.ddin.yagu.entity;

import ala.ddin.yagu.entity.tmp.AbsLongEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper = true)
public class Status extends AbsLongEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long inCityPrice;
    @Column(nullable = false)
    private Long exCityPrice;
    @Column(nullable = false)
    private Integer forOwner;
}
