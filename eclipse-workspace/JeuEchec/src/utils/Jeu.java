package utils;
import java.util.ArrayList;
import java.util.Scanner;

import pieces.Piece;
import pieces.Piece_Pion;
import pieces.Piece_Roi;

public class Jeu {
	private ArrayList<Plateau> parties;
	private Plateau plateauActuel;
	private Joueur participants[];
	private Joueur joueurActuel;
	
	public Jeu(String nomJ1, String nomJ2) {
		parties = new ArrayList<Plateau>();
		plateauActuel = new Plateau((byte)8,(byte)8);
		participants = new Joueur[2];
		participants[0]=new Joueur(nomJ1,false);
		participants[1]=new Joueur(nomJ2,true);
		joueurActuel = participants[0];
	}
	
	public void changerJoueur() {
		if (joueurActuel == participants[0]) {
			joueurActuel = participants[1];
		} else {
			joueurActuel = participants[0];
		}
	}
	
	public void gererJeu() {
		Scanner sc = new Scanner(System.in);
		String[] split = null;
		String[] split2 = null;
		String[] split3 = null;
		boolean validate;
		Mouvement tmpMove;
		Piece tmp = null;
		ArrayList<Mouvement> mvt = new ArrayList<Mouvement>();
		while (plateauActuel.verifierPlateau() != GAMESTATUS.BLACK_CHECKMATE && 
				plateauActuel.verifierPlateau() != GAMESTATUS.WHITE_CHECKMATE) {
			// TANT QU'IL N'Y A PAS D'ECHEC ET MAT
			validate = false;
			System.out.println(plateauActuel);
			do {
				System.out.println("c'est à " + joueurActuel.getNom() + " de jouer");
				split = sc.nextLine().toLowerCase().split(" ");
				if (split[0].equals("move") && split.length == 3) {
					split2 = split[1].split("-");
					split3 = split[2].split("-");
					tmpMove = new Mouvement(new Coordonees((byte)Integer.parseInt(split2[0]),(byte)Integer.parseInt(split2[1])),
							new Coordonees((byte)Integer.parseInt(split3[0]),(byte)Integer.parseInt(split3[1])));
					if (plateauActuel.deplacerPiece(tmpMove, joueurActuel.estNoir())){
						validate = true;
					} else {
						System.out.println(tmpMove + " impossible");
					}
					
				}
				else if (split[0].equals("list")) {
					if (split.length == 1) {
						tmp = plateauActuel.getRoi(joueurActuel.estNoir());
						if (tmp != null) {
							mvt = ((Piece_Roi)tmp).getPossiblesMouvements(plateauActuel);
							
							for (int i = 0; i < mvt.size(); i++) {
								System.out.println(mvt.get(i));
							}
							
						}
						mvt.clear();
						tmp = null;
					} else if (split.length == 2) {
						split2 = split[1].split("-");
						tmp = plateauActuel.getPieceFromCoord(new Coordonees((byte)Integer.parseInt(split2[0]),(byte)Integer.parseInt(split2[1]))); 
						if (tmp != null) {
							System.out.println(tmp + " peut jouer les coups suivants:");
							mvt = tmp.calculerMouvement(plateauActuel);
							if (tmp instanceof Piece_Pion) {
								mvt.addAll(((Piece_Pion)tmp).calculerMouvementManger(plateauActuel));
							
							}
							for (int i = 0; i < mvt.size(); i++) {
								System.out.println(mvt.get(i));
							}
						}
						mvt.clear();
						tmp = null;
					}
				}
			} while (!validate);
			if (plateauActuel.verifierPlateau() != GAMESTATUS.BLACK_CHECKMATE && 
					plateauActuel.verifierPlateau() != GAMESTATUS.WHITE_CHECKMATE)
				changerJoueur();
			
		}
		if (plateauActuel.verifierPlateau() == GAMESTATUS.BLACK_CHECKMATE) {
			if (!participants[0].estNoir()) {
				System.out.println(participants[0].getNom() + " a gagné");
			} else {
				System.out.println(participants[1].getNom() + " a gagne");
			}
		} else {
			if (participants[0].estNoir()) {
				System.out.println(participants[0].getNom() + " a gagné");
			} else {
				System.out.println(participants[1].getNom() + " a gagne");
			}
		}
		sc.close();

	}
	
	
	
}
