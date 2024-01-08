package ala.ddin.yagu.entity;

import ala.ddin.yagu.entity.tmp.AbsLongEntity;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Car extends AbsLongEntity {
    private String mark;
    private String model;
    private String color;
    @DateTimeFormat(pattern = "")
    private Date boughtDate;
    private String number;
}
