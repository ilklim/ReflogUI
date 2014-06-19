import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogReader {
    static String read(String adress) throws Exception {
        ProcessBuilder builder = new ProcessBuilder("git", "reflog");
        builder.directory(new File(adress));
        builder.redirectErrorStream(true);

        Process process = null;
        process = builder.start();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            StringBuilder res = new StringBuilder("");
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                res.append(nextLine + "\n");
            }
            return res.toString();
        } catch (Exception e) {
            throw e;
        }
    }
}
