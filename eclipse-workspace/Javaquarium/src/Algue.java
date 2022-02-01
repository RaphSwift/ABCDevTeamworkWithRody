
public class Algue extends EtreVivant {
	public Algue() {
		super();
	}
	
	public Algue(short pv) {
		super(pv);
	}
	
	@Override
	public void onTurn(Aquarium aquarium) {
		if (pv>0)
			pv++;
	}
	
	@Override
	public void onEat(Poisson p) {
		pv-=2;
	}
}
