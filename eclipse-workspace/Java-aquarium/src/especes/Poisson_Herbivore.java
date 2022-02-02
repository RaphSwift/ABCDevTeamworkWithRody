package especes;

import java.util.ArrayList;

import utils.Aquarium;
import utils.Command_EatAlgue;
import utils.Command_EatPoisson;
import utils.Command_ReproduirePoisson;
import utils.Utils;

public class Poisson_Herbivore extends Poisson{
	private final static String[] especesPossibles = {"Carpe","Bar","Sole"};
	
	public static final String[] getEspecesPossibles() {
		return especesPossibles;
	}
	
	
	
	public Poisson_Herbivore(byte _espece) {
		super();
		espece = _espece;
		typeReproduction = espece;
		int sexeTmp = Utils.random(0,2);
		if (sexeTmp == 1) {
			isMale = false;
		} else {
			isMale = true;
		}
	}
	
	public Poisson_Herbivore(String _nom, byte _espece) {
		super();
		espece = _espece;
		typeReproduction = espece;
		int sexeTmp = Utils.random(0,2);
		if (sexeTmp == 1) {
			isMale = false;
		} else {
			isMale = true;
		}
		nom = _nom;
	}
	
	public Poisson_Herbivore(short _pv,short _age, String _nom, byte _espece, byte _typeReproduction,boolean _isMale) {
		super(_pv,_age, _nom, _espece,_typeReproduction,_isMale);
	}
	
	public Poisson_Herbivore(Poisson_Herbivore from) {
		this(from.pv,from.age,from.nom,from.espece,from.typeReproduction,from.isMale);
	}
	
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return new Poisson_Herbivore(this);
	}
	
	@Override
	public void onTurn(Aquarium aquarium) {
		age++;
		if (age >= 20) {
			pv = 0;
		}
		if (age == 10 && typeReproduction == 1) {
			isMale = !isMale;
		}
		pv--;
		if (pv > 0) {
			
			if (pv <= 5) {
				ArrayList<Algue> algues = aquarium.getAlgues();
				for (int i = algues.size()-1; i >= 0; i--) {
					if (algues.get(i).getPV() <= 0) {
						algues.remove(i);
					}
				}
				if (algues.size() > 0) {
					
					Algue tmp = algues.get(Utils.random(0,algues.size()-1));
					aquarium.addCommand(new Command_EatAlgue(this,tmp));
					pv+=2;
				}
				algues.clear();
			} else {
				ArrayList<Poisson> poissons = aquarium.getPoissons();
				
				for (int i = poissons.size()-1; i >= 0; i--) {
					if (poissons.get(i).getPV() <= 0 || poissons.get(i).hashCode() ==this.hashCode()) {
						poissons.remove(i);
					}
				}
				if (poissons.size() > 0) {
					Poisson tmp = poissons.get(Utils.random(0, poissons.size()-1));
					aquarium.addCommand(new Command_ReproduirePoisson(this,tmp,aquarium));
				}
				poissons.clear();
			}
		}
	}

	@Override
	public void onEat(Poisson p) {
		pv-=4;
	}
	
	public boolean eat(Algue algue) {
		if (algue.getPV() > 0) {
			algue.onEat(this);
			pv+=3;
		}
		return true;
	}
	
	@Override
	public boolean preparerSeReproduire(Poisson p) {
		if (typeReproduction == 0 || typeReproduction == 1) {
			return (p instanceof Poisson_Herbivore)&&(p.getEspece() == espece)&&(p.estUnMale() != isMale);
		} else{
			if ((p instanceof Poisson_Herbivore)&&(p.getEspece() == espece)) {
				isMale = !p.estUnMale();
				return true;
			}
			return false;
		}
		
	}
	
	
	
	@Override
	public String toString() {
		return nom + ", " + especesPossibles[espece] + ", "+ age + " an"+ (age>1 ? "s":"");
	}



	@Override
	public boolean seReproduire(Poisson p, Aquarium aquarium) {
		if (preparerSeReproduire(p)) {
			aquarium.ajouterEtreVivant(new Poisson_Herbivore(Utils.generateName((byte)4),espece),true);
			return true;
		}
		return false;
	}
}
