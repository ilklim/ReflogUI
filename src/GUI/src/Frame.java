import javax.swing.*;

public class Frame {
    public static JFrame createReflogFrame() {
        JFrame frame = new JFrame();
        frame.setSize(550, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(Panel.createReflogPanel());
        return frame;
    }
}
