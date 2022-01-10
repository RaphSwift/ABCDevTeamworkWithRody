import java.util.ArrayList;
import java.util.Random;

public class Joueur_Ordi extends Joueur{
	public Joueur_Ordi(Joueur_Ordi j) {
		this (j.nom,j.couleur,j.score);
	}
	
	public Joueur_Ordi(String _nom, char _couleur, byte _score) {
		super(_nom,_couleur,_score);
	}
	
	public Joueur_Ordi(String _nom, char _couleur) {
		super(_nom,_couleur);
	}
	
	/* return true if they are all different else
	 * return false
	 */
	public boolean compareHashCode(ArrayList<Plateau> px) {
		boolean rt = true;
		int i, j;
		i=0;
		while (rt && i<px.size()) {
			j=i+1;
			while (rt && j<px.size()) {
				if (px.get(i).hashCode() == px.get(j).hashCode()) {
					rt = false;
				}
				j++;
			}
			i++;
		}
		return rt;
	}
	
	public byte jouer(Plateau p) {
		ArrayList<Plateau> plateaux = new ArrayList<Plateau>();
		ArrayList<Plateau> plateaux_adversaire = new ArrayList<Plateau>();
		//ArrayList<Plateau> rts = new ArrayList<Plateau>();
		byte id = -1;
		byte finded = -1;
		byte i=0;
		for (i = 0; i < p.getWidth(); i++) {
			id++;
			try {
				plateaux.add((Plateau)p.clone());
				if (!plateaux.get(id).ajouterCoup(i, this)) {
					id--;
					plateaux.remove(id);
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		while (i < plateaux.size() && finded<0) {
			if (plateaux.get(i).getWin() != ' ') {
				finded = plateaux.get(i).getLastX();
			}
			i++;
		}	
		if (finded != -1) {
			plateaux.clear();
			plateaux_adversaire.clear();
			return finded;
		}
		// UN COUP EMPECHE IL L'ADVERSAIRE D'AVOIR UNE VICTOIRE
		Joueur tmp = null;
		if (couleur == Winner.RED_WINNER.getLetter()) {
			tmp = new Joueur(" ",Winner.YELLOW_WINNER.getLetter());
		} else {
			tmp = new Joueur(" ",Winner.RED_WINNER.getLetter());
		}
				
		id = -1;
		
		for (i = 0; i < p.getWidth(); i++) {
			id++;
			try {
				plateaux_adversaire.add((Plateau)p.clone());
				if (!plateaux_adversaire.get(id).ajouterCoup(i, tmp)) {
					id--;
					plateaux_adversaire.remove(id);
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		i=0;
		while (i < plateaux_adversaire.size() && finded<0) {
			if (plateaux_adversaire.get(i).getWin() == tmp.getCouleur()) {
				finded = plateaux_adversaire.get(i).getLastX();
			}
			i++;
		}	
		if (finded != -1) {
			plateaux.clear();
			plateaux_adversaire.clear();
			return finded;
		}
		/*rts.add(p);
		rts.addAll(plateaux);
		rts.addAll(plateaux_adversaire);
		if (compareHashCode(rts)) {
			System.out.println("les copies sont différentes");
		} else {
			System.out.println("les copies sont similaires");
		}*/
		plateaux.clear();
		plateaux_adversaire.clear();
		Random rnd = new Random();
		
		return random((byte)0,p.getWidth());
	}
	
	private byte random(byte min, byte max) {
		Random rnd = new Random();
		return (byte)(Math.random()*(max-min)+min);
	}
}
