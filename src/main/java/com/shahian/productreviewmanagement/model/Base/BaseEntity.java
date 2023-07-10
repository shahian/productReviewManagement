package com.shahian.productreviewmanagement.model.Base;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class BaseEntity {

    @Column(name = "createDateTime")
    protected Date createDateTime;

    @Column(name = "modifyDateTime")
    protected Date modifyDateTime;




    @Column(name = "is_deleted")
    protected Boolean isDeleted;

    @PrePersist
    protected void onCreate() {

        createDateTime = new Timestamp(System.currentTimeMillis());
        isDeleted = false;
        modifyDateTime = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        modifyDateTime = new Timestamp(System.currentTimeMillis());
    }
}
