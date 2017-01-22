package ResultService;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Random;

public class Draw extends JPanel {

    private final int SIZE;
    private GeneralPath path = new GeneralPath();

    private ArrayList<FitnessFunction.Coordinates> coords;
    private boolean drawBest;

    public Draw(int size) {
        drawBest = false;
        SIZE = size;
    }

    public Draw(ArrayList<FitnessFunction.Coordinates> coordinates, int size) {
        drawBest = true;
        coords = coordinates;
        SIZE = size;
    }

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
        int w = getWidth() / 2;
        int h = getHeight() / 2;
        int r1 = (int) ((3.0 / 4.0) * SIZE);
        int r2 = SIZE;
        double scale = SIZE / (2.0 * 40.0);
        double r = 35.0 * scale;
        g2d.draw(new Ellipse2D.Double(w - r1 / 2, h - r1 / 2, r1, r1));
        g2d.draw(new Ellipse2D.Double(w - r2 / 2, h - r2 / 2, r2, r2));

        if (drawBest) {
            path.reset();
            path.moveTo(w + r, h);
            g2d.setColor(Color.red);
            for (FitnessFunction.Coordinates c : coords) {
                double x = scale * c.getR() * Math.cos(c.getPhi()) + w;
                double y = scale * c.getR() * Math.sin(c.getPhi()) + h;
                path.lineTo(x, y);
            }
            g2d.draw(path);
        } else {
            for (ArrayList<FitnessFunction.Coordinates> arrayElement : AlgoEvoZuzlowiec.allCoords) {
                path.reset();
                path.moveTo(w + r, h);
                Random random = new Random();
                Color randomColor = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
                g2d.setColor(randomColor);
                for (FitnessFunction.Coordinates c : arrayElement) {
                    double x = scale * c.getR() * Math.cos(c.getPhi()) + w;
                    double y = scale * c.getR() * Math.sin(c.getPhi()) + h;
                    path.lineTo(x, y);
                }
                g2d.draw(path);
            }
        }
    }

    public void init() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(this);
        f.pack();
        f.setVisible(true);
    }
}