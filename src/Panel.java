import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iosif on 14.06.2014.
 */
public class Panel extends JPanel{

    JButton printLog;
    JTextField adressField;
    JTextArea result;

    public Panel() {

        printLog = new JButton("Print log");
        adressField = new JTextField(40);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        result = new JTextArea();

        add(adressField);
        add(printLog);
        add(result);
        Printer printer = new Printer();
        printLog.addActionListener(printer);
    }

    private class Printer implements ActionListener {
        public void actionPerformed(ActionEvent event){
            List<String> log = new ArrayList<String>();
            try {
                List<String> command = new ArrayList<String>();
                command.add("git");
                command.add("reflog");
                ProcessBuilder builder = new ProcessBuilder(command);
                builder.directory(new File(adressField.getText()));
                Process process = builder.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String nextLine;
                while ((nextLine = reader.readLine()) != null) {
                    log.add(nextLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            result.setText("");
            for(int i = 0; i < log.size(); i++){
                result.setText(result.getText() + log.get(i) + "\n");
            }


        }
    }
}
