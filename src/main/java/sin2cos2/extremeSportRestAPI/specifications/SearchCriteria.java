package sin2cos2.extremeSportRestAPI.specifications;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
    // Entity field name
    private String key;
    // Parameter value
    private Object value;
    // Operation
    private SearchOperation operation;

    public SearchCriteria() {
    }

    public SearchCriteria(String key, Object value, SearchOperation operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }
}
