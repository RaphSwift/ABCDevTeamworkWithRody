public class MainClass {
       
       public static void main(String[] args) {
             System.out.println(genererConway(9));
       }
       
       public static String genererConway(int id) {
             String rt = "";
             if (id <= 1) {
                    return "1";
             } else {
                    rt = genererConway(id-1);
                    String newReturn = "";
                    char tmp = rt.charAt(0);
                    int nb = 0;
                    boolean finded4 = false;
                    for (int i = 0; i < rt.length();i++) {
                           if (tmp == rt.charAt(i)) {
                                  nb++;
                           } else {
                                  if (nb >= 4) {
                                        finded4 = true;
                                  }
                                  newReturn+= nb+""+tmp;
                                  nb = 1;
                                  tmp = rt.charAt(i);
                           }
                    }
                    if (nb>=4 || finded4) {
                           return rt;
                    }
                    newReturn+= nb+""+tmp;
                    return newReturn;
             }
       }
}
