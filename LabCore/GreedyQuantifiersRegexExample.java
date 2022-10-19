import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GreedyQuantifiersRegexExample {
    public static void main(String[] args) {
        String text = "xxxjavaxxxxxxxxjava";
        Pattern pattern = Pattern.compile(".*va", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(text);
        System.out.println("Greedy ---> "+matcher.find());
        System.out.println(text.substring(matcher.regionStart(), matcher.regionEnd()));
        System.out.println("Greedy ---> "+matcher.find(matcher.regionEnd()));
    }
}
