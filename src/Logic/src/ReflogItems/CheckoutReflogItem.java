package ReflogItems;

import javax.swing.*;
import javax.swing.text.Style;
import java.awt.*;

public class CheckoutReflogItem extends GeneralReflogItem {
    private final int from;
    private final int to;
    public CheckoutReflogItem(int number, String key, String from, String to) {
        super(number, key, "", ReflogAction.CHECKOUT);
        this.from = BranchManager.getIndex(from);
        this.to = BranchManager.getIndex(to);
    }

    public String getFrom() {
        return BranchManager.getBranchName(from);
    }

    public String getTo() {
        return BranchManager.getBranchName(to);
    }

    public String toString() {
        return getHash() + " HEAD@{" + getNumber() + "}: " + getAction() + ": moving from " + getFrom() + " to " + getTo();
    }

    public void coloredPrint(JTextPane pane, Style style) {
        append(Color.MAGENTA, getHash(), pane, style);
        append(Color.black, " HEAD@{", pane, style);
        append(Color.MAGENTA, String.valueOf(getNumber()), pane, style);
        append(Color.black, "}: ", pane, style);
        append(Color.GREEN, getAction(), pane, style);
        append(Color.black, ": ", pane, style);
        append(Color.red, "moving from ", pane, style);
        append(Color.orange, getFrom(), pane, style);
        append(Color.red, " to ", pane, style);
        append(Color.orange, getTo()+ "\n", pane, style);
    }
}
