package ee.Karu.webshop.dao;

import ee.Karu.webshop.model.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getAllByOrderByIdAsc();

    List<Product> getAllByStockGreaterThanOrderByIdAsc(int stock);

    List<Product> getAllByStockGreaterThanAndActiveEqualsOrderByIdAsc(int stock, boolean active);
}
