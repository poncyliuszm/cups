package pl.poncyliusz.backend.repository.cup;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import pl.poncyliusz.backend.dto.cup.CupCriteria;
import pl.poncyliusz.backend.model.Cup;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class CupSpecification implements Specification<Cup> {

    private final CupCriteria cupCriteria;

    @Override
    public Predicate toPredicate(Root<Cup> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(cupCriteria.getName()))
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), '%' + cupCriteria.getName().toLowerCase() + '%'));

        if (Objects.nonNull(cupCriteria.getDescription()))
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), '%' + cupCriteria.getDescription().toLowerCase() + '%'));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
