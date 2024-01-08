package ala.ddin.yagu.entity;

import ala.ddin.yagu.entity.tmp.AbsUUIDEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "orders")
@EqualsAndHashCode(callSuper = true)
public class Order extends AbsUUIDEntity {
    @JoinColumn(nullable = false)
    @ManyToOne
    private User customer;
    @JoinColumn(nullable = false)
    @ManyToOne
    private User driver;
    private Integer distance;
    private Long price;
    private Boolean isFinished;
    private String fromLocation;
    private String toLocation;
}
