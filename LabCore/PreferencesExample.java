
import java.util.prefs.Preferences;

class PreferencesExample{
    public static void main(String[] args) {
   Preferences preferences = 
           Preferences.userNodeForPackage(PreferencesExample.class);
        System.out.println("Old value = "+preferences.get("data1", "No value"));
        preferences.put("data1", "value"+System.currentTimeMillis());
        System.out.println("New value = "+preferences.get("data1", "No value"));
}
}