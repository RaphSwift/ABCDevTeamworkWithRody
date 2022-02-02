package especes;
import utils.Aquarium;
import utils.Command_ReproduireAlgue;

public class Algue extends EtreVivant {
	public Algue() {
		super();
	}
	
	public Algue(short _pv) {
		super(_pv);
	}
	
	public Algue(Algue from) {
		this(from.pv, from.age);
	}
	
	public Algue(short _pv, short _age) {
		super(_pv, _age);
	}
	
	public Object clone() throws CloneNotSupportedException{
		return new Algue(this);
	}
	
	@Override
	public void onTurn(Aquarium aquarium) {
		age++;
		if (age >= 20) {
			pv = 0;
		}
		
		if (pv>0)
			pv++;
		if (pv >= 10) {
			aquarium.addCommand(new Command_ReproduireAlgue(this,aquarium));
		}
	}
	
	@Override
	public void onEat(Poisson p) {
		pv-=2;
	}


	@Override
	public String toString() {
		return "algue " + age + " an"+(age>1? "s":"");
	}

	public boolean seDiviser(Aquarium aquarium) {
		if (pv >= 10) {
			if (pv%2 == 0) {
				pv /= 2;
				aquarium.ajouterEtreVivant(new Algue(pv),true);
			} else {
				pv /= 2;
				aquarium.ajouterEtreVivant(new Algue((short)(pv+1)),true);
			}
			return true;
		} 
		return false;
	}
}
