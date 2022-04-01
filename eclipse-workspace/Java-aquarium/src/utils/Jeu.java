package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import Exceptions.IncorrectNameException;
import Exceptions.IncorrectNumberException;
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


	private int gererTurn(String[] split) throws NumberFormatException, IncorrectNumberException{
		int nbTmp;
		if (split.length == 2) {
			nbTmp = Integer.valueOf(split[1]);
			if (nbTmp <= 0) {
				throw new IncorrectNumberException("Problème de valeur " + nbTmp + " doit etre > 0");
			}
			return nbTmp;
		} else {
			return 1;
		}
	}
	
	private boolean inserer(String[] split) throws NumberFormatException, IncorrectNumberException, IncorrectNameException {
		int nb1, nb2;
		if (split.length <= 5) {
			if (split.length == 5) {
				if (!split[4].equals("an") && !split[4].equals("ans")) {
					return false;
				}
			}
			if (split[2].equals("algues") || split[2].equals("algue")) {
				nb1 = Integer.valueOf(split[1]);
				nb2 = Integer.valueOf(split[3]);
				if (nb1 <= 0 ) {
					throw new IncorrectNumberException("Erreur sur le parametre 1 : " + split[1] + " , il doit etre > 0");
				}
				if (nb2 < 0 || nb2 >= 20) {
					throw new IncorrectNumberException("Erreur sur le parametres 3: " + split[3] + " il doit etre compris entre 0 et 19");
				}
				int currentInserted= 0;
				for (int i = 0; i < nb1; i++) {
					if (aquarium.ajouterEtreVivant(new Algue(((byte)(10+nb2)),(byte)nb2),false)) {
						currentInserted++;
					}
				}
				return currentInserted == nb1;
			} else {
				nb2 = Integer.valueOf(split[3]);
				if (nb2 < 0 || nb2 >= 20) {
					throw new IncorrectNumberException("Erreur sur le parametres 3: " + split[3] + " il doit etre compris entre 0 et 19");
				}
				String nomPoisson = split[1].toUpperCase().charAt(0) + split[1].substring(1,split[1].length());
				String espece = split[2].replace(",", "");
				int especeInt;
				especeInt = verifierEspeceHerbivore(espece);
				if (especeInt < 0) {
					especeInt = verifierEspeceCarnivore(espece);
					if (especeInt >= 0) {
						return aquarium.ajouterEtreVivant(new Poisson_Carnivore(nomPoisson.replace(",",""),(byte)especeInt,(byte)nb2),false);
					} else {
						throw new IncorrectNameException("Erreur du parametre 2: " + espece + " cette race n'est pas connue");
					}
				} else {
					return aquarium.ajouterEtreVivant(new Poisson_Herbivore(nomPoisson.replace(",",""),(byte)especeInt,(byte)nb2),false);
				}		
			}
		}
		return false;
	}
	
	private boolean generer(String[] split) throws NumberFormatException, IncorrectNumberException{
		int nb;
		if (split.length == 2) {
			nb = Integer.valueOf(split[1]);
			if (nb <= 0) {
				throw new IncorrectNumberException("Erreur du parametre 1: " + split[1] + " doit etre > 0");
			}
			aquarium.generateRandomAquarium(nb);
		}
		return false;
	}
	
	private int verifierEspeceHerbivore(String str) {
		int i=0;
		int rt = -1;
		while(i < Poisson_Herbivore.getEspecesPossibles().length && rt == -1) {

			if (Poisson_Herbivore.getEspecesPossibles()[i].toLowerCase().equals(str)){
				rt = i;
			}
			i++;
		}
		return rt;
	}
	
	private int verifierEspeceCarnivore(String str) {
		int i=0;
		int rt = -1;
		String tab[] = Poisson_Carnivore.getEspecesPossibles();
		while(i < tab.length && rt == -1) {

			if (tab[i].toLowerCase().equals(str)){
				rt = i;
			}
			i++;
		}
		return rt;
	}
	
//	public void gererJeu() {
//		Scanner sc = new Scanner(System.in);
//		boolean veutQuitter = false;
//		boolean saisieCorrecte;
//		String str = "";
//		String[] split;
//		int nbrTmp, nbrTmp2; // INSERTION
//		int currentInserted;
//		int i;
//		boolean uniteCorrecte;
//		String poissonNom;
//		while (!veutQuitter) {
//			uniteCorrecte = true;
//			saisieCorrecte = false;
//			aquarium.afficherContenu();
//			nbrTmp = 0;
//			do {
//				System.out.println("Que voulez vous faire?");
//				str = sc.nextLine().trim().toLowerCase();
//				split = str.split(" ");
//				if (split[0].equals("quit") && split.length == 1) {
//					saisieCorrecte = true;
//					veutQuitter = true;
//				} else if (split[0].equals("turn") && split.length <= 2) {
//					saisieCorrecte = true;
//					if (split.length == 2) {
//						try {
//							nbrTmp = Integer.parseInt(split[1]);
//							if (nbrTmp <= 0) {
//								System.out.println("Erreur le nombre de tour doit etre minimum à 1");
//								saisieCorrecte = false;
//							}
//						} catch (Exception e) {
//							saisieCorrecte = false;
//							System.out.println("Erreur saisie");
//						}
//					} else {
//						nbrTmp = 1;
//					}
//				} else if (split[0].equals("list") && split.length == 1) {
//					aquarium.afficherCommands();
//					aquarium.afficherContenu();
//				} else if (split[0].equals("resume") && split.length == 1) {
//					aquarium.afficherContenu();
//				} else if (split[0].equals("insert") && split.length >=4) {
//					if ((split[2].equals("algues") || split[2].equals("algue"))) {
//						if(split.length<=5) {
//							if (split.length == 5) {
//								if (split[4].equals("an") || split[4].equals("ans")) {
//									uniteCorrecte = true;
//								} else {
//									uniteCorrecte = false;
//								}
//							}
//							if (uniteCorrecte) {
//								try {
//									nbrTmp = Integer.parseInt(split[1]);
//									nbrTmp2 = Integer.parseInt(split[3]);
//									currentInserted = 0;
//									if (nbrTmp > 0 && nbrTmp2 >= 0) {
//										for (i = 0; i < nbrTmp; i++) {
//											if (aquarium.ajouterEtreVivant(new Algue(((byte)(10+nbrTmp2)),(byte)nbrTmp2),false)) {
//												currentInserted++;
//											}
//										}
//										if (currentInserted == nbrTmp2) {
//											System.out.println("Ajout des algues avec succès");
//										}
//										i=0;
//									} else {
//										System.out.println("Erreur sur l'un des parametres nombre ou age");
//									}
//								} catch (Exception e) {
//									System.out.println("Saisie invalide");
//								}
//							} else {
//								System.out.println("Probleme sur l'argument 5 de la commande : " + split[4]);
//							}
//						} else {
//							System.out.println("Trop de parametres");
//						}
//					} else { // POISSON
//						if(split.length<=5) {
//							if (split.length == 5) {
//								if (split[4].equals("an") || split[4].equals("ans")) {
//									uniteCorrecte = true;
//								} else {
//									uniteCorrecte = false;
//								}
//							}
//							if (uniteCorrecte) {
//								try {
//									nbrTmp = Integer.parseInt(split[3]);
//									nbrTmp2 = -1;
//									poissonNom = split[1].toUpperCase().charAt(0) + split[1].substring(1,split[1].length());
//									split[2] = split[2].toUpperCase().charAt(0) + split[2].substring(1,split[2].length());
//									i=0;
//									while(i < Poisson_Carnivore.getEspecesPossibles().length && nbrTmp2 == -1) {
//										if (Poisson_Carnivore.getEspecesPossibles()[i].equals(split[2].replace(",", ""))){
//											nbrTmp2 = i;
//										}
//										i++;
//									}
//									if (nbrTmp2<0) {
//										i=0;
//
//										while(i < Poisson_Herbivore.getEspecesPossibles().length && nbrTmp2 == -1 && split[2].length() >= 2) {
//
//											if (Poisson_Herbivore.getEspecesPossibles()[i].equals(split[2].replace(",", ""))){
//												nbrTmp2 = i;
//											}
//											i++;
//										}
//										if (nbrTmp2 < 0) {
//											System.out.println("Le " + split[2].replace(",", "") + " n'est pas une race de poisson connue");
//										}
//										else {
//											// POISSON HERBIVORE
//											if (nbrTmp >= 0) {
//												if (aquarium.ajouterEtreVivant(new Poisson_Herbivore(poissonNom.replace(",",""),(byte)nbrTmp2,(byte)nbrTmp),false)) {
//													System.out.println("Ajout du poisson effectué");
//												}
//											} else {
//												System.out.println("Age incorrect");
//											}
//
//										}
//									} else {
//										// POISSON CARNIVORE
//										if (nbrTmp >= 0) {
//											if (aquarium.ajouterEtreVivant(new Poisson_Carnivore(poissonNom.replace(",",""),(byte)nbrTmp2,(byte)nbrTmp),false)){
//												System.out.println("Ajout du poisson effectué");
//											}
//										} else {
//											System.out.println("Age incorrect");
//										}
//									}
//								} catch (Exception e){
//									System.out.println("Erreur de saisie");
//								}
//							} else {
//								System.out.println("Erreur param 5" + split[4]);
//							}
//						}
//					}
//				} else if (split[0].equals("generate") && split.length == 2){
//					try {
//						nbrTmp = Integer.parseInt(split[1]);
//						if (nbrTmp > 0) {
//							aquarium.generateRandomAquarium(nbrTmp);
//						} else {
//							System.out.println("Parametre " + nbrTmp + " doit etre plus grand que 0");
//						}
//					} catch (Exception e) {
//						System.out.println("Erreur de conversion du param " + split[1]);
//					}
//				}else {
//					System.out.println("Saisie incorrecte!");
//				}
//			} while (!saisieCorrecte);
//			if (!veutQuitter) {
//				// -- ON SAUVEGARDE
//				for (i = 0; i < nbrTmp; i++) {
//					aquarium.effectuerTour();
//				}
//				if(serialize()) {
//					System.out.println("\nSauvegarde réussie");
//				} else {
//					System.out.println("\nEchec de la sauvagarde");
//				}
//			}
//		}		
//		sc.close();
//	}
	
	public void gererJeu() {
		Scanner sc = new Scanner(System.in);
		boolean veutQuitter = false;
		boolean saisieCorrecte;
		String str = "";
		String[] split;
		int nbTours;
		while (!veutQuitter) {
			nbTours = 0;
			saisieCorrecte = false;
			aquarium.afficherContenu();
			do {
				System.out.println("Que voulez vous faire?");
				str = sc.nextLine().trim().toLowerCase();
				split = str.split(" ");
				if (split[0].equals("quit") && split.length == 1) {
					saisieCorrecte = true;
					veutQuitter = true;
				} else if (split[0].equals("turn") && split.length <= 2) {
					try {
						nbTours = gererTurn(split);
						if (nbTours>0) {
							saisieCorrecte = true;
						}
					} catch (Exception e){
						System.out.println(e.getMessage());
					}
				} else if (split[0].equals("list") && split.length == 1) {
					aquarium.afficherCommands();
					aquarium.afficherContenu();
				} else if (split[0].equals("resume") && split.length == 1) {
					aquarium.afficherContenu();
				} else if (split[0].equals("insert")) {
					try {
						inserer(split);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else if (split[0].equals("generate")){
					try {
						generer(split);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}else {
					System.out.println("Saisie incorrecte!");
				}
			} while (!saisieCorrecte);
			if (!veutQuitter) {
				// -- ON SAUVEGARDE
				for (int i = 0; i < nbTours; i++) {
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
