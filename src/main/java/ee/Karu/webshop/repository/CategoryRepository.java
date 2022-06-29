package ee.Karu.webshop.repository;

import ee.Karu.webshop.model.database.Category;
import ee.Karu.webshop.model.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
