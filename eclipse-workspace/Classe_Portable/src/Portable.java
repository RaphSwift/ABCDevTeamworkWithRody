
public class Portable {
	private boolean estEteint;
	private boolean estEnModeAvion;
	private boolean estEnEconomieEnergie;
	private String marque;
	private String modele;
	
	private float puissanceTelephoneEnWatt;
	private int tensionTelephoneEnVolts;
	private float intensiteDelivreEnAh;
	private float batterieDepenseeEnAh;
	public Portable(Portable from) {
		this (from.estEteint, from.estEnModeAvion, from.estEnEconomieEnergie, from.marque, from.modele,
			from.puissanceTelephoneEnWatt,from.tensionTelephoneEnVolts,from.intensiteDelivreEnAh, from.batterieDepenseeEnAh);
	}
	
	public Portable(boolean _estEteint,
			boolean _estEnModeAvion,
			boolean _estEnEconomieEnergie,
			String _marque,
			String _modele,
			float _puissanceTelephoneEnWatt,
			int _tensionTelephoneEnVolts,
			float _intensiteDelivreEnAh,
			float _batterieDepenseeEnAh) {
		estEteint = _estEteint;
		estEnModeAvion = _estEnModeAvion;
		estEnEconomieEnergie = _estEnEconomieEnergie;
		marque = _marque;
		modele = _modele;
		puissanceTelephoneEnWatt = _puissanceTelephoneEnWatt;
		tensionTelephoneEnVolts= _tensionTelephoneEnVolts;
		intensiteDelivreEnAh = _intensiteDelivreEnAh;
		batterieDepenseeEnAh = _batterieDepenseeEnAh;
		
	}
	
	public boolean getEstEteint() { return estEteint;}
	public boolean getEstEnEconomieEnergie() { return estEnEconomieEnergie; }
	public String getMarque() { return marque; }
	public String getModele() { return modele; }
	public float getPuissanceTelephoneEnWatt() { return puissanceTelephoneEnWatt; }
	public int getTensionTelephoneEnVolts() { return tensionTelephoneEnVolts; }
	public float getIntensiteDelivreEnAh() { return intensiteDelivreEnAh; }
	public float getBatterieDepenseeEnAh() { return batterieDepenseeEnAh;}
	public void eteindre() {
		estEteint = true;
		estEnEconomieEnergie = false;
		estEnModeAvion = false;
		puissanceTelephoneEnWatt = 0.01f;
	}
	
	
	public void allumer() throws Exception{
		if (batterieDepenseeEnAh >= intensiteDelivreEnAh)
			throw new Exception("La batterie est vide impossible d'allumer");
		estEteint = false;
		estEnEconomieEnergie = false;
		estEnModeAvion = false;
		puissanceTelephoneEnWatt = 9.0f;
	}
	
	public void activerEconomieEnergie() {
		if (!estEteint && !estEnEconomieEnergie) {
			estEnEconomieEnergie = true;
			puissanceTelephoneEnWatt -= 4;
		}
	}
	
	public void desactiverEconomieEnergie() {
		if (!estEteint && estEnEconomieEnergie) {
			estEnEconomieEnergie = false;
			puissanceTelephoneEnWatt += 4;
		}
	}
	
	public void activerModeAvion() {
		if  (!estEteint && !estEnModeAvion) {
			estEnModeAvion = true;
			puissanceTelephoneEnWatt -= 1;
		}
	}
	
	public void desactiverModeAvion() {
		if (!estEteint) {
			estEnModeAvion = false;
			puissanceTelephoneEnWatt += 1;
		}
	}
	
	public boolean allerSurYoutube() {
		if (estEteint || estEnModeAvion)
			return false;
		else
			return true;
	}
	
	public double connaitreTempsBatterieEnSecondes() {
		return (3600.0f*(intensiteDelivreEnAh-batterieDepenseeEnAh))/(puissanceTelephoneEnWatt/tensionTelephoneEnVolts);
		
	}
	
	
	
	public void faireDefilerTemps(int timeDurationSeconds) {
		float batterieDepenseeTmp;
		if (timeDurationSeconds > 0) {
			batterieDepenseeTmp = (puissanceTelephoneEnWatt/tensionTelephoneEnVolts)*(3600/timeDurationSeconds);
			batterieDepenseeEnAh = Math.min(batterieDepenseeEnAh+ batterieDepenseeTmp, intensiteDelivreEnAh);
			if (batterieDepenseeEnAh == 0) {
				eteindre();
			}
		}
	}
	
}
