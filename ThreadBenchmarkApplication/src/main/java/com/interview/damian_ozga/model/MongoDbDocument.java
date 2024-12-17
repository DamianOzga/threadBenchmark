package com.interview.damian_ozga.model;

import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Abstract base class for MongoDB documents to handle common audit fields.
 */
@Getter
@Setter
public abstract class MongoDbDocument {

    // The date and time when the document was created
    @Timestamp
    private LocalDateTime creationDate;
}