import java.util.ArrayList;

public class Parser {

    public static LogItem parseLine(String line) {
        try {
            String[] preParse = line.split(":");
            if (preParse[0].equalsIgnoreCase("fatal")) {
                return new LogItem("", -1, "fatal", preParse[1]);
            } else {
                String[] keyAndNumber = preParse[0].split(" ");
                String key = keyAndNumber[0];
                String numberInBrackets = keyAndNumber[1].split("@")[1];
                int number = Integer.parseInt(numberInBrackets.substring(1, numberInBrackets.length() - 1));
                String action = preParse[1].substring(1);
                String message = preParse[2].substring(1);

                return new LogItem(key, number, action, message);
            }
        } catch (Exception e) {
            return new LogItem("", -1, "error", "Impossible to parse this line: " + line);
        }
    }

    public static ArrayList<LogItem> parse(String log) {
        String[] lines = log.split("\n");
        ArrayList<LogItem> res = new ArrayList<LogItem>();
        for (int i = 0; i < lines.length; i++) {
            res.add(parseLine(lines[i]));
        }
        return res;
    }

    public static String printParsedLog(String log) {
        StringBuilder res = new StringBuilder("");
        ArrayList<LogItem> parsed = parse(log);
        for (int i = 0; i < parsed.size(); i++) {
            res.append(parsed.get(i).toString() + "\n");
        }
        return res.toString();
    }
}
