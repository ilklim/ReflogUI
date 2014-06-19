import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
        printLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    result.setText(LogReader.read(adressField.getText()));
                } catch (Exception e1) {
                    result.setText("Something has gone wrong. Probably, incorrect directory");
                }
            }
        });
    }

    private class Printer implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            ProcessBuilder builder = new ProcessBuilder("git", "reflog");
            builder.directory(new File(adressField.getText()));
            builder.redirectErrorStream(true);

            Process process = null;
            try {
                 process = builder.start();
            } catch (IOException e) {
                result.setText("Something has gone wrong. Probably, incorrect directory");
            }

            try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                result.setText("");
                StringBuilder res = new StringBuilder("");
                String nextLine;
                while ((nextLine = reader.readLine()) != null) {
                    res.append(nextLine + "\n");
                }
                result.setText(res.toString());
            } catch (Exception e) {
                result.setText("Something has gone wrong. Probably, incorrect directory");
            }
        }
    }
}