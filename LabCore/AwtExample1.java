
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.AbstractList;
import java.util.ArrayList;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class AwtExample1 extends Frame {

//    public AwtExample1() {
//        Frame f = new Frame();
//        f.setLayout(new FlowLayout());
//        Button pushButton = new Button("Press me");
//        add(pushButton);
//    }
//
//    public static void main(String[] args) {
//        AwtExample1 screen = new AwtExample1();
//        screen.setSize(500, 100);
//        screen.setVisible(true);
//        
//        screen.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(1);
//            }
//        });
//    }
//    public AwtExample1(){
//        Frame f = new Frame();
//        //f.setLayout(new FlowLayout());
//        f.setLayout(new BorderLayout());
//        
//        final TextField text = new TextField();
//        add(text, BorderLayout.CENTER);
//        Button pushButton = new Button("Press me");
//        add(pushButton, BorderLayout.LINE_END);
//        pushButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                AwtExample1.this.setTitle(text.getText());
//            }
//        });
//    }
    public static void main(String[] args) {
        AwtExample1 screen = new AwtExample1();
        screen.setSize(500, 100);
        screen.setVisible(true);

        screen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }

//    private List list;
//    public AwtExample1(){
//        final TextField text = new TextField();
//        add(text, BorderLayout.PAGE_START);
//        list = new List(4, true);
//        IntStream.range(0, 10).forEach(i -> {
//            list.add("Item "+i);
//        });
//        add(list, BorderLayout.CENTER);
//        Button pushButton = new Button("Press me");
//        add(pushButton, BorderLayout.PAGE_END);
//        pushButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String[] values = list.getSelectedItems();
//                StringBuilder builder = new StringBuilder();
//                Arrays.stream(values).forEach(value -> {
//                    if(builder.length()>0) builder.append("; ");
//                    builder.append(value);
//                });
//                text.setText(builder.toString());
//            }
//        });
////        pushButton.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                Supplier<StringBuilder> supplier = StringBuilder::new;
////                BiConsumer<StringBuilder, String> consumer = (builder, value) -> {
////                    if (builder.length() > 0) {
////                        builder.append(", ");
////                    }
////                    builder.append(value);
////                };
////                BinaryOperator<StringBuilder> operator = StringBuilder::append;
////                Function<StringBuilder, String> finisher = StringBuilder::toString;
////
////                String[] values = list.getSelectedItems();
////                Collector<String, StringBuilder, String> collector
////                        = Collector.of(supplier, consumer, operator, finisher);
////                text.setText(Arrays.stream(values).collect(collector));
////            }
////        });
//    } 
//    public AwtExample1() {
//        MenuBar menuBar = new MenuBar();
//        setMenuBar(menuBar);
//        
//        Menu menu = new Menu("File");
//        menuBar.add(menu);
//        
//        MenuItem menuItem = 
//                new MenuItem("Exit", new MenuShortcut('X'));
//        menu.add(menuItem);
//        menuItem.addActionListener(e -> {
//            System.exit(1);
//        });
//    }
}

class ShowExampleActionListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        AwtExample1 screen = new AwtExample1();
        screen.setSize(250, 400);
        screen.setVisible(true);
        
        screen.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(1);
            }
        });
    }
    
}

class SystemTrayExample{
    public static void main(String[] args) {
        SystemTray tray = SystemTray.getSystemTray();
        
        Icon icon = UIManager.getIcon("OptionPane.informationIcon");
        Image image = ((ImageIcon) icon).getImage();
        
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo", null);
        trayIcon.addActionListener(new ShowExampleActionListener());
        try {
            tray.add(trayIcon);
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }
}