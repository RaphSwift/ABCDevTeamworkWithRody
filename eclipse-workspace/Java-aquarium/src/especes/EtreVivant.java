package especes;
import utils.Aquarium;

public abstract class EtreVivant {
	short pv;
	short age;
	public EtreVivant() {
		pv = 10;
		age = 0;
	}
	
	public EtreVivant(short _pv, short _age) {
		pv = _pv;
		age = _age;
	}
	
	public short getPV() {
		return pv;
	}
	
	
	public abstract void onTurn(Aquarium aquarium);
	
	public abstract void onEat(Poisson p);
	
}
