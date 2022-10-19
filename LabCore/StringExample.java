
class StringExample {
//    public static void main(String[] args) {
//   String text = "say hello to everyone";
//        System.out.println("Length of text is "+text.length());
//        System.out.println("vi tri cua tu hello la "+text.indexOf("hello"));
//        System.out.println("cat tu = "+text.substring(4, 8));
//}

//    public static void main(String[] args) {
//   char[] chars = {'h', 'e', 'l', 'l', 'o'};
//   String text = new String(chars);
//        //System.out.println(text);
//        
//        String text1 = text.concat(" ");
//        String text2 = text1.concat(args[0]);
//        //System.out.println("New value is: "+text2);
//        System.out.println("hello java".equals(text2));
//}
//    public static void main(String[] args) {
//   String text = "Absolute Value";
//   byte[] bytes = text.getBytes();
//        System.out.println("Byte values are");
//        for (int i = 0; i < bytes.length; i++) {
//            if(i==bytes.length-1){
//                
//            System.out.println((int) bytes[i]+" ");
//            }else
//            System.out.println((int) bytes[i]+",");
//            }
//        }
//    public static void main(String[] args) {
//   String text = "say hello to everyone";
//        System.out.println("Length of text is "+text.length());
//        int i = 0;
//        while(i<text.length()){
//            char c = text.charAt(i);
//            if(c==' '){
//                i++;
//                continue;
//            }
//            System.out.println("Character at "+i+" is "+c);
//            i++;
//        }
//}
    public static void main(String[] args) {
        byte[] bytes = {99, 58, 80, 31, 69, 101, 112, 44, 32, 104, 105, 31, 70,
            65, 112, 44, 32, 103, 105, 31, 70, 65, 116};
        try {
            System.out.println(new String(bytes, "utf8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
