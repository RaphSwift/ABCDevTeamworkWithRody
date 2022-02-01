
public class Poisson extends EtreVivant{
	public Poisson() {
		super();
	}
	
	public Poisson(short pv) {
		super(pv);
	}
	
	public abstract void onTurn(Aquarium aquarium);
	
	public abstract void onEat(Poisson p);
	
	public abstract void eat(EtreVivant e);
}
