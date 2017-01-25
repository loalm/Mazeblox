import javax.swing.*;
import java.awt.*;

/**
 * Created by lovealmgren on 2017-01-24.
 */
public class Renderer extends JPanel {

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Mazeblox.mazeblox.repaint(g);
    }
}
