import java.util.Scanner;

public class CalculAireSecteurCirculaire {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float rayon;
		float angle;
		float aireSecteurCirculaire;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez indiquer le rayon de la sphère: ");
		rayon = sc.nextFloat();
		System.out.println("Veuillez indiquer l'angle: ");
		angle = sc.nextFloat();
		
		aireSecteurCirculaire = (float) (Math.PI*Math.pow(rayon, 2)*angle/360.0f);
		
		System.out.println("L'aire du secteur circulaire de la sphere de rayon : " + rayon + " et d'angle " + angle + " est de : " + aireSecteurCirculaire);
		sc.close();
	}

}
