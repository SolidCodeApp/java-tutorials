package LSP;

public class LimitExceedException extends RuntimeException {
    public LimitExceedException(String messString) {
        super(messString);
    }

}
