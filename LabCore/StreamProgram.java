
import java.util.Arrays;

class StreamProgram{
    public static void main(String[] args) {
   System.out.println("Length of arguments " + args.length);
    Arrays.stream(args).forEach((String value) -> {
        System.out.println("Value is "+value);
    });
}
}