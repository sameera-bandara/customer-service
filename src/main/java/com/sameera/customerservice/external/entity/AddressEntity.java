package com.sameera.customerservice.external.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
public class AddressEntity {
    private String street;
    private String state;
    private String city;
    private String zip;
}
