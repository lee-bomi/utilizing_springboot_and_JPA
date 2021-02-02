package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")            // 1:1매핑일때 FK를 잡고, 잡혀지는 클래스에서는 mappedBy를 기재해준다.
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)       //Enum타입일때 어노테이션달기, default값은 ORDINAL(숫자로 들어간다_순서가매겨진다), 그렇기 때문에 꼭 String으로 쓴다!
    private DeliveryStatus status;      //READY, COMP
}
