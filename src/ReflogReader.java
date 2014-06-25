import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReflogReader {
    static String getReflog(File gitRepository) throws IOException {
        ProcessBuilder builder = new ProcessBuilder("git", "reflog");
        builder.directory(gitRepository);
        builder.redirectErrorStream(true);

        Process process = builder.start();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            StringBuilder res = new StringBuilder("");
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                res.append(nextLine).append("\n");
            }
            return res.toString();
        }
    }
}
