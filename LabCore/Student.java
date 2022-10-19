import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Student {
    private int id;
    private int age;
    private String name;

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(int age, String name) {
        id = (int) (Math.random()*1000);
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return id+": "+name+" : "+age;
    }

}
interface Filter<T>{
    public boolean valid(T t);
}

class StudentTest{
    public static List<Student> filter(List<Student> students,Filter<Student> pred){
        List list = new ArrayList<>();
        for (Student student : students){
            if(pred.valid(student)) list.add(student);
        }
        return list;
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student(23, "Tran Van A"));
        students.add(new Student(34, "Nguyen Thi B"));
        students.add(new Student(46, "Ta Van C"));
        students.add(new Student(15, "Nguyen Thi C"));

//        Filter<Student> older = student -> student.getAge() >= 30;
//        List<Student> filtered = filter(students, older);

//        for (Student student : filtered){
//            System.out.println(student);
//        }
//        filtered.forEach(student -> System.out.println(student));

//        Stream<Student> stream = students.stream().filter(student -> student.getAge() >= 30);
//        stream.forEach(student -> System.out.println(student));

//        Collections.sort(students,
//                (Student student1, Student student2) -> student2.getAge() - student1.getAge());
//        students.forEach(student -> System.out.println(student));

//        Stream<Student> stream = students.stream().sorted(
//                (Student s1, Student s2) -> s2.getAge() - s1.getAge());
//        stream.forEach(s -> System.out.println(s));

        Comparator<Student> comparator = (Student s1, Student s2) -> s2.getAge()- s1.getAge();
        Stream<Student> stream = students.stream().sorted(comparator);
        stream.forEach(s -> System.out.println(s));

        System.out.println("\nMax is "+students.stream().max(comparator));

        int sum = students.stream().mapToInt(Student::getAge).sum();
        System.out.println("Average of age is "+sum/students.size());

        students.parallelStream().forEach((it) -> System.out.println(Thread.currentThread().getName()+" hello "+it.getName()));
    }
}
