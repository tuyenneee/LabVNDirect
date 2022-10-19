
import java.util.Calendar;
import static java.util.Calendar.DATE;
import java.util.Locale;


class SwitchStatement{
    String getTypeOfDay(Calendar calender){
        String type = calender.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, new Locale("vi"));
        switch(type){
            case "Th 2":
                return "Monday";
            case "Th 3":
                return "Tuesday";
            case "Th 4":
                return "Wednesday";
            case "Th 5":
                return "Thurday";
            case "Th 6":
                return "Friday";
            case "Th 7":
                return "Satuday";
            case "CN":
                return "Weekend";
        }
        return "Unknown";
    }
    public static void main(String[] args) {
        SwitchStatement statement = new SwitchStatement();
        Calendar calendar = Calendar.getInstance();
        System.out.println("Today is "+statement.getTypeOfDay(calendar));
        calendar.set(Calendar.DATE, calendar.get(DATE)+1);
        System.out.println("Tomorrow is "+statement.getTypeOfDay(calendar));
    }
}