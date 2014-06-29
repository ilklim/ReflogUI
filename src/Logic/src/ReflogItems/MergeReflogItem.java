package ReflogItems;

import javax.swing.*;
import javax.swing.text.Style;
import java.awt.*;

public class MergeReflogItem extends GeneralReflogItem {
    private final int sideBranch;
    private final int mainBranch;
    public MergeReflogItem(int number, String key, String message, String mainBranch, String sideBranch) {
        super(number, key, message, ReflogAction.MERGE);
        this.sideBranch = BranchManager.getIndex(sideBranch);
        this.mainBranch = BranchManager.getIndex(mainBranch);
    }

    public MergeReflogItem(int number, String key, String message, String sideBranch) {
        super(number, key, message, ReflogAction.MERGE);
        this.sideBranch = BranchManager.getIndex(sideBranch);
        this.mainBranch = BranchManager.getCurrentBranchName();
    }

    public String getSideBranch() {
        return BranchManager.getBranchName(sideBranch);
    }

    public String getMainBranch() {
        return BranchManager.getBranchName(mainBranch);
    }

    public String toString() {
        return getHash() + " HEAD@{" + getNumber() + "}: " + getAction() + " " + getSideBranch() + ": " + getMessage();
    }

    public void coloredPrint(JTextPane pane, Style style) {
        append(Color.MAGENTA, getHash(), pane, style);
        append(Color.black, " HEAD@{", pane, style);
        append(Color.MAGENTA, String.valueOf(getNumber()), pane, style);
        append(Color.black, "}: ", pane, style);
        append(Color.GREEN, getAction() + " ", pane, style);
        append(Color.orange, getSideBranch(), pane, style);
        append(Color.black, ": ", pane, style);
        append(Color.RED, getMessage()+"\n", pane, style);
    }
}
