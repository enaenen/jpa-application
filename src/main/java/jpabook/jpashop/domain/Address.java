package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
    protected Address() {
        //JPA 스펙상 필요- JPA 구현 라이브러리가 객체 생성시 리플렉션 같은 기술들을 사용할 수 있도록 지원
    }
}
