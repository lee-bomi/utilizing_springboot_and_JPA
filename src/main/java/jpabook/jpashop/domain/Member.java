package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")   //ordertable에있는 member필드에 의해 매핑됨 // mappedBy : 매핑당한 거울일 뿐이야!
    private List<Order> orders = new ArrayList<>();
}
