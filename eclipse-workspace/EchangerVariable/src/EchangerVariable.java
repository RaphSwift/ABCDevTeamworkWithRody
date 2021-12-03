import java.util.Scanner;

public class EchangerVariable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float nb1;
		float nb2;
		float nbTemp;
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrez le premier nombre");
		nb1 = sc.nextFloat();
		System.out.println("Veuillez entrez le deuxieme nombre");
		nb2 = sc.nextFloat();
		nbTemp = nb1;
		nb1 = nb2;
		nb2 = nbTemp;
		System.out.println("Inversion " + nb1 + "/" + nb2);
		sc.close();
		
	}

}
