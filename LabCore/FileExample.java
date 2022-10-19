import java.io.File;

public class FileExample {
    public static void main(String[] args) {
        File file = new File("C:\\Temp");
        System.out.println("This is " + (file.isFile() ? "file" : "folder") + "!");
//        if(file.isDirectory()){
//            System.out.println("This is folder");
//        }
//        else {
//            System.out.println("This is file");
//        }
        File[] files = file.listFiles();
        for (File f : files){
            System.out.println(f.getName());
            System.out.println(f.length());
        }
    }
}
