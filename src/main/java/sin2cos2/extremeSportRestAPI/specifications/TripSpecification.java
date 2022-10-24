package sin2cos2.extremeSportRestAPI.specifications;

import org.springframework.data.jpa.domain.Specification;
import sin2cos2.extremeSportRestAPI.entities.Trip;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TripSpecification implements Specification<Trip> {

    private final List<SearchCriteria> list;

    public TripSpecification() {
        list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        list.forEach(criteria -> predicates.add(createPredicate(criteria, root, builder)));

        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate createPredicate(SearchCriteria criteria, Root<Trip> root, CriteriaBuilder builder) {

        if (criteria.getValue() instanceof LocalDate)
            return datePredicate(criteria, root, builder);
        else
            return stringPredicate(criteria, root, builder);
    }

    private Predicate stringPredicate(SearchCriteria criteria, Root<Trip> root, CriteriaBuilder builder) {

        Expression<String> key = getStringExpression(root, criteria);
        String value = criteria.getValue().toString();

        if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
            return builder.equal(key, value);
        }
        if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
            return builder.notEqual(key, value);
        }
        if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
            return builder.lessThan(key, value);
        }
        if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
            return builder.lessThanOrEqualTo(key, value);
        }
        if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
            return builder.greaterThan(key, value);
        }
        if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
            return builder.greaterThanOrEqualTo(key, value);
        }

        throw new RuntimeException(criteria.getOperation().toString() + " search operation is not implemented!");
    }

    private Predicate datePredicate(SearchCriteria criteria, Root<Trip> root, CriteriaBuilder builder) {

        Path<LocalDate> key = root.get(criteria.getKey());
        LocalDate value = (LocalDate) criteria.getValue();

        if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
            return builder.equal(key, value);
        }
        if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
            return builder.notEqual(key, value);
        }
        if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
            return builder.lessThan(key, value);
        }
        if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
            return builder.lessThanOrEqualTo(key, value);
        }
        if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
            return builder.greaterThan(key, value);
        }
        if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
            return builder.greaterThanOrEqualTo(key, value);
        }

        throw new RuntimeException(criteria.getOperation().toString() + " search operation is not implemented!");

    }

    private Expression<String> getStringExpression(Root<Trip> root, SearchCriteria criteria) {

        String[] paths;
        Expression<String> key;
        Path<String> tempKey;

        if (criteria.getKey().contains(".")) {
            paths = criteria.getKey().split("\\.");

            tempKey = root.get(paths[0]);
            for (int i = 1; i < paths.length; i++)
                tempKey = tempKey.get(paths[i]);

            key = tempKey;
        } else {
            key = root.get(criteria.getKey());
        }
        return key;
    }
}
