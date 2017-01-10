import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class Draw extends JPanel {

    private static final int SIZE = 400;
    private GeneralPath path = new GeneralPath();

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SIZE, SIZE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        double dt = Math.PI / 180;
        int w = getWidth() / 2;
        int h = getHeight() / 2;
        int r1 = 300;
        int r2 = 400;
        double r = 170;
        g2d.draw(new Ellipse2D.Double(w-r1/2,h-r1/2, r1,r1));
        g2d.draw(new Ellipse2D.Double(w-r2/2,h-r2/2, r2,r2));
        path.reset();
        path.moveTo(w+r, h);
        for (double t = 0; t < 2* Math.PI; t += dt) {
            r = r - 0.5;

            double x = r * Math.cos(t)+w;
            double y = r * Math.sin(t)+h;
            path.lineTo(x, y);
        }
        g2d.setColor(Color.red);
        g2d.draw(path);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(new Draw());
                f.pack();
                f.setVisible(true);
            }
        });
    }
}