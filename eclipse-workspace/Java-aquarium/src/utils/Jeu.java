package utils;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import especes.Algue;
import especes.Poisson_Carnivore;
import especes.Poisson_Herbivore;

public class Jeu implements java.io.Serializable{
	private Aquarium aquarium;
	
	public Jeu() {
		aquarium = new Aquarium();
	}
	
	public Jeu(Aquarium _aquarium) {
		aquarium = _aquarium;
	}
	
	public boolean serialize() {
		boolean rt = true;
		
		try {
			FileOutputStream fileOut = new FileOutputStream("save.ser");
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
	
	public void gererJeu() {
		Scanner sc = new Scanner(System.in);
		boolean veutQuitter = false;
		boolean saisieCorrecte;
		String str = "";
		String[] split;
		int nbrTmp, nbrTmp2; // INSERTION
		int currentInserted;
		int i;
		while (!veutQuitter) {
			saisieCorrecte = false;
			aquarium.afficherContenu();
			do {
				System.out.println("Que voulez vous faire?");
				str = sc.nextLine().toLowerCase();
				split = str.split(" ");
				if (split[0].equals("save") && split.length == 1) {
					if(serialize()) {
						System.out.println("Sauvegarde réussie");
					} else {
						System.out.println("Echec de la sauvagarde");
					}
				} else if (split[0].equals("quit") && split.length == 1) {
					saisieCorrecte = true;
					veutQuitter = true;
				} else if (split[0].equals("turn") && split.length == 1) {
					saisieCorrecte = true;
				} else if (split[0].equals("list") && split.length == 1) {
					aquarium.afficherCommands();
					aquarium.afficherContenu();
				} else if (split[0].equals("resume") && split.length == 1) {
					aquarium.afficherContenu();
				} else if (split[0].equals("insert") && split.length > 1) {
					if ((split[2].equals("algues") || split[2].equals("algue")) && split.length == 5) {
						try {
							nbrTmp = Integer.parseInt(split[1]);
							nbrTmp2 = Integer.parseInt(split[3]);
							currentInserted = 0;
							for (i = 0; i < nbrTmp; i++) {
								if (aquarium.ajouterEtreVivant(new Algue(((byte)(10+nbrTmp2)),(byte)nbrTmp2),false)) {
									currentInserted++;
								}
							}
							if (currentInserted == nbrTmp2) {
								System.out.println("Ajout des algues avec succès");
							}
							i=0;
						} catch (Exception e) {
							System.out.println("Saisie invalide");
						}
					} else { // POISSON
						try {
							nbrTmp = Integer.parseInt(split[3]);
							nbrTmp2 = -1; 
							split[2] = split[2].toUpperCase().charAt(0) + split[2].substring(1,split[2].length());
							i=0;
							while(i < Poisson_Carnivore.getEspecesPossibles().length && nbrTmp2 == -1) {
								if (Poisson_Carnivore.getEspecesPossibles()[i].equals(split[2].replace(",", ""))){
									nbrTmp2 = i;
								}
								i++;
							}
							if (nbrTmp2<0) {
								i=0;
								
								while(i < Poisson_Herbivore.getEspecesPossibles().length && nbrTmp2 == -1 && split[2].length() >= 2) {
									
									if (Poisson_Herbivore.getEspecesPossibles()[i].equals(split[2].replace(",", ""))){
										nbrTmp2 = i;
									}
									i++;
								}
								if (nbrTmp2 < 0) {
									System.out.println("Le " + split[2].replace(",", "") + " n'est pas une race de poisson connue");
								}
								else {
									// POISSON HERBIVORE
									aquarium.ajouterEtreVivant(new Poisson_Herbivore(split[1].replace(",",""),(byte)nbrTmp2),false);
								}
							} else {
								// POISSON CARNIVORE
								aquarium.ajouterEtreVivant(new Poisson_Carnivore(split[1].replace(",",""),(byte)nbrTmp2),false);
							}
						} catch (Exception e){
							
						}
					}
					
				}
			} while (!saisieCorrecte);
			if (!veutQuitter) {
				aquarium.effectuerTour();
			}
		}		
		sc.close();
	}
}
