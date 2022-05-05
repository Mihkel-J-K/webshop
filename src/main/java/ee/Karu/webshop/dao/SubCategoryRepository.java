package ee.Karu.webshop.dao;

import ee.Karu.webshop.model.database.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<Subcategory, Long> {
}
