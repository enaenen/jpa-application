package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import javax.persistence.*;

@Entity
@Getter@Setter
public class Delivery {
    @Id@GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // READY, COMP
}
