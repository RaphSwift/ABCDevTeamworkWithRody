import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir un nombre svp");
		int nb = sc.nextInt();
		for (int i = nb; i <= nb+10;i++)
			System.out.println(i);
		sc.close();
	}
}
