import java.util.ArrayList;

public class MainClass {
	public static void main(String[] args) {
		ArrayList<Ville> villes= new ArrayList<Ville>();
		
		villes.addAll(Utils.getElements());
		ArrayList<Parcours> parcours = new ArrayList<Parcours>();
		for (int i = 0; i < villes.size(); i++) {
			Utils.getParcours(villes, new Parcours(), parcours,i);
		}
		Utils.haveMin(parcours);
		
		Ville[] villesArray = new Ville[villes.size()];
		for (int i = 0; i < villes.size(); i++) {
			villesArray[i] = villes.get(i);
		}
		System.out.println(Utils.toHeuristique(villesArray));
		//int[][] tab = Utils.getSwapFromId(3);
	}
	
}
