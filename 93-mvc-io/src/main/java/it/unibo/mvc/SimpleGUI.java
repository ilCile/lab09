package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();
    private final SimpleController controller = new SimpleController();

    /** */
    public SimpleGUI() {
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel panel1 = new JPanel(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        final JTextField text = new JTextField();
        final JButton print = new JButton("print");
        final JButton history = new JButton("show history");

        textArea.setEditable(false);

        panel1.add(print, BorderLayout.LINE_START);
        panel1.add(history, BorderLayout.LINE_END);
        panel.add(text, BorderLayout.NORTH);
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(panel1, BorderLayout.SOUTH); 

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        print.addActionListener(e -> {
            controller.setStringToPrint(text.getText());
            controller.printString();
        });

        history.addActionListener(e -> textArea.setText(String.join("\n", controller.getPrintHistory())));
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /** @param args inutile. */
    public static void main(final String... args) {
        new SimpleGUI().display();
    }
}
