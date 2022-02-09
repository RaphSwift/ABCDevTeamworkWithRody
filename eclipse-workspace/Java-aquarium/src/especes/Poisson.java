package especes;
import utils.Aquarium;

public abstract class Poisson extends EtreVivant{
	protected byte espece;
	protected byte typeReproduction; // 0 mono sexué 1 hermaphrodite avec age 2 hermaphrodite opportuniste
	protected boolean isMale;
	protected String nom;
	
	public Poisson() {
		super();
	}
	
	public Poisson(Poisson from) {
		this(from.pv,from.age,from.nom,from.espece,from.typeReproduction,from.isMale);
	}
	
	
	

	public Poisson(short _pv, short _age, String _nom, byte _espece,byte _typeReproduction,boolean _isMale) {
		super(_pv,_age);
		nom = _nom;
		espece = _espece;
		typeReproduction = _typeReproduction;
		isMale = _isMale;
	}
	
	public String getNom() {
		return nom;
	}
	
	public boolean estUnMale() {
		return isMale;
	}
	
	public byte getReproductionType() {
		return typeReproduction;
	}
	
	public byte getEspece() {
		return espece;
	}
	
	@Override
	public abstract String toString();
	public abstract Object clone() throws CloneNotSupportedException;
	public abstract void onTurn(Aquarium aquarium);
	public abstract boolean preparerSeReproduire(Poisson p);
	public abstract boolean seReproduire(Poisson p, Aquarium aquarium);
	
}
