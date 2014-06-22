package LogItems;

public class Merge extends AbstractLogItem{
    private final int sideBranch;
    private final int mainBranch;
    public Merge(int number, String key, String message, String mainBranch, String sideBranch) {
        super(number, key, message);
        this.sideBranch = BranchMaster.getIndex(sideBranch);
        this.mainBranch = BranchMaster.getIndex(mainBranch);
    }

    public Merge(int number, String key, String message, String sideBranch) {
        super(number, key, message);
        this.sideBranch = BranchMaster.getIndex(sideBranch);
        this.mainBranch = BranchMaster.getCurrentBranch();
    }

    public String getSideBranch() {
        return BranchMaster.getBranch(sideBranch);
    }

    public String getMainBranch() {
        return BranchMaster.getBranch(mainBranch);
    }

    @Override
    public String getAction() {
        return "Merge";
    }

    public String toString() {
        return "" + getNumber() + "   " + getKey() + "   " + getAction() + "   " + getMainBranch() + "   with   " +getSideBranch() + "   by   "+ getMessage();    }
}
