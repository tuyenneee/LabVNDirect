import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReluctantQuantifiersRegexExample {
    public static void main(String[] args) {
        String text = "xxxjavaxxxxxxxxjava";
        Pattern pattern = Pattern.compile(".*+va");
        Matcher matcher = pattern.matcher(text);
        System.out.println("Reluctant ---> "+matcher.find());
        //System.out.println(text.substring(matcher.start(), matcher.end()));

//        System.out.println("Reluctant ---> "+matcher.find(matcher.end()));
//        System.out.println(text.substring(matcher.start(), matcher.end()));
    }
}
