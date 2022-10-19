public class Person{
    private String name;
    public Person(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}

class PersonTest{
    public static void main(String[] args) {
        Person person1 = new Person(args[0]);
        Person person2 = new Person(args[1]);
        
        System.out.println("Person 1's name is "+person1.getName());
        System.out.println("Person 2's name is "+person2.getName());
    }
}