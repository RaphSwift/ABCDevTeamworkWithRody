import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float sommeBarnabe;
		int nbMagasins = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("definissez la somme de Barnabe > 1");
			sommeBarnabe = sc.nextFloat();
		} while (sommeBarnabe <=1);
		while (sommeBarnabe >= 2) {
			nbMagasins++;
			sommeBarnabe = (sommeBarnabe/2)-1;
		}
		if (sommeBarnabe > 0) {
			nbMagasins++;
			System.out.println("Barnab� � �t� dans " + nbMagasins + " magasins");
			System.out.println("dans le dernier il � d�pens�: " + sommeBarnabe + "�");
		} else {
			System.out.println("Barnab� � �t� dans " + nbMagasins + " magasins");
		}
		sc.close();
	}

}
