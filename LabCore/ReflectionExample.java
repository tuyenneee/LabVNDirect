import java.lang.reflect.Field;

public class ReflectionExample {
    class TotalCaculator {
        private int TOTAL = 3;
    }

    static class ReflectionTest {
        public static void explore(Object obj) {
            Class<?> clazz = obj.getClass();
            System.out.println("Clazz name: " + clazz.getName());

            try {
                Field field = clazz.getDeclaredField("TOTAL");
                System.out.println("Total value is " + field.get(obj));
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        public static void main(String[] args) {
            TotalCaculator obj;
            try {
                obj = TotalCaculator.class.newInstance();
                explore(obj);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
