package ee.Karu.webshop.dao;

import ee.Karu.webshop.model.database.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
