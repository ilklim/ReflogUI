package LogItems;

public class FatalItem extends AbstractLogItem {

    public FatalItem(String message) {
        super(-1, "", message);
    }

    @Override
    public String getAction() {
        return "Fatal";
    }

    public String toString() {
        return getAction() + ":   " + getMessage();
    }
}
