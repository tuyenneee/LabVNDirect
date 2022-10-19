
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class LikedListExample {

//    public static void main(String[] args) {
//        List<String> list = new LinkedList<>();
//        for (String ele : args) {
//            list.add(ele);
//        }
//        Iterator<String> iterator = list.iterator();
//        while(iterator.hasNext()){
//            System.out.println("===>"+iterator.next());
//        }
//    }
    public static void main(String[] args) {
        List<String> list = new LinkedList<>(Arrays.asList(args));
        list.forEach(value -> {
            System.out.println(value);
        });
    }
}
