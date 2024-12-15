package com.interview.damian_ozga.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Getter
@Setter
public abstract class MongoDbDocument {
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;
}