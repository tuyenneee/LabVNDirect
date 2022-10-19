
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CollectionsExample {

//    public static void main(String[] args) {
//        Short[] values = {1, 2, 4, 5, 6, 7, 8, 9};
//        List<Short> list = new ArrayList<>();
//        Collections.addAll(list, values);
//        Collections.reverse(list);
//        list.toArray(values);
//        //System.out.println(list);
//        for (int i = 0; i < values.length; i++) {
//            if (i < values.length - 1) {
//                System.out.print(values[i] + ", ");
//            }
//            else
//            {
//                System.out.println(values[i] + "");
//            }
//        }
//    }
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        Collections.addAll(list, "Tu", "An", "Hoa", "Binh");
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                System.out.print(list.get(i) + ", ");
            } else {
                System.out.print(list.get(i) + "");
            }
        }
        System.out.println("");
        System.out.println("Vi tri thu " + Collections.binarySearch(list, "Hoa"));
    }
}
