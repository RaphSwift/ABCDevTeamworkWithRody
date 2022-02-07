package especes;
import java.util.ArrayList;

import utils.Aquarium;
import utils.Command_EatPoisson;
import utils.Command_ReproduirePoisson;
import utils.Utils;

public class Poisson_Carnivore extends Poisson{

	private final static String[] especesPossibles = {"Thon","Mérou", "Poisson-clown"};
	
	public static final String[] getEspecesPossibles() {
		return especesPossibles;
	}
	
	public Poisson_Carnivore(byte _espece) {
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
	
	public Poisson_Carnivore(String _nom, byte _espece) {
		super();
		espece = _espece;
		typeReproduction = espece;
		nom = _nom;
		int sexeTmp = Utils.random(0,2);
		if (sexeTmp == 1) {
			isMale = false;
		} else {
			isMale = true;
		}
	}
	
	public Poisson_Carnivore(String _nom, byte _espece, byte _age) {
		super();
		espece = _espece;
		typeReproduction = espece;
		nom = _nom;
		int sexeTmp = Utils.random(0,2);
		if (sexeTmp == 1) {
			isMale = false;
		} else {
			isMale = true;
		}
		age = _age;
	}
	
	
	public Poisson_Carnivore(short _pv, short _age,  String _nom, byte _espece, byte _typeReproduction, boolean _isMale) {
		super(_pv,_age,_nom,_espece,_typeReproduction,_isMale);
	}
	
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
			ArrayList<Poisson> poissons = aquarium.getPoissons();
			for (int i = poissons.size()-1; i >= 0; i--) {
				if (poissons.get(i).getPV() <= 0  || poissons.get(i).hashCode() ==this.hashCode()) {
					poissons.remove(i);
				}
			}
			if (pv <= 5) {
				
				if (poissons.size() > 0) {
					Poisson tmp = poissons.get(Utils.random(0,poissons.size()-1));
					aquarium.addCommand(new Command_EatPoisson(this,tmp));
				}
				poissons.clear();
			} else {
				if (poissons.size() > 0) {
					Poisson tmp = poissons.get(Utils.random(0, poissons.size()-1));
					aquarium.addCommand(new Command_ReproduirePoisson(this,tmp,aquarium));
				}
				poissons.clear();
			}
		}
	}
	
	public Poisson_Carnivore(Poisson_Carnivore from) {
		this(from.pv,from.age,from.nom,from.espece,from.typeReproduction,from.isMale);
	}
	
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return new Poisson_Carnivore(this);
	}

	@Override
	public void onEat(Poisson p) {
		// TODO Auto-generated method stub
		pv-=4;
		if (pv <= 0) {
			
		}
	}

	
	
	@Override
	public String toString() {
		return nom + ", " + especesPossibles[espece] + ", "+ age + " an"+ (age>1 ? "s":"");
	}

	public boolean eat(Poisson p) {
		if (p instanceof Poisson_Carnivore) {
			if (p.getEspece() != espece) {
				p.onEat(this);
				return true;
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean preparerSeReproduire(Poisson p) {
		if (typeReproduction == 0 || typeReproduction == 1) {
			return (p instanceof Poisson_Carnivore)&&(p.getEspece() == espece)&&(p.estUnMale() != isMale);
		} else{
			if ((p instanceof Poisson_Carnivore)&&(p.getEspece() == espece)) {
				isMale = !p.estUnMale();
				return true;
			}
			return false;
		}
		
	}
	
	@Override
	public boolean seReproduire(Poisson p, Aquarium aquarium) {
		if (preparerSeReproduire(p)) {
			aquarium.ajouterEtreVivant(new Poisson_Carnivore(Utils.generateName((byte)3),espece),true);
			return true;
		}
		return false;
	}
	
	
}
