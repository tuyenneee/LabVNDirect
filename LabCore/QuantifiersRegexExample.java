import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuantifiersRegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(t*\\S+)(\\S+)(.*)", Pattern.UNICODE_CHARACTER_CLASS);
        String text = "Dat hoc java";
        Matcher matcher = pattern.matcher(text);
        System.out.println(text+" ---> "+matcher.matches());

        String text1 = "Khai hoc java";
        Matcher matcher1 = pattern.matcher(text1);
        System.out.println(text1+" ---> "+matcher1.matches());

        String text2 = "Bao hoc java";
        Matcher matcher2 = pattern.matcher(text2);
        System.out.println(text2+" ---> "+matcher2.matches());
    }
}
