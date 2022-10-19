
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;

class RobotExample{
    static void leftClick(Robot robot){
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(200);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(200);
    }
    
    static void type(Robot robot, String s){
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            int code = b;
            if(code > 96 && code < 123) code = code -32;
            robot.delay(40);
            robot.keyPress(code);
            robot.keyRelease(code);
        }
    }
    public static void main(String[] args) throws AWTException, IOException {
        Robot robot = new Robot();
        robot.delay(2*1000);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //robot.mouseMove((int) dim.getWidth()/2, (int) dim.getHeight()/2);
        leftClick(robot);
        
        String userDir = System.getProperty("user.home");
        File file = new File(new File(userDir), "temp.txt");
        if(!file.exists()) file.createNewFile();
        Desktop.getDesktop().edit(file);
        
        robot.delay(500);
        type(robot, "toi/ la de nhat quoc su Hoa Ki Tran Dan co van "
                + "toi cao cua tong thong My Donal Trump djalskdasljdallasjdlkajadjslkdasl");
        robot.delay(5*1000);
        System.exit(0);
    }
}



