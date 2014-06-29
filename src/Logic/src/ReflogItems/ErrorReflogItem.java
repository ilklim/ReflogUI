package ReflogItems;

import javax.swing.*;
import javax.swing.text.Style;
import java.awt.*;

public class ErrorReflogItem extends GeneralReflogItem {
    public ErrorReflogItem(String message) {
        super(0, "", message, ReflogAction.ERROR);
    }

    public String toString() {
        return "ERROR " + getMessage();
    }

    public void coloredPrint(JTextPane pane, Style style) {
        append(Color.red, getAction() + " ", pane, style);
        append(Color.black, getMessage()+"\n", pane, style);
    }
}
