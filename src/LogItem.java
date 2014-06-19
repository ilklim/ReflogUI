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

    public String toString() {
        if (number != -1) {
            return "№" + this.number + "   key: " + this.key +
                    "   action: " + this.action.toUpperCase() + "   message: " + this.message.toUpperCase();
        } else {
            return this.message;
        }
    }
}
