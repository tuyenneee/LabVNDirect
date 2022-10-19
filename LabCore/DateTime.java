
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

class DateTime{
    public static void main(String[] args) {
        Locale locale = new Locale("en", "EN");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE, dd MMMM yyyy", locale);
        Calendar calender = Calendar.getInstance();
        System.out.println(dateFormat.format(calender.getTime()));
    }
}