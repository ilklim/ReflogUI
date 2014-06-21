package LogItems;

public class Commit extends AbstractLogItem {
    public Commit(int number, String key, String message) {
        super(number, key, message);
    }

    @Override
    public String getAction() {
        return "Commit";
    }
}
