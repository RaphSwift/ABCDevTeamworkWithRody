package utils;

import especes.Algue;
import especes.Poisson;
import especes.Poisson_Carnivore;
import especes.Poisson_Herbivore;

public class Command_EatAlgue implements Command{
	private Poisson_Herbivore from;
	private Algue to;
	private boolean rt;
	
	public Command_EatAlgue(Poisson_Herbivore _from,
			Algue _to) {
		from = _from;
		to = _to;
	}
	
	public Command_EatAlgue(Poisson_Herbivore _from,
			Algue _to,
			boolean _rt) {
		from = _from;
		to = _to;
		rt = _rt;
	}
	
	public Command_EatAlgue(Command_EatAlgue _from) {
		this(_from.from,_from.to,_from.rt);
	}
	
	public Object clone() throws CloneNotSupportedException{
		return new Command_EatAlgue(this);
	}
	
	@Override()
	public boolean exec() {
		rt = from.eat(to);
		return rt;
	}
	
	@Override
	public String toString() {
		if (rt) {
			return from + " a croqué dans " + to ;
		} else {
			return from + " voulait manger " + to + " mais n'a pas réussi";
		}
	}
}
