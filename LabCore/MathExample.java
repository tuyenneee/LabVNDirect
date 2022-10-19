
class MathExample {

//    public static void main(String[] args) {
//        int i = 4;
//        int j = -8;
//        double x = 47.6;
//        double y = 1.4;
//        
//        System.out.println("|"+j+"| is "+ Math.abs(j));
//        System.out.println("|"+x+"| is "+ Math.abs(x));
//        
//        System.out.println(x+" is approximately "+Math.round(x));
//        System.out.println("The ceiling of "+i+" is "+Math.ceil(i));
//        System.out.println("The ceiling of "+y+" is "+Math.ceil(y));
//        System.out.println("The floor of "+x+" is "+Math.floor(x));
//        System.out.println("Min("+x+","+y+") is "+Math.min(x, y));
//    }
    
    public static void main(String[] args) {
        System.out.println("Pi is "+Math.PI);
        double a = 45.0*2.0*(Math.PI/360.0);
        System.out.println("cos("+a+") is "+Math.cos(a));
        
        double value = 0.707;
        System.out.println("acos("+value+") is "+Math.acos(value));
        
        System.out.println("exp(0.0) is "+Math.exp(0.0));
        System.out.println("log(10.0) is "+Math.log(10.0));
        System.out.println("pow(2.0, 2.0) is "+Math.pow(2.0, 2.0));
        
        System.out.println("Here's one random number: "+Math.random());
    }

}
