package jpabook.jpashop.domain;


import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable               //JPA의 내장타입
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {

    }
}
