package LogItems;

import java.util.ArrayList;

public class BranchMaster {
    private static final ArrayList<String> Branches= new ArrayList<String>();

    public static int getIndex(String branch) {
        int i = Branches.indexOf(branch);
        if (i == -1) {
            Branches.add(branch);
            return Branches.size()-1;
        } else {
            return i;
        }
    }

    public static String getBranch(int i){
        return Branches.get(i);
    }
}
