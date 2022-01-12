
public class Jeton implements java.io.Serializable,Cloneable{
	public final char couleur;
	public final byte numeroCoup;
	
	public Object clone() {
		return new Jeton(this);
	}
	
	public Jeton(Jeton from) {
		this(from.couleur,from.numeroCoup);
	}
	
	public Jeton(final char _couleur, final byte _numeroCoup) {
		couleur = _couleur;
		numeroCoup = _numeroCoup;
	}
	
	public final char getCouleur() {
		return couleur;
	}
	
	public final byte getNumeroCoup() {
		return numeroCoup;
	}

	@Override
	public String toString() {
		return "Jeton [couleur=" + couleur + "]";
	}
	
	
}
