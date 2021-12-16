
public class MainClass {
	public static void main(String[] args) {
		int[] tableauATrier = new int[8];
		tableauATrier= new int[]{16,14,12,10,8,6,4,2};
		int iteratorMinTemporaire;
		int iteratorValeurCompare;
		int valeurTemporaire;
		int iteratorValeurCourrante;
		
		for (iteratorValeurCourrante = 0; iteratorValeurCourrante < (tableauATrier.length)-1; iteratorValeurCourrante++) {
			iteratorMinTemporaire = rechercheIterateurMin(tableauATrier,iteratorValeurCourrante);
			if (iteratorValeurCourrante != iteratorMinTemporaire) {
				valeurTemporaire = tableauATrier[iteratorValeurCourrante];
				tableauATrier[iteratorValeurCourrante] = tableauATrier[iteratorMinTemporaire];
				tableauATrier[iteratorMinTemporaire] = valeurTemporaire;
			}
		}
		System.out.println("LA LISTE EN SORTIE PAR ORDRE CROISSANT:");
		for (iteratorValeurCompare = 0; iteratorValeurCompare < tableauATrier.length; iteratorValeurCompare++) {
			System.out.print(tableauATrier[iteratorValeurCompare] + "/");
		}
	}
	
	public static int rechercheIterateurMin(int[] tableau, int start) {
		int iterateurMin;
		iterateurMin = start;
		for (int itValCompare = start+1; itValCompare < tableau.length;itValCompare++) {
			if (tableau[itValCompare] < tableau[iterateurMin])
				iterateurMin = itValCompare;
		}
		return iterateurMin;
	}
}
