import java.util.Scanner;

public class TriTroisNombre {
	public static void main(String[] args) {
		
		float nb1, nb2, nb3;
		float r1,r2,r3; // resultat
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Programme de tri de trois nombres");
		System.out.println("Premier nombre svp: ");
		nb1 = sc.nextFloat();
		System.out.println("Deuxieme nombre svp: ");
		nb2 = sc.nextFloat();
		System.out.println("Troisieme nombre svp: ");
		nb3 = sc.nextFloat();
		
		// TRAITEMENT
		if (nb1 < nb2) {
			r1 = nb1;
			r2 = nb2;
			if(nb1 < nb3) {
				if (nb2<nb3) {
					r3 = nb3;
				} else {
					r3 = nb2;
					r2 = nb3;
				}
			}
			else {
				r1 = nb3;
				r2 = nb1;
				r3 = nb2;
			}
		} else {
			r1 = nb2;
			r2 = nb1;
			if(nb2 < nb3) {
				if (nb1<nb3) {
					r3 = nb3;
				} else {
					r3 = nb1;
					r2 = nb3;
				}
			}
			else {
				r1 = nb3;
				r2 = nb2;
				r3 = nb1;
			}
		}
		System.out.println(r1 + "<=" + r2 + "<=" + r3);
		sc.close();
	}
}
