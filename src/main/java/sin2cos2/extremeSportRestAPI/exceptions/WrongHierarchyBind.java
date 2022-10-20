package sin2cos2.extremeSportRestAPI.exceptions;

public class WrongHierarchyBind extends RuntimeException{

    public WrongHierarchyBind() {
    }

    public WrongHierarchyBind(String message) {
        super(message);
    }

    public WrongHierarchyBind(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongHierarchyBind(Throwable cause) {
        super(cause);
    }
}
