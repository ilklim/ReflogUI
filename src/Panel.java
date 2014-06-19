import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//todo reflog keys

public class Panel {

    public static JPanel createReflogPanel() {
        final JPanel panel = new JPanel();
        final JButton printLog = new JButton("Print log");
        final JTextField adressField = new JTextField(40);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        final JTextArea result = new JTextArea(12, 45);
        final JScrollPane sp = new JScrollPane(result);

        panel.add(adressField);
        panel.add(printLog);
        panel.add(sp);

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
        return panel;
    }
}