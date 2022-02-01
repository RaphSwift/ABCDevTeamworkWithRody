package especes;
import utils.Aquarium;

public abstract class Poisson extends EtreVivant implements Cloneable{
	byte espece;
	byte typeReproduction; // 0 mono sexué 1 hermaphrodite avec age 2 hermaphrodite opportuniste
	boolean isMale;
	
	public Poisson() {
		super();
	}
	
	public Poisson(Poisson from) {
		this(from.pv,from.age,from.espece,from.typeReproduction,from.isMale);
	}
	

	public Poisson(short _pv, short _age,byte _espece,byte _typeReproduction,boolean _isMale) {
		super(_pv,_age);
		espece = _espece;
		typeReproduction = _typeReproduction;
		isMale = _isMale;
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
	public abstract void onTurn(Aquarium aquarium);

	
}
