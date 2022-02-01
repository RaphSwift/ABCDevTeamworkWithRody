package utils;

import especes.Algue;
import especes.Poisson;
import especes.Poisson_Carnivore;
import especes.Poisson_Herbivore;

public class Command_EatPoisson implements Command {
	private Poisson_Carnivore from;
	private Poisson to;
	private boolean rt;
	
	public Command_EatPoisson(Poisson_Carnivore _from,
			Poisson _to) {
		from = _from;
		to = _to;
	}
	
	public Command_EatPoisson(Poisson_Carnivore _from,
			Poisson _to,
			boolean _rt) {
		from = _from;
		to = _to;
		rt = _rt;
	}
	
	public Command_EatPoisson(Command_EatPoisson _from) {
		this(_from.from,_from.to,_from.rt);
	}
	
	public Object clone() throws CloneNotSupportedException{
		return new Command_EatPoisson(this);
	}
	
	@Override
	public boolean exec() {
		rt = from.eat(to);
		try {
			from = (Poisson_Carnivore)from.clone();
			to = (Poisson)to.clone();
		} catch (Exception e) {
			
		}
		return rt;
	}
	
	@Override
	public String toString() {
		if (rt) {
			return from + " a croqué " + to;
		} else {
			return from + " a voulu attaquer " + to + " mais c'est rendu compte qu'il avait l'air mauvais";
		}
	}
}
