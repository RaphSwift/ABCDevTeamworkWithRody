
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
	
	public void verouillerPorte() {
		estVerouillee=true;
	}
	
	public void deverouillerPorte() {
		estVerouillee = false;
	}
	
	public void ouvrir(float pourcentageOuvertureFinal) {
		if (!estVerouillee) {
			if (pourcentageOuvertureFinal > pourcentageOuverture) {
				pourcentageOuverture = pourcentageOuvertureFinal;
			}
		}
	}
	
	public void fermer(float pourcentageOuvertureFinal) {
		if (!estVerouillee) {
			if (pourcentageOuvertureFinal < pourcentageOuverture) {
				pourcentageOuverture = pourcentageOuvertureFinal;
			}
		}
	}
}
