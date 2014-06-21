public abstract class AbstractLogItem {
    private final int number;
    private final String key;
    private final String message;

    public AbstractLogItem(int number, String key, String message) {
        this.number = number;
        this.key = key;
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    abstract String getAction();
}
