import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.UNICODE_CHARACTER_CLASS;

public class GroupRegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(\\w+)(\\S+)(.*)",
                UNICODE_CHARACTER_CLASS);
        String text = "toi hoc java";
        Matcher matcher = pattern.matcher(text);
        matcher.matches();
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
        System.out.println(matcher.group(3));



    }
}
