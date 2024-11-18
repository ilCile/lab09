package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();
    private Controller controller = new Controller();

    public SimpleGUIWithFileChooser(){
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel panel1 = new JPanel(new BorderLayout());
        final JTextField text = new JTextField();
        final JButton browse = new JButton("Browse...");
        final JTextArea textArea = new JTextArea();
        final JButton save = new JButton("save");

        text.setText(controller.getCurrentFile().getPath());
        text.setEditable(false);

        panel1.add(text, BorderLayout.CENTER);
        panel1.add(browse, BorderLayout.LINE_END);

        panel.add(panel1, BorderLayout.NORTH);
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(save, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        browse.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                final int res = chooser.showSaveDialog(frame);
                if(res == JFileChooser.APPROVE_OPTION){
                    controller.setCurrentFile(chooser.getSelectedFile());
                    text.setText(controller.getCurrentFile().getPath());
                }else if(res != JFileChooser.CANCEL_OPTION){
                    JOptionPane.showMessageDialog(frame, "ERRORE", "ERRORE", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.write(textArea.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
            
        });
    }

    private void display(){
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public void setController(final Controller controller){
        this.controller = controller;
    }

    public static void main(final String... args){
        new SimpleGUIWithFileChooser().display();
    }
}
