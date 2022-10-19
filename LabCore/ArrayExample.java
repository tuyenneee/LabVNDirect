class ArrayExample{
    public static void main(String[] args) {
   float[] values = {4.5f, 5.5f, 20, 12.5f, 12.8f};
   float total = 0;
   for(float value : values){
       total+=value;
   }
        System.out.println("The total value of array is "+total);
        
    float max = values[0];
        for (int i = 0; i < values.length; i++) {
            if(max<values[i]){
                max = values[i];
            }
        }
        System.out.println("Gia tri lon nhat = "+max);
}
}