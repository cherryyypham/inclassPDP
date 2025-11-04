import javax.swing.JFrame;

/**
 * The type Frame.
 */
public class Frame {

    JFrame frame1;

    /**
     * Instantiates a new Frame.
     *
     * @param panel the panel
     */
    public Frame(Panel panel) {
        frame1 = new JFrame("PDP Clock :)");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(500, 150);
        frame1.add(panel);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}