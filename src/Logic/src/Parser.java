import ReflogItems.*;

import javax.swing.*;
import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static GeneralReflogItem parseLine(String line) {
        if (line.substring(0, 5).equalsIgnoreCase("fatal")) {
            System.out.println(line);
            return new ErrorReflogItem(line);
        } else {
            Pattern p = Pattern.compile("([0-9_a-f]*) HEAD@\\{(\\d*)\\}: (.*?):(?!//) (.*)");
            Matcher m = p.matcher(line);
            m.matches();

            String hash = m.group(1);
            int number = Integer.parseInt(m.group(2));
            String complexAction = m.group(3);  //action with additional information
            String message = m.group(4);

            String[] splitedAction = complexAction.split(" ");
            switch (splitedAction[0]) { //main word of action
                case "checkout": {
                    String to = parseCheckoutTo(message);
                    String from = parseCheckoutFrom(message);
                    BranchManager.setCurrentBranch(to);
                    return new CheckoutReflogItem(number, hash, from, to);
                }
                case "merge": {
                    String sideBranch = splitedAction[1];
                    return new MergeReflogItem(number, hash, message, sideBranch);
                }
                case "commit": {
                    if (splitedAction.length == 1) {
                        return new GeneralReflogItem(number, hash, message, ReflogAction.COMMIT);
                    } else {
                        switch (splitedAction[1]) { //additional information about commit (in brackets)
                            case "(initial)": return new GeneralReflogItem(number, hash, message, ReflogAction.INITIAL_COMMIT);
                            case "(amend)": return new GeneralReflogItem(number, hash, message, ReflogAction.AMENDING_COMMIT);
                        }
                    }
                }
                case "rebase": {
                    if (splitedAction.length == 1) {
                        return new GeneralReflogItem(number, hash, message, ReflogAction.REBASE);
                    } else {
                        switch (splitedAction[1]) { //additional information about rebase
                            case "-i": { //interactive rebase
                                switch (splitedAction[2]) { // additional information about interactive rebase (in brackets)
                                    case "(pick)": return new GeneralReflogItem(number, hash, message, ReflogAction.INTERACTIVE_REBASE_PICK);
                                    case "(edit)": return new GeneralReflogItem(number, hash, message, ReflogAction.INTERACTIVE_REBASE_EDIT);
                                    case "(finish)": return new GeneralReflogItem(number, hash, message, ReflogAction.INTERACTIVE_REBASE_FINISH);
                                }
                            }
                            case "finished": return new GeneralReflogItem(number, hash, message, ReflogAction.REBASE_FINISHED);
                        }
                    }
                }
                case "reset":  return new GeneralReflogItem(number, hash, message, ReflogAction.RESET);
                case "cherry-pick":  return new GeneralReflogItem(number, hash, message, ReflogAction.CHERRY_PICK);
                case "pull": {
                    if (splitedAction[1].equalsIgnoreCase("--rebase")) return new GeneralReflogItem(number, hash, message, ReflogAction.PULL_REBASE);
                }
                default: {System.out.println(line); return new ErrorReflogItem("Unknown action in line: " + line);}
            }
        }
    }

    public static ArrayList<GeneralReflogItem> parse(String log) {
        String[] lines = log.split("\n");   //todo ask A.A.
        ArrayList<GeneralReflogItem> res = new ArrayList<>();
        for (int i = lines.length-1; i >= 0; i--) {
            try {
                res.add(parseLine(lines[i]));
            } catch (Exception e) {
                res.add(new ErrorReflogItem("Impossible to parse line: " + lines[i]));
            }
        }
        return res;
    }

    public static void coloredPrint(String log, JTextPane pane, Style style) {
        ArrayList<GeneralReflogItem> parsed = parse(log);
        pane.setText("");
        for (int i = parsed.size()-1; i >= 0; i--) {
            parsed.get(i).coloredPrint(pane, style);
        }
    }

    private static String parseCheckoutFrom(String message) {
        String[] parsed = message.split(" ");
        return parsed[2];
    }

    private static String parseCheckoutTo(String message) {
        String[] parsed = message.split(" ");
        return parsed[4];
    }
}
