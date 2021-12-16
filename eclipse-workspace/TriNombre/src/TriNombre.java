import java.util.Scanner;

public class TriNombre {
	// JEU D'ESSAI {2,2;0,15}{0,15;2,2}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float nb1;
		float nb2;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Programme de tri de nombre");
		System.out.println("Veuillez entrez le premier nombre");
		nb1 = sc.nextFloat();
		System.out.println("Veuillez entrez le deuxieme nombre");
		nb2= sc.nextFloat();
		
		if (nb1 > nb2) {
			System.out.println(nb2 + " <= " + nb1);
		} else {
			System.out.println(nb1 + " <= " + nb2);
		}
		sc.close();
	}
	
	
	

}
