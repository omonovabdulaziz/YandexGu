package ala.ddin.yagu.entity.tmp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class AbsUUIDEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    @CreationTimestamp
    @Column(updatable = false)
    @JsonIgnore
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updatedAt;
}