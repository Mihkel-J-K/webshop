package ee.Karu.webshop.dao;

import ee.Karu.webshop.model.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
