import LogItems.*;

import java.util.ArrayList;

public class Parser {

    public static AbstractLogItem parseLine(String line) {
        try {
            String[] preParse = line.split(":");
            if (preParse[0].equalsIgnoreCase("fatal")) {
                return new FatalItem(preParse[1]);
            } else {
                String[] keyAndNumber = preParse[0].split(" ");
                String key = keyAndNumber[0];
                String numberInBrackets = keyAndNumber[1].split("@")[1];
                int number = Integer.parseInt(numberInBrackets.substring(1, numberInBrackets.length() - 1));
                String[] preAction = preParse[1].substring(1).split(" ");
                String message = preParse[2].substring(1);

                String s = parseAction(preAction);
                s = s.toLowerCase();
                switch (s) {
                    case "commit": return new Commit(number, key, message);
                    case "checkout": return new Checkout(number, key, parseCheckoutFrom(message), parseCheckoutTo(message));
                    case "initialcommit": return new InitialCommit(number, key, message);
                    case "amendingcommit": return new AmendingCommit(number, key, message);
                    case "merge": return new Merge(number, key, message, preAction[1]);
                    default: return new FatalItem("Impossible to parse this line: " + line);
                }
            }
        } catch (Exception e) {
            return new FatalItem("Impossible to parse this line: " + line);
        }
    }

    public static ArrayList<AbstractLogItem> parse(String log) {
        String[] lines = log.split("\n");
        ArrayList<AbstractLogItem> res = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            res.add(parseLine(lines[i]));
        }
        return res;
    }

    public static String printParsedLog(String log) {
        StringBuilder res = new StringBuilder("");
        ArrayList<AbstractLogItem> parsed = parse(log);
        for (int i = 0; i < parsed.size(); i++) {
            res.append(parsed.get(i).toString() + "\n");
        }
        return res.toString();
    }

    private static String parseAction(String[] preAction) {
        if (preAction.length == 1) {
            return preAction[0];
        } else {
            String s = preAction[1];
            switch (s) {
                case "(initial)": return "InitialCommit";
                case "(amend)": return "AmendingCommit";
                case  "merge": return "merge";
                default: return "Unknown";
            }
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
