package sin2cos2.extremeSportRestAPI.specifications;

import org.springframework.data.jpa.domain.Specification;
import sin2cos2.extremeSportRestAPI.entities.Trip;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class TripSpecification implements Specification<Trip> {

    private final List<SearchCriteria> list;

    public TripSpecification() {
        list = new ArrayList<>();
    }

    private static Expression<String> getStringExpression(Root<Trip> root, SearchCriteria criteria) {
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

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();
        Expression<String> key;

        for (SearchCriteria criteria : list) {

            key = getStringExpression(root, criteria);

            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                predicates.add(builder.greaterThan(
                        key, criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(builder.lessThan(
                        key, criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                predicates.add(builder.greaterThanOrEqualTo(
                        key, criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                predicates.add(builder.lessThanOrEqualTo(
                        key, criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(builder.notEqual(
                        key, criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(
                        key, criteria.getValue()));
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
