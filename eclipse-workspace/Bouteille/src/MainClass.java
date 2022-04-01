
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bouteille maBouteille = new Bouteille();
		getState(maBouteille);
		System.out.println(maBouteille.vider() ? "action vider r�ussie" : "action vider impossible");
		getState(maBouteille);
		maBouteille.ouvrir();
		getState(maBouteille);
		System.out.println(maBouteille.vider() ? "action vider r�ussie" : "action vider impossible");
		getState(maBouteille);
		//maBouteille.fermer();
		
		System.out.println(maBouteille.remplir() ? "La bouteille a bien �t� remplie" : "L'action remplir � �chou�");
		getState(maBouteille);
		//System.out.println(maBouteille.ouvrir() ? "Ouverture r�ussie" : "Ouverture impossible");
			
	}

	public static void getState(Bouteille par) {
		String str = "";
		str += (par.getOpenState()== Bouteille.CLOSED ? "La bouteille est ferme" : "La bouteille est ouverte") + " et " + 
		(par.getFilled() == Bouteille.FILLED ? "La bouteille est pleine" : "La bouteille est vide");
		System.out.println(str);
	}
}
