public class LogItem {

    private final String key;
    private final int number;
    private final String action;
    private final String message;

    public LogItem(String key, int number, String action, String message) {
        this.key = key;
        this.number = number;
        this.action = action;
        this.message = message;
    }

    public String getKey() {
        return this.key;
    }

    public int getNumber() {
        return this.number;
    }

    public String getAction() {
        return this.action;
    }

    public String getMessage() {
        return this.message;
    }
}
