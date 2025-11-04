import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class Panel extends JPanel {

    private final JLabel clockFace;
    private final TimeData timeData;
    private String timeText;

    public Panel() {
        this.setLayout(new BorderLayout());

        clockFace = new JLabel();
        Font font1 = new Font("Arial", Font.BOLD, 24);
        clockFace.setFont(font1);
        clockFace.setForeground(Color.MAGENTA);
        clockFace.setBackground(Color.BLACK);
        clockFace.setHorizontalAlignment(SwingConstants.CENTER);
        clockFace.setVerticalAlignment(SwingConstants.CENTER);
        this.add(clockFace, BorderLayout.CENTER);
        this.setBackground(Color.PINK);

        this.add(clockFace, BorderLayout.CENTER);

        timeData = new TimeData(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockFace.setText(timeData.startTime(timeText));
            }
        });

        timeData.start();
    }
}

