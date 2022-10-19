
import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CheckBoxExample extends Frame{
    public CheckBoxExample(){
        setLayout(new FlowLayout());
        
        Checkbox checkbox1 = new Checkbox("Option 1");
        checkbox1.setFont(new Font("Arial", Font.BOLD, 14));
        add(checkbox1);
        
        final Checkbox checkbox2 = new Checkbox("Option 2");
        add(checkbox2);
        checkbox2.addItemListener(e -> {
            System.out.println("Value 2 = "+checkbox2.getState());
        });
    }
    public static void main(String[] args) {
        CheckBoxExample screen = new CheckBoxExample();
        screen.setSize(500, 100);
        screen.setVisible(true);

        screen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }
}