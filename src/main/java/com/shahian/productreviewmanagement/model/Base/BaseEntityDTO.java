package com.shahian.productreviewmanagement.model.Base;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.Date;


@MappedSuperclass
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseEntityDTO {
    private Boolean isDeleted=false;
    private Date createDateTime = new Timestamp(System.currentTimeMillis());
    private Date modifyDateTime= new Timestamp(System.currentTimeMillis());
    private Long creatorUserId;
}
