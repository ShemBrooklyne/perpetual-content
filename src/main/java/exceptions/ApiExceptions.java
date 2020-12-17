package exceptions;

public class ApiExceptions extends RuntimeException {

    private final int statusCode;

    public ApiExceptions(int statusCode, String msg) {
        super(msg);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
