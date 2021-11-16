public class Prime {
    public static void main(String[] args) {
        int i;
        for(i=2;i<=100;i++){
            if(i - 2 == 0){
                System.out.println(i);
            }
            else if(i - 3 == 0){
                System.out.println(i);
            }
            else if(i - 5 == 0){
                System.out.println(i);
            }
            else if(i - 7 == 0){
                System.out.println(i);
            }
            if(i % 2 == 0){
            }
            else if(i % 3 == 0){
            }
            else if(i % 5 == 0){
            }
            else if(i % 7 == 0){
            }
            else{
                System.out.println(i);
            }
        }
    }
}
