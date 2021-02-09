package pl.poncyliusz.backend.repository.cup;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import pl.poncyliusz.backend.dto.cup.CupCriteria;
import pl.poncyliusz.backend.model.Cup;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CupSpecification implements Specification<Cup> {

    private final CupCriteria cupCriteria;

    @Override
    public Predicate toPredicate(Root<Cup> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (!CollectionUtils.isEmpty(cupCriteria.getNames())) {
            List<Predicate> subPredicates = new ArrayList<>();
            cupCriteria.getNames().stream().map(String::toLowerCase).forEach(x ->
                    subPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), '%' + x + '%')));
            predicates.add(criteriaBuilder.or(subPredicates.toArray(new Predicate[0])));
        }

        if (!CollectionUtils.isEmpty(cupCriteria.getDescriptions())) {
            List<Predicate> subPredicates = new ArrayList<>();
            cupCriteria.getDescriptions().stream().map(String::toLowerCase).forEach(x ->
                    subPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), '%' + x + '%')));
            predicates.add(criteriaBuilder.or(subPredicates.toArray(new Predicate[0])));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
