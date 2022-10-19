
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThrowableExample {

    public static class SaiSoException extends Exception {

        private String so;

        public SaiSoException(String value) {
            this.so = value;
        }

        public String getMessage() {
            return "Gia tri \"" + so + "\" khong phai la so!";
        }
    }

    static int toNumber(String value) throws SaiSoException {
        try {
            Integer integer = Integer.parseInt(value);
            return integer.intValue();
        } catch (NumberFormatException e) {
            throw new SaiSoException(value);
        }
    }

//    public static void main(String[] args) {
//        try {
//            System.out.println("Number = " + toNumber(args[0]));
//        } catch (SaiSoException e) {
//            System.err.println(e.getMessage());
//        }
//    }
    private final static Logger LOGGER = 
            Logger.getLogger(ThrowableExample.class.getName());
    public static void main(String[] args) {
        try {
            System.out.println("Number = "+toNumber(args[0]));
        } catch (SaiSoException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }
}
