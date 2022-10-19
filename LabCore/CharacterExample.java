
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;


public class CharacterExample {

//    private static int countDigit(String value) {
//        int counter = 0;
//        for (int i = 0; i < value.length(); i++) {
//            char c = value.charAt(i);
//            if(Character.isWhitespace(c)){
//                counter++;
//            }
//        }
//        return counter;
//    }
//
//    public static void main(String[] args) {
//        System.out.println("There are " + countDigit(args[0]) + " digits in the text.");
//    }
    
    private static int countDigit(String value){
        AtomicInteger counter = new AtomicInteger(0);
        IntStream stream = value.chars();
        stream.forEach(c -> {
            if(Character.isDigit(c)) counter.incrementAndGet();
        });
        return counter.get();
    }
    public static void main(String[] args) {
        System.out.println("There are " + countDigit(args[0]) + " digits in the text.");
    }
}
