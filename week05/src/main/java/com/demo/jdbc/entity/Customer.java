package com.demo.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Customer {
    private int id;

    private String firstName;
    private String lastName;

}
