
import java.util.Calendar;
import java.util.Locale;

public class EnumExample{
    public enum VnDay{
        THU_HAI, THU_BA, CHU_NHAT, THU_BAY, KHONG_BIET;
        public static VnDay valueOf(Calendar calendar){
            String type = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, new Locale("vi"));
            switch(type){
                case "Th 3":
                    return THU_BA;
                case "Th 7":
                    return THU_BAY;
                case "CN":
                    return CHU_NHAT;
            }
            return KHONG_BIET;
        }
    }
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println("Hom nay la "+VnDay.valueOf(calendar));
    }
}