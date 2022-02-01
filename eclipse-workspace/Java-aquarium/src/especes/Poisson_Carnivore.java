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
	
	
	public Poisson_Carnivore(short _pv, short _age,  byte _espece, byte _typeReproduction, boolean _isMale) {
		super(_pv,_age, _espece,_typeReproduction,_isMale);
	}
	
	public void onTurn(Aquarium aquarium) {
		age++;
		if (age >= 20) {
			pv = 0;
		}
		pv--;
		if (pv > 0) {
			
			if (pv <= 5) {
				ArrayList<Poisson> poissons = aquarium.getPoissons();
				for (int i = poissons.size()-1; i >= 0; i--) {
					if (poissons.get(i).getPV() <= 0  || poissons.get(i).hashCode() ==this.hashCode()) {
						poissons.remove(i);
					}
				}
				if (poissons.size() > 0) {
					Poisson tmp = poissons.get(Utils.random(0,poissons.size()-1));
					aquarium.addCommand(new Command_EatPoisson(this,tmp));
				}
			} else {
				ArrayList<Poisson> poissons = aquarium.getPoissons();
				for (int i = poissons.size()-1; i >= 0; i--) {
					if (poissons.get(i).getPV() <= 0 || poissons.get(i).hashCode() ==this.hashCode()) {
						poissons.remove(i);
					}
				}
				Poisson tmp = poissons.get(Utils.random(0, poissons.size()-1));
				aquarium.addCommand(new Command_ReproduirePoisson(this,tmp,aquarium));
				poissons.clear();
			}
		}
	}
	
	public Poisson_Carnivore(Poisson_Carnivore from) {
		this(from.pv,from.age,from.espece,from.typeReproduction,from.isMale);
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
		return "Poisson_Carnivore [espece=" + espece + ", typeReproduction=" + typeReproduction + ", isMale=" + isMale
				+ ", pv=" + pv + ", age=" + age + "]";
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
			aquarium.ajouterEtreVivant(new Poisson_Carnivore(espece));
			return true;
		}
		return false;
	}
	
	
}
