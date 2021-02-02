package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")    // 외래키 이름이 member_id라고 생각하면됨.
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne            //1:1매칭의 경우 FK를 어디에 둬도 상관없다. 그렇기때문에 어느클래스에 둘건지 기준을 세울것!
    @JoinColumn(name = "delevery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;   //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  //주문상태 [ORDER, CANCEL]

}
