package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)         //자식테이블의 부모로써, 부모클래스에 전략을 잡아줘야한다.
@DiscriminatorColumn(name = "dtype")                          //single_table로 자손과 매칭이 되므로, 서로 알아볼 수 있도록 db에 알려줘야한다.
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

}
