import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(Panel.createReflogPanel());
    }
}
