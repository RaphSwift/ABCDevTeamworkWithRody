import java.math.BigDecimal;

import references.RefObject;

public class MainClass {
	/* ICI JE N'AI PAS VU D'INTERET A METTRE UNE FONCTION 
	 * ET UN PASSAGE DE REFERENCE JE N'AI DONC FAIT QU'UNE PROCEDURE
	 */
	public static void main(String[] args) {
		RefObject<BigDecimal> moyenne=null;
		float nb1, nb2;
		nb1 = 10;
		nb2 = 15;
		calculerMoyenne(nb1,nb2,moyenne);
		System.out.println("la moyenne de "+ nb1 + " et "+ nb2 + " est de " + moyenne);
	}
	
	public static void calculerMoyenne(float a, float b, RefObject<BigDecimal> moyenne) {
		moyenne.argValue = new BigDecimal((a+b)/2.0f);
	}
}
