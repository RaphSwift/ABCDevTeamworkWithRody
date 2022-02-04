package utils;

import java.io.File;
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
		File sauvegarde = new File("save.txt");
		if (sauvegarde.exists()) {
			try {
				sauvegarde.delete();
			} catch (Exception e) {}
		} 
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
		int debut;
		String str = "";
		String[] split;
		int nbrTmp, nbrTmp2; // INSERTION
		int currentInserted;
		int i;
		boolean uniteCorrecte;
		String poissonNom;
		while (!veutQuitter) {
			uniteCorrecte = true;
			saisieCorrecte = false;
			aquarium.afficherContenu();
			nbrTmp = 0;
			do {
				System.out.println("Que voulez vous faire?");
				str = sc.nextLine().trim().toLowerCase();
				split = str.split(" ");
				if (split[0].equals("quit") && split.length == 1) {
					saisieCorrecte = true;
					veutQuitter = true;
				} else if (split[0].equals("turn") && split.length <= 2) {
					saisieCorrecte = true;
					if (split.length == 2) {
						try {
							nbrTmp = Integer.parseInt(split[1]);
							if (nbrTmp <= 0) {
								System.out.println("Erreur le nombre de tour doit etre minimum à 1");
								saisieCorrecte = false;
							}
						} catch (Exception e) {
							saisieCorrecte = false;
							System.out.println("Erreur saisie");
						}
					} else {
						nbrTmp = 1;
					}
				} else if (split[0].equals("list") && split.length == 1) {
					aquarium.afficherCommands();
					aquarium.afficherContenu();
				} else if (split[0].equals("resume") && split.length == 1) {
					aquarium.afficherContenu();
				} else if (split[0].equals("insert") && split.length >=4) {
					if ((split[2].equals("algues") || split[2].equals("algue"))) {
						if(split.length<=5) {
							if (split.length == 5) {
								if (split[4].equals("an") || split[4].equals("ans")) {
									uniteCorrecte = true;
								} else {
									uniteCorrecte = false;
								}
							}
							if (uniteCorrecte) {
								try {
									nbrTmp = Integer.parseInt(split[1]);
									nbrTmp2 = Integer.parseInt(split[3]);
									currentInserted = 0;
									if (nbrTmp > 0 && nbrTmp2 >= 0) {
										for (i = 0; i < nbrTmp; i++) {
											if (aquarium.ajouterEtreVivant(new Algue(((byte)(10+nbrTmp2)),(byte)nbrTmp2),false)) {
												currentInserted++;
											}
										}
										if (currentInserted == nbrTmp2) {
											System.out.println("Ajout des algues avec succès");
										}
										i=0;
									} else {
										System.out.println("Erreur sur l'un des parametres nombre ou age");
									}
								} catch (Exception e) {
									System.out.println("Saisie invalide");
								}
							} else {
								System.out.println("Probleme sur l'argument 5 de la commande : " + split[4]);
							}
						} else {
							System.out.println("Trop de parametres");
						}
					} else { // POISSON
						if(split.length<=5) {
							if (split.length == 5) {
								if (split[4].equals("an") || split[4].equals("ans")) {
									uniteCorrecte = true;
								} else {
									uniteCorrecte = false;
								}
							}
							if (uniteCorrecte) {
								try {
									nbrTmp = Integer.parseInt(split[3]);
									nbrTmp2 = -1;
									poissonNom = split[1].toUpperCase().charAt(0) + split[1].substring(1,split[1].length());
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
											if (nbrTmp >= 0) {
												if (aquarium.ajouterEtreVivant(new Poisson_Herbivore(poissonNom.replace(",",""),(byte)nbrTmp2,(byte)nbrTmp),false)) {
													System.out.println("Ajout du poisson effectué");
												}
											} else {
												System.out.println("Age incorrect");
											}

										}
									} else {
										// POISSON CARNIVORE
										if (nbrTmp >= 0) {
											if (aquarium.ajouterEtreVivant(new Poisson_Carnivore(poissonNom.replace(",",""),(byte)nbrTmp2,(byte)nbrTmp),false)){
												System.out.println("Ajout du poisson effectué");
											}
										} else {
											System.out.println("Age incorrect");
										}
									}
								} catch (Exception e){
									System.out.println("Erreur de saisie");
								}
							} else {
								System.out.println("Erreur param 5" + split[4]);
							}
						}
					}
				} else {
					System.out.println("Saisie incorrecte!");
				}
			} while (!saisieCorrecte);
			if (!veutQuitter) {
				// -- ON SAUVEGARDE
				for (i = 0; i < nbrTmp; i++) {
					aquarium.effectuerTour();
				}
				if(serialize()) {
					System.out.println("\nSauvegarde réussie");
				} else {
					System.out.println("\nEchec de la sauvagarde");
				}
			}
		}		
		sc.close();
	}
}
