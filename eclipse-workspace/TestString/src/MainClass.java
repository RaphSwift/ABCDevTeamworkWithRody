import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1, str2;
		Scanner sc = new Scanner(System.in);
		str1 = sc.nextLine();
		str2 = sc.nextLine();
		
		if (str1.compareTo(str2)<0) {
			System.out.println(str1 + " " + str2);
		} else {
			System.out.println(str2 + " " + str1);
		}
		sc.close();
	}

}
