import Exception.CloseException;
import Exception.LockException;
import Exception.OpenException;
import Exception.UnlockException;

public class PorteGarage {
	private String marque;
	private String modele;
	private boolean estVerouillee;
	private float pourcentageOuverture;
	
	public PorteGarage(PorteGarage from) {
		this(from.marque, from.modele, from.estVerouillee, from.pourcentageOuverture);
	}
	public PorteGarage(String _marque, String _modele, boolean _estVerouillee, float _pourcentageOuverture) {
		marque = _marque;
		modele = _modele;
		estVerouillee = _estVerouillee;
		pourcentageOuverture = _pourcentageOuverture;
	}
	
	public boolean verouillerPorte() {
		if (!estVerouillee) {
			estVerouillee=true;
			return true;
			
		}
		return false;
	}
	
	public boolean deverouillerPorte() {
		if (estVerouillee) {
			estVerouillee=false;
			return true;
			
		}
		return false;
	}
	
	public boolean ouvrir(float pourcentageOuvertureFinal) {
		if (!estVerouillee) {
			if (pourcentageOuvertureFinal > pourcentageOuverture &&
				pourcentageOuvertureFinal <= 1) {
				pourcentageOuverture = pourcentageOuvertureFinal;
				return true;
			}
		}
		return false;
		
	}
	
	public boolean fermer(float pourcentageOuvertureFinal) {
		if (!estVerouillee) {
			if (pourcentageOuvertureFinal < pourcentageOuverture &&
				pourcentageOuvertureFinal >= 0) {
				pourcentageOuverture = pourcentageOuvertureFinal;
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public String toString() {
		return "PorteGarage [marque=" + marque + ", modele=" + modele + ", estVerouillee=" + estVerouillee
				+ ", pourcentageOuverture=" + pourcentageOuverture + "]";
	}
	
	public void verouillerPorteWithException() throws LockException{
		if (!estVerouillee) {
			estVerouillee=true;
			
		}
		throw new LockException("Impossible de verrouiller un portail déja verouillé");
	}
	
	public void deverouillerPorteWithException() throws UnlockException{
		if (estVerouillee) {
			estVerouillee=false;
			
		}
		throw new UnlockException("Impossible de déverrouiller un portail déja déverouillé");
	}
	
	public void ouvrirWithException(float pourcentageOuvertureFinal) throws OpenException{
		if (!estVerouillee) {
			if (pourcentageOuvertureFinal > pourcentageOuverture &&
				pourcentageOuvertureFinal <= 1) {
				pourcentageOuverture = pourcentageOuvertureFinal;
			} else {
				if (pourcentageOuvertureFinal <= pourcentageOuverture) {
					throw new OpenException("Le pourcentage d'ouverture final ne peux etre inférieur à celui actuel");
				} else if (pourcentageOuvertureFinal > 1f) {
					throw new OpenException("Le pourcentage d'ouverture ne peux pas dépasser 100%");
				}
			}
		}
		throw new OpenException("Le portail est vérouillé impossible de l'ouvrir");
	}
	
	public void fermerWithException(float pourcentageOuvertureFinal) throws CloseException{
		if (!estVerouillee) {
			if (pourcentageOuvertureFinal < pourcentageOuverture &&
				pourcentageOuvertureFinal >= 0) {
				pourcentageOuverture = pourcentageOuvertureFinal;
			} else {
				if (pourcentageOuvertureFinal >= pourcentageOuverture) {
					throw new CloseException("Impossible de mettre le portail a un taux d'ouverture plus grand en fermeture");
				} else if (pourcentageOuvertureFinal < 0f) {
					throw new CloseException("Le pourcentage d'ouverture ne peux pas etre negatif");
				}
			}
		}
		throw new CloseException("Le portail est verouillé impossible de le fermer");
	
	}
	
	
	
}
