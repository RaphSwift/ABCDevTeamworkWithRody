import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int saisie = sc.nextInt();
		if (saisie < 5 ) {
			System.out.println("trop petit");
		} else if (saisie > 10) {
			System.out.println("trop grand");
		} else {
			System.out.println("OK");
		}
		sc.close();
	}

}
