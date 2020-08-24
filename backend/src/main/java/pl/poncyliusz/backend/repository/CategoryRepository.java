package pl.poncyliusz.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.poncyliusz.backend.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByIsActive(boolean b);
}
