import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

interface WindowClosing extends WindowListener{
    public default void windowOpened(WindowEvent e){}
    public default void windowIconified(WindowEvent e){}
    public default void windowDeiconified(WindowEvent e){}
    public default void windowActivated(WindowEvent e){}
    public default void windowDeactivated(WindowEvent e){}
    public default void windowClosed(WindowEvent e){}
}

public class AWTListenerExample {
    public static void main(String[] args) {
        Frame screen = new Frame();
        Button button = new Button("Press me");
        screen.add(button);

//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Welcome to Java!",
//                        "Java Sample", JOptionPane.INFORMATION_MESSAGE);
//            }
//        });

        button.addActionListener(e ->
        JOptionPane.showMessageDialog(null, "Info box: Welcome to Java!",
                "Java Sample", JOptionPane.INFORMATION_MESSAGE)
        );
        screen.setSize(250, 400);
        screen.setVisible(true);

//        screen.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(1);
//            }
//        });
        screen.addWindowListener((WindowClosing)(e) -> System.exit(1));
    }
}
