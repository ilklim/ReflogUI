package LogItems;

public class AmendingCommit extends AbstractLogItem {
    public AmendingCommit(int number, String key, String message) {
        super(number, key, message);
    }

    @Override
    public String getAction() {
        return "Amending commit";
    }
}
