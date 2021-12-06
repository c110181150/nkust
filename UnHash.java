public class UnHash {
    public static void main (String[] args) {
        System.out.println(revHash(6933552791181934L));
        System.out.println(hash("justdoit")); //574318821802
    }

    public static String letters = "cdefghijlmnoqstuvxz";

    public static String revHash(long hash) {
        String result = "" ;
        String NewResult= "" ;

        while (hash != 7) {
            result = result + letters.charAt((int) (hash % 23));
            hash = hash / 23;
        }
        for (int i =result.length();i>=1;i--) {
            NewResult = NewResult + result.charAt(i - 1);
        }
        return NewResult ;
    }

    public static long hash(String s){
        long h = 7;
        for (int i = 0; i < s.length(); i++){
            h = h * 23 + letters.indexOf(s.charAt(i));
        }
        return h;
    }

}
