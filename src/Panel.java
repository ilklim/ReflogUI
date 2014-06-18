import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Iosif on 14.06.2014.
 */
//todo delete

//todo closing streams
//todo composition
//todo reflog keys

public class Panel extends JPanel {

    private final JButton printLog;
    private final JTextField adressField;
    private final JTextArea result;

    public Panel() {
        printLog = new JButton("Print log");
        adressField = new JTextField(40);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        result = new JTextArea(12, 45);
        JScrollPane sp = new JScrollPane(result);
        add(adressField);
        add(printLog);
        add(sp);
        Printer printer = new Printer();
        printLog.addActionListener(printer);
    }

    private class Printer implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                ProcessBuilder builder = new ProcessBuilder("git", "reflog");
                builder.directory(new File(adressField.getText()));
                builder.redirectErrorStream(true);

                Process process = builder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                result.setText("");
                StringBuilder res = new StringBuilder("");
                String nextLine;
                while ((nextLine = reader.readLine()) != null) {
                    res.append(nextLine + "\n");
                }
                result.setText(res.toString());
                reader.close();
            } catch (IOException e) {
                result.setText("Something has gone wrong. Probably, incorrect directory");
            }
        }
    }
}
