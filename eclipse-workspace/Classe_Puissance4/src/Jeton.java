
public class Jeton implements java.io.Serializable,Cloneable{
	public final char couleur;
	
	public Object clone() {
		return new Jeton(this);
	}
	
	public Jeton(Jeton from) {
		this(from.couleur);
	}
	
	public Jeton(final char _couleur) {
		couleur = _couleur;
	}
	
	public final char getCouleur() {
		return couleur;
	}

	@Override
	public String toString() {
		return "Jeton [couleur=" + couleur + "]";
	}
	
	
}
