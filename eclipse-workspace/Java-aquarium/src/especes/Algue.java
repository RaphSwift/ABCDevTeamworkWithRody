package especes;
import utils.Aquarium;

public class Algue extends EtreVivant {
	public Algue() {
		super();
	}
	
	public Algue(short _pv, short _age) {
		super(_pv, _age);
	}
	
	@Override
	public void onTurn(Aquarium aquarium) {
		age++;
		if (age >= 20) {
			pv = 0;
		}
		if (pv>0)
			pv++;
	}
	
	@Override
	public void onEat(Poisson p) {
		pv-=2;
	}
}
