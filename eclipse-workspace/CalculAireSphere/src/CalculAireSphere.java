import java.util.Scanner;

public class CalculAireSphere {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		float rayon;
		float aire;
		float volume;
		
		System.out.println("Veuillez indiquer le rayon de la Sphere à calculer: ");
		rayon = sc.nextFloat();
		
		aire = (float) (4.0f*Math.PI*Math.pow(rayon,2));
		volume = (float) (4.0f/3.f*Math.PI*Math.pow(rayon,3));
		
		System.out.println("l'aire de la sphere est de: " + aire + " 10et son volume: " + volume);
		sc.close();
	}

}
