package ee.Karu.webshop.dao;

import ee.Karu.webshop.model.database.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
