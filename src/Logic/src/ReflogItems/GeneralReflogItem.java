package ReflogItems;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class GeneralReflogItem {
    private final int number;
    private final String hash;
    private final String message;
    private final ReflogAction action;

    public GeneralReflogItem(int number, String hash, String message, ReflogAction action) {
        this.number = number;
        this.hash = hash;
        this.message = message;
        this.action = action;
    }

    public int getNumber() {
        return number;
    }

    public String getHash() {
        return hash;
    }

    public String getMessage() {
        return message;
    }

    public String getAction() {
        return action.toString();
    }

    public String toString() {
        return hash + " HEAD@{" + number + "}: " + action + ": " + message;
    }

    public void coloredPrint(JTextPane pane, Style style) {
        append(Color.MAGENTA, getHash(), pane, style);
        append(Color.black, " HEAD@{", pane, style);
        append(Color.MAGENTA, String.valueOf(getNumber()), pane, style);
        append(Color.black, "}: ", pane, style);
        append(Color.GREEN, getAction(), pane, style);
        append(Color.black, ": ", pane, style);
        append(Color.RED, getMessage()+"\n", pane, style);

    }

    protected void append(Color c, String s, JTextPane pane, Style style) { //todo ask A.A., if protected is ok
        StyledDocument doc = pane.getStyledDocument();
        StyleConstants.setForeground(style, c);
        try { doc.insertString(doc.getLength(), s, style); }
        catch (BadLocationException ignored){
            //todo ask A.A.
        }
    }
}
