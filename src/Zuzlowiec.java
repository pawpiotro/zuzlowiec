import javax.swing.*;
import java.io.PrintStream;

public class Zuzlowiec extends JFrame{
    private JPanel rootPanel;
    private JTextArea text;
    private JScrollPane scrollPane;

    public Zuzlowiec() {

        JFrame frame = new JFrame("Zuzlowiec");
        frame.setContentPane(this.rootPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        setCustomOutput(text);
    }

    private void setCustomOutput(JTextArea area) {
        PrintStream print_stream = new PrintStream(new CustomOutputStream(area));
        //podmiana standardowego strumienia
        System.setOut(print_stream);
        System.setErr(print_stream);
    }
}
