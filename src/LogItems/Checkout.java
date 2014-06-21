package LogItems;

public class Checkout extends AbstractLogItem {
    private final int from;
    private final int to;
    public Checkout(int number, String key, String from, String to) {
        super(number, key, "");
        this.from = BranchMaster.getIndex(from);
        this.to = BranchMaster.getIndex(to);
    }

    public String getFrom() {
        return BranchMaster.getBranch(from);
    }

    public String getTo() {
        return BranchMaster.getBranch(to);
    }

    @Override
    public String getAction() {
        return "Checkout";
    }

    public String toString() {
        return "" + getNumber() + "   " + getKey() + "   " + getAction() + "   " + "from" + "   " + getFrom() + "   to" + getTo();
    }
}
