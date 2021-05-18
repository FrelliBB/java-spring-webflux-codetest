package com.example.demo.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)
public class Customer {

    Long id;
    String name;
    ZonedDateTime dueTime;
    ZonedDateTime joinTime;

}
