import java.util.Scanner;

public class MainClass {
	// JEU D'ESSAI {5,13},{176,812}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nb1,nb2;
		System.out.println("Saisissez 2 nombres: ");
		nb1 = sc.nextInt();
		nb2 = sc.nextInt();
		System.out.println("LE PGCD de " + nb1 + " et " + nb2 + " est " + Calcul_PGCD_Euclide(nb1,nb2));
		sc.close();
	}
	
	public static int Calcul_PGCD(int a, int b) {
		while(a!=b) {
			if (a > b)
				a-=b;
			else
				b-=a;
		}
		return a;
	}
	
	public static int Calcul_PGCD_Euclide(int a, int b) {
		int dividende;
		int diviseur;
		int reste;
		dividende = Math.max(a,  b);
		diviseur = Math.min(a, b);
		reste = dividende%diviseur;
		while(reste >0) {
			dividende = diviseur;
			diviseur = reste;
			reste = dividende%diviseur;
			
		} 
		return diviseur;
	}
}
