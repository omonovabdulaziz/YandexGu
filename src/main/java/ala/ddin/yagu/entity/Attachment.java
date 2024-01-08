package ala.ddin.yagu.entity;

import ala.ddin.yagu.entity.enums.FileName;
import ala.ddin.yagu.entity.tmp.AbsUUIDEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper = true)
public class Attachment extends AbsUUIDEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FileName fileName;
    private String contentType;
    @ManyToOne
    private User user;
    @Column(nullable = false)
    private String path;
}
