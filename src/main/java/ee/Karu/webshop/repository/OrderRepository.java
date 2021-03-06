package ee.Karu.webshop.repository;

import ee.Karu.webshop.model.database.Order;
import ee.Karu.webshop.model.database.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getOrdersByPersonOrderByCreationDateDesc(Person person);
}
