package LogItems;

public class InitialCommit extends AbstractLogItem {
    public InitialCommit(int number, String key, String message) {
        super(number, key, message);
    }

    @Override
    public String getAction() {
        return "Initial commit";
    }
}
