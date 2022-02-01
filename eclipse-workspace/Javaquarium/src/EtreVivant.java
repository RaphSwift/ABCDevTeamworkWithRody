
public class EtreVivant {
	short pv;
	
	public EtreVivant() {
		pv = 10;
	}
	
	public EtreVivant(short _pv) {
		pv = _pv;
	}
	
	public short getPV() {
		return pv;
	}
	
	
	public abstract void onTurn(Aquarium aquarium);
	
	public abstract void onEat(Poisson p);
	
}
