package especes;

import java.util.ArrayList;

import utils.Aquarium;
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
	}
	
	public Poisson_Herbivore(short _pv,short _age, byte _espece, byte _typeReproduction,boolean _isMale) {
		super(_pv,_age, _espece,_typeReproduction,_isMale);
	}
	
	public Poisson_Herbivore(Poisson_Herbivore from) {
		this(from.pv,from.age,from.espece,from.typeReproduction,from.isMale);
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
					eat(algues.get(Utils.random(0,algues.size()-1)));
					pv+=2;
				}
				algues.clear();
			} else {
				ArrayList<Poisson> poissons = aquarium.getPoissons();
				for (int i = poissons.size()-1; i >= 0; i--) {
					if (poissons.get(i).getPV() <= 0) {
						poissons.remove(i);
					}
				}
				Poisson tmp = poissons.get(Utils.random(0, poissons.size()-1));
				if (seReproduire(tmp)) {
					
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
	
	public boolean seReproduire(Poisson p) {
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
}
