package pl.poncyliusz.backend.repository.cup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.poncyliusz.backend.dto.cup.CupListDTO;
import pl.poncyliusz.backend.model.Cup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CupSearchRepositoryImpl implements CupSearchRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<CupListDTO> findCupList(Specification<Cup> specification, Pageable pageable) {
        Long total = getTotal(specification);
        List<CupListDTO> content = new ArrayList<>();
        if (!Objects.equals(total, 0L)) {
            content = getContent(specification, pageable);
        }
        return new PageImpl<>(content, pageable, total);
    }

    private Long getTotal(Specification<Cup> specification) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Cup> root = query.from(Cup.class);
        query.select(cb.count(root));
        query.where(specification.toPredicate(root, query, cb));
        return entityManager.createQuery(query).getSingleResult();
    }

    private List<CupListDTO> getContent(Specification<Cup> specification, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CupListDTO> query = cb.createQuery(CupListDTO.class);
        Root<Cup> root = query.from(Cup.class);

        query.multiselect(
                root.get("id"),
                root.get("name"),
                root.get("category").get("name"),
                root.get("description")
        );
        query.where(specification.toPredicate(root, query, cb));
        TypedQuery<CupListDTO> typedQuery = entityManager.createQuery(query);

        typedQuery.setFirstResult((pageable.getPageNumber()) * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        return typedQuery.getResultList();
    }
}
