import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.text.AttributeSet.ColorAttribute;

public class Jeu implements java.io.Serializable{
	private Joueur participants[];
	private Joueur currentPlayer;
	private final String nom;
	private Plateau plateauActuel;
	private ArrayList<Plateau> parties;
	private ArrayList<Joueur> winners;
	private int nbParties;
	
	public Jeu(String _nom, Joueur _joueurRouge, Joueur _joueurJaune) {
		nom = _nom;
		parties = new ArrayList<Plateau>();
		winners = new ArrayList<Joueur>();
		nbParties = 0;
		participants = new Joueur[2];
		participants[0] = _joueurRouge;
		participants[1] = _joueurJaune;
		currentPlayer = participants[0];
		plateauActuel = new Plateau((byte)7,(byte)6);
		/*try {
			Plateau p2;
			p2 = (Plateau)plateauActuel.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		

	}
	
	
	
	public boolean serialize() {
		boolean rt = true;
		
		try {
			FileOutputStream fileOut = new FileOutputStream("fichier.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (Exception e) {
			//e.printStackTrace();
			rt= false;
		}
		return rt;
	}
	

	@Override
	public String toString() {
		String str = "Jeu [participants=" + Arrays.toString(participants) + ", nom="
				+ nom;
		if (parties.size() > 0) {
			str += ", ";
			for (int i = 0 ; i < parties.size();i++) {
				str += "parties["+i+"]=[";
				str += parties.get(i).toString();
				str += "]";
				if (i+1 < parties.size())
					str+=", ";
			}
		}
		if (winners.size() > 0) {
			str += ", ";
			for (int i = 0 ; i < winners.size();i++) {
				str += "winner["+i+"]=[";
				str += winners.get(i).toString();
				str += "]";
				if (i+1 < winners.size())
					str+=", ";
			}
		}
		str += "]";
		return str;
	}
	
	




	public void changerJoueur() {
		if  (currentPlayer == participants[0])
			currentPlayer = participants[1];
		else
			currentPlayer = participants[0];
	}
	
	public Joueur gererPlateau() {
		Joueur winner = null;
		char win = ' ';
		
		Scanner sc = new Scanner(System.in);
		int column;
		boolean havePlayed = false;
		String replay;
		System.out.println("Vous jouez au " + nom );
		do {
			System.out.println(participants[0].getNom() +"  aura pour couleur " + participants[0].getCouleur());
			System.out.println(participants[1].getNom() +"  aura pour couleur " + participants[1].getCouleur());

			if (participants[0].getCouleur() == Winner.RED_WINNER.getLetter())
				currentPlayer = participants[0];
			else
				currentPlayer = participants[1];
			
			if (winners.size() > 0)
				plateauActuel.reset();
			winner = null;
			while (winner == null) {
				plateauActuel.afficherGrille();
				if (!(currentPlayer instanceof Joueur_Ordi)){
					do {
						do {
							System.out.println("C'est à " + currentPlayer.getNom() + " de jouer. Quel colonne(0-6)");
							column = sc.nextInt();
						} while (column <  0 || column >= 7);
						if (plateauActuel.ajouterCoup((byte)column,currentPlayer)) {
							havePlayed = true;
						}
					} while (!havePlayed);
				} else {
					byte c;
					c = ((Joueur_Ordi)currentPlayer).jouer(plateauActuel);
					plateauActuel.ajouterCoup(c, currentPlayer);
					System.out.println(currentPlayer.getNom() + " joue en colonne " + c);
					
				}
				win = plateauActuel.getWin();
				if (win != ' ') {
					if (win == participants[0].getCouleur()) {
						winner = participants[0];
						participants[0].donnerUnPoint();
						
					} else {
						winner = participants[1];
						participants[1].donnerUnPoint();
					}
					participants[0].changerCouleur();
					
					participants[1].changerCouleur();
					plateauActuel.afficherGrille();
					System.out.println(winner.getNom() + " a gagné!");

					parties.add(new Plateau(plateauActuel));
					winners.add(new Joueur(winner));
					nbParties++;
					
				} else {
					changerJoueur();					
				}
				havePlayed = false;
			}
			do {
				System.out.println("Voulez vous rejouer? (oui/non)");
				replay = sc.next().toUpperCase();
				if (!replay.equals("OUI") && !replay.equals("NON")) {
					System.out.println("Veuillez saisir oui ou non");
				}
					
			} while (!replay.equals("OUI") && !replay.equals("NON"));
		} while (replay.equals("OUI"));
		sc.close();
		return winner;
	}
}
