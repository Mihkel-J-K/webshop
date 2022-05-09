package ee.Karu.webshop.model.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@SequenceGenerator(name = "seq", initialValue = 3214123, allocationSize = 1)
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="orderSeq")
    private Long id;
    private double orderSum;
    private PaymentState paymentState;

    @ManyToMany
    private List<Product> products;

    //              @OneToMany
    // Location    List<TimeSlot>   ÜKS TimeSlot on ainult ÜHES ASUKOHTAS

    //              @ManyToOne
    // TimeSlot     Location
}
