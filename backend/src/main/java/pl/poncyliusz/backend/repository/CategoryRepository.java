package pl.poncyliusz.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.poncyliusz.backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
