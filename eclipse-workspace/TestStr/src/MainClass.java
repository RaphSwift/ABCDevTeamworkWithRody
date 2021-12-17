import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str;
		str = sc.next();
		
		if (str=="oui") {
			System.out.print("ok");
		} else {
			System.out.print("nop");
		}
		sc.close();
	}

}
