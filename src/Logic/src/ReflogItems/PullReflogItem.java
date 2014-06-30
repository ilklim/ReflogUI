package ReflogItems;

import javax.swing.*;
import javax.swing.text.Style;
import java.awt.*;

public class PullReflogItem extends GeneralReflogItem {
    private final String serverAdress;
    private final int branch;
    public PullReflogItem(int number, String hash, String message, String serverAddress, String branchName) {
        super(number, hash, message, ReflogAction.PULL);
        this.branch = BranchManager.getIndex(branchName);
        this.serverAdress = serverAddress;
    }

    public String getServerAdress() {
        return serverAdress;
    }

    public String getBranch() {
        return BranchManager.getBranchName(branch);
    }

    public String toString() {
        return getHash() + " HEAD@{" + getNumber() + "}: " + getAction() + " " + getServerAdress() + " " + getBranch()+ ": " + getMessage();
    }

    public void coloredPrint(JTextPane pane, Style style) {
        append(Color.MAGENTA, getHash(), pane, style);
        append(Color.black, " HEAD@{", pane, style);
        append(Color.MAGENTA, String.valueOf(getNumber()), pane, style);
        append(Color.black, "}: ", pane, style);
        append(Color.GREEN, getAction() + " ", pane, style);
        append(Color.orange, getServerAdress()+" ", pane, style);
        append(Color.orange, getBranch()+" ", pane, style);
        append(Color.black, ": ", pane, style);
        append(Color.RED, getMessage()+"\n", pane, style);
    }
}
