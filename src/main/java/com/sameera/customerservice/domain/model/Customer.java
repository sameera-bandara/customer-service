package com.sameera.customerservice.domain.model;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Customer {
    private UUID id;
    private String firstName;
    private String lastName;
    private Address address;
}
