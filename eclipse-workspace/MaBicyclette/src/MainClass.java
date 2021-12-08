import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String livre;
		int saisieUtilisateur;
		Scanner sc = new Scanner(System.in);
		System.out.println("Repondez aux questions par 1 pour oui OU 0 pour non");
		System.out.println("Fait il beau demain?");
		saisieUtilisateur = sc.nextInt();
		if (saisieUtilisateur == 1) {
			System.out.println("Ma bicyclette fonctionne elle?");
			saisieUtilisateur = sc.nextInt();
			if (saisieUtilisateur == 1) {
				System.out.println("Demain je ferais une balade à bicyclette");
			} else {
				System.out.println("Je devrais ammener ma bicyclette chez le garagiste demain");
				System.out.println("les reperations seront elle immédiates?");
				saisieUtilisateur = sc.nextInt();
				if (saisieUtilisateur == 1) {
					System.out.println("je pourrais donc faire ma balade à bicyclette après le garagiste");
				} else {
					System.out.println("je laisserais tomber ma balade à bicyclette pour aller cueillir des jonc à l'étang");
				}
			}
		} else {
			System.out.println("Est-ce que j'ai le tome 1 de Game of Thrones?");
			saisieUtilisateur = sc.nextInt();
			if (saisieUtilisateur == 1) {
				livre = "Game of Thrones tome 1";
			} else {
				System.out.println("J'irais donc à la bibliothéque pour le chercher");
				System.out.println("Est-ce que Game of Thrones sera disponible à la bibliohéque?");
				saisieUtilisateur = sc.nextInt();
				if (saisieUtilisateur == 1) {
					System.out.println("J'emprunterais Game of Thrones tome 1");
					livre = "Game of Thrones tome 1";
				} else {
					System.out.println("J'emprunterais un roman policier");
					livre = "Oui-oui mène l'enquête";
				}
			}
			System.out.println("Je vais m'installer confortablement dans un fauteuil \n et me plongerais dans mon livre : " + livre);
		}
		sc.close();
	}

}
