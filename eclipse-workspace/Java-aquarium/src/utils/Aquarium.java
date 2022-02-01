package utils;
import java.util.ArrayList;
import java.util.Random;

import especes.Algue;
import especes.EtreVivant;
import especes.Poisson;
import especes.Poisson_Carnivore;
import especes.Poisson_Herbivore;

public class Aquarium{
	private ArrayList <EtreVivant> etresVivants;
	private CommandManager commandList;
	private short nbTours; 
	
	public Aquarium() {
		etresVivants = new ArrayList<EtreVivant>();
		commandList = new CommandManager();
		nbTours = 0;
		generateRandomAquarium(30);
	}
	
	public Aquarium(ArrayList<EtreVivant> _evs, CommandManager _command, short _nbTours) {
		etresVivants = new ArrayList<EtreVivant>();
		for (int i = 0; i < _evs.size(); i++) {
			try {
				etresVivants.add((EtreVivant)_evs.get(i).clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		commandList = new CommandManager(_command);
		nbTours= _nbTours;
	}
	
	public void generateRandomAquarium(int n) {
		short rnd;
		byte espece;
		for (int i = 0; i < n; i++) {
			rnd = Utils.random(0,3);
			if (rnd == 0) {
				etresVivants.add(new Algue());
			} else if (rnd == 1) {
				espece = (byte)Utils.random(0,Poisson_Carnivore.getEspecesPossibles().length-1);
				etresVivants.add(new Poisson_Carnivore(espece));
				
			} else if (rnd == 2) {
				espece = (byte)Utils.random(0,Poisson_Herbivore.getEspecesPossibles().length-1);
				etresVivants.add(new Poisson_Herbivore(espece));
			}
		}
	}
	
	public void generateAquariumTest(int n) {
		for (int i = 0; i < n; i++) {
			etresVivants.add(new Poisson_Carnivore((byte)0));
		}
	}
	
	public void jouer(int nTour) {
		nbTours++;
		for (int i=0; i < nTour;i++) {
			effectuerTour();
		}
		removeDead();
		System.out.println("---Resultat du tour " + nbTours + "-----\n" + commandList);
	}
	
	public boolean addCommand(Command c) {
		return commandList.execute(c);
	}
	
	public void ajouterEtreVivant(EtreVivant ev) {
		if (ev instanceof Poisson_Herbivore)
			etresVivants.add(new Poisson_Herbivore((Poisson_Herbivore)ev));
		else if (ev instanceof Poisson_Carnivore)
			etresVivants.add(new Poisson_Carnivore((Poisson_Carnivore)ev));
		else if (ev instanceof Algue)
			etresVivants.add(new Algue((Algue)ev));
	}
	
	public void effectuerTour() {
		for (int i = 0; i < etresVivants.size();i++) {
			if (etresVivants.get(i).getPV() > 0) {
				etresVivants.get(i).onTurn(this);
			} else {
				etresVivants.remove(i);
			}
		}
		removeDead();
		
	}
	
	public void removeDead() {
		for (int i = etresVivants.size()-1; i >=0;i--) {
			if (etresVivants.get(i).getPV() <= 0) {
				etresVivants.remove(i);
			}
		}
	}

	
	public void AfficherContenu() {
		int especes[] = new int[3];
		for (int i = 0; i < etresVivants.size(); i++) {
			if (etresVivants.get(i) instanceof Algue) {
				especes[0]++;
			} else if (etresVivants.get(i) instanceof Poisson_Carnivore) {
				especes[1]++;
			} else {
				especes[2]++;
			}
			
		}
		System.out.println("Il y'a " + especes[0] + " algues, " + especes[1] + " poissons carnivores et " +  especes[2] + " poissons herbivores.");
		
	}
	
	public ArrayList<Poisson> getPoissons() {
		ArrayList<Poisson> poissons = new ArrayList<Poisson>();
		for (int i  = 0; i < etresVivants.size(); i++) {
			if (etresVivants.get(i) instanceof Poisson) {
				poissons.add((Poisson)etresVivants.get(i));
			}
		}
		return poissons;
	}
	
	public ArrayList<Poisson_Herbivore> getPoissonsHerbivore(){
		ArrayList<Poisson_Herbivore> poissons = new ArrayList<Poisson_Herbivore>();
		for (int i  = 0; i < etresVivants.size(); i++) {
			if (etresVivants.get(i) instanceof Poisson_Herbivore) {
				poissons.add((Poisson_Herbivore)etresVivants.get(i));
			}
		}
		return poissons;
	}
	
	public ArrayList<Poisson_Carnivore> getPoissonsCarnivore(){
		ArrayList<Poisson_Carnivore> poissons = new ArrayList<Poisson_Carnivore>();
		for (int i  = 0; i < etresVivants.size(); i++) {
			if (etresVivants.get(i) instanceof Poisson_Carnivore) {
				poissons.add((Poisson_Carnivore)etresVivants.get(i));
			}
		}
		return poissons;
	}
	
	public ArrayList<Algue> getAlgues(){
		ArrayList<Algue> algues = new ArrayList<Algue>();
		for (int i  = 0; i < etresVivants.size(); i++) {
			if (etresVivants.get(i) instanceof Algue) {
				algues.add((Algue)etresVivants.get(i));
			}
		}
		return algues;
	}
	
	public boolean removeObject(Object o) {
		return etresVivants.remove(o);
	}
}
