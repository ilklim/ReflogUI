import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//todo reflog keys

public class Panel {

    public static JPanel createReflogPanel() {
        final JPanel panel = new JPanel(new BorderLayout());
        final JButton printLog = new JButton("Print log");
        final JTextField adressField = new JTextField(System.getProperty("user.dir"), 40);
        final JTextPane result = new JTextPane();
        JPanel noWrapPanel = new JPanel( new BorderLayout() );
        noWrapPanel.add(result);
        final Style style = result.addStyle("Reflog style", null);
        final JScrollPane sp = new JScrollPane(noWrapPanel);

        JPanel textFieldAndButton = new JPanel(new BorderLayout());
        textFieldAndButton.add(adressField, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(printLog);
        textFieldAndButton.add(buttonPanel, BorderLayout.CENTER);

        panel.add(textFieldAndButton, BorderLayout.NORTH);
        panel.add(sp, BorderLayout.CENTER);

        printLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String reflog = ReflogReader.getReflog(new File(adressField.getText()));
                    Parser.coloredPrint(reflog, result, style);
                } catch (Exception e1) {
                    result.setText(e1.toString());
                }
            }
        });
        return panel;
    }
}