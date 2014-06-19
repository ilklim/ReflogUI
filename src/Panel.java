import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        printLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    result.setText(Parser.printParsedLog(LogReader.read(adressField.getText())));
                } catch (Exception e1) {

                    result.setText("Something has gone wrong. Probably, incorrect directory");
                }
            }
        });
    }
}