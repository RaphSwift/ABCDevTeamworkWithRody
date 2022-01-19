import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
	private Plateau monPlateau;
	private ArrayList<Plateau> parties;
	private Joueur[] participants;
	public Joueur joueurActuel;
	
	
	public Jeu(String _nomJ1, String _nomJ2) {
		parties = new ArrayList<Plateau>();
		monPlateau = null;
		participants = new Joueur[2];
		participants[0] = new Joueur(_nomJ1,true);
		participants[1] = new Joueur(_nomJ1,false);
		joueurActuel = participants[0];
		monPlateau = new Plateau((byte)8,(byte)9);
		monPlateau.reset();
	}
	

	
	public void changerJoueur() {
		if (joueurActuel == participants[0]) {
			joueurActuel = participants[1];
		} else {
			joueurActuel = participants[0];
		}
		
	}
	
	public void gererJeu() {
		String saisieTmp;
		boolean selectPiece = false;
		boolean movePiece = false;
		boolean saisieCorrecte = false;
		
		Piece tmp = null;
		Scanner sc = new Scanner(System.in);
		Coordonees from;
		Coordonees to;
		while(monPlateau.verifierPlateau() != GAMESTATUS.BLACK_CHECKMATE &&
			monPlateau.verifierPlateau() != GAMESTATUS.WHITE_CHECKMATE){
			selectPiece = false;
			movePiece = false;
			System.out.println(monPlateau.afficherConsole());
			System.out.println("C'est a " + joueurActuel.getNom() + " de jouer(");
			if (joueurActuel.isNoir()) {
				System.out.print("noir)\n");
			} else {
				System.out.println("blanc)\n");
			}
			do {
				System.out.println("quel pièce voulez vous déplacer?(Coordonnées sous la forme xy 68 pour 6;8 )");
				saisieTmp = sc.next();
				if (saisieTmp.length()==2) {
					tmp = monPlateau.getPiece(new Coordonees((byte)(Integer.parseInt(""+saisieTmp.charAt(0))),
							(byte)(Integer.parseInt(""+saisieTmp.charAt(1)))));
					if (tmp != null) {
						from = tmp.position;
						do {
							
							System.out.println("A quel coordonées voulez vous déplacer ");
							if (tmp instanceof Piece_Pion) {
								System.out.print("le pion");
							} else if (tmp instanceof Piece_Tour) {
								System.out.print("la tour");
							} else if (tmp instanceof Piece_Reine) {
								System.out.print("la reine");
							} else if (tmp instanceof Piece_Roi) {
								System.out.print("le roi");
							} else if (tmp instanceof Piece_Cavalier) {
								System.out.print("le cavalier");
							} else if (tmp instanceof Piece_Fou) {
								System.out.print("le fou");
							}
							System.out.print("[" + from.getX() + "-"+from.getY() +"]");
							saisieTmp = sc.next();
						} while(!movePiece);
					}
				}
			} while (!selectPiece);
			
			
			changerJoueur();
		}
		sc.close();
	}
}
