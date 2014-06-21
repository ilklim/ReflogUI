package LogItems;

public class Merge extends AbstractLogItem{
    private final int sideBranch;
    public Merge(int number, String key, String message, String sideBranch) {
        super(number, key, message);
        this.sideBranch = BranchMaster.getIndex(sideBranch);
    }

    public String getSideBranch() {
        return BranchMaster.getBranch(sideBranch);
    }

    @Override
    public String getAction() {
        return "Merge";
    }

    public String toString() {
        return "" + getNumber() + "   " + getKey() + "   " + getAction() + "   " + getSideBranch() + "   " + getMessage();
    }
}
