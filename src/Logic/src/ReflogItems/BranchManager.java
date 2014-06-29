package ReflogItems;

import java.util.ArrayList;

public class BranchManager {
    private static final ArrayList<String> BRANCHES = new ArrayList<>();
    private static int currentBranch = 0;

    static {
        BRANCHES.add("master");
    }

    public static int getIndex(String branch) {
        int i = BRANCHES.indexOf(branch);
        if (i == -1) {
            BRANCHES.add(branch);
            return BRANCHES.size()-1;
        } else {
            return i;
        }
    }

    public static String getBranchName(int i){
        return BRANCHES.get(i);
    }

    public static int getCurrentBranchName() {
        return currentBranch;
    }

    public static void setCurrentBranch(String branchName) {
        currentBranch = getIndex(branchName);
    }
}
