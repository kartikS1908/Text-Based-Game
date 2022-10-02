package globals;

public class HandledException extends Exception{
    private String message;
    public HandledException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
