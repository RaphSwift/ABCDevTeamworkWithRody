package especes;
import java.util.ArrayList;

import utils.Aquarium;
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
				ArrayList<Poisson> poisson = aquarium.getPoissons();
				for (int i = poisson.size()-1; i >= 0; i--) {
					if (poisson.get(i).getPV() <= 0) {
						poisson.remove(i);
					}
				}
				if (eat(poisson.get(Utils.random(0,poisson.size()-1)))) {
					pv+=5;
				}
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

	public boolean seReproduire(Poisson p) {
		
		return (p instanceof Poisson_Carnivore)&&(p.getEspece() == espece);
	}
	
	
}
