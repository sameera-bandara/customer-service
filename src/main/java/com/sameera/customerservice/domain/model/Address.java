package com.sameera.customerservice.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Address {
    private String street;
    private String state;
    private String city;
    private String zip;
}
