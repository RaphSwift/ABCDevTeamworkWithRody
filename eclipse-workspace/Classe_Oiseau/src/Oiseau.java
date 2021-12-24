
public class Oiseau {
	
	public Oiseau() {
		this.nom = "pigeon";
		this.taille = 0.4f;
		this.taillePlumes = new float[]{0.05f};
		this.couleurPlumes = new String[] {"Gris"};
		this.tailleAiles = 20f;
		this.tailleBec = 4f;
		this.position = new float[]{0,0,0};
		this.regimeAlimentaire = new String[] {"pain","crouton","vers"};
		this.distanceParcourueEnMetres =0;
		this.ageEnJour=0;
		this.esperanceDeVieEnJour=0;
		this.estUnHomme = true;
		this.vitesseEnVolEnKillometreHeure = 20;
		this.poids = 2;
		this.cri = "piou piou";
		this.habitat = "villes";
	}
	
	public Oiseau(Oiseau from) {
		this(from.nom, from.getTaille(), from.getTaillePlumes(), from.getCouleurPlumes(), from.getTailleAiles(),
				from.getTailleBec(), from.getPosition(), from.getRegimeAlimentaire(), from.getDistanceParcourueEnMetres(), from.getAgeEnJour(),
				from.getEsperanceDeVieEnJour(), from.getEstUnHomme(), from.getVitesseEnKillometreHeure(), from.getPoids(), from.getCri(),
				from.getHabitat());
	}
	
	public Oiseau(String nom, float taille, float[] taillePlumes, String[] couleurPlumes, float tailleAiles, float tailleBec, float[] position,
			String[] regimeAlimentaire, int distanceParcourueEnMetre, int ageEnJour, int esperanceDeVieEnJour,
			boolean estUnHomme, float vitesseEnVolEnKillometreHeure, float poids, String cri, String habitat) {
		this.nom = nom;
		this.taille = taille;
		this.taillePlumes = taillePlumes;
		this.couleurPlumes = couleurPlumes;
		this.tailleAiles = tailleAiles;
		this.tailleBec = tailleBec;
		this.position = position;
		this.regimeAlimentaire = regimeAlimentaire;
		this.distanceParcourueEnMetres =distanceParcourueEnMetre;
		this.ageEnJour=ageEnJour;
		this.esperanceDeVieEnJour=esperanceDeVieEnJour;
		this.estUnHomme = estUnHomme;
		this.vitesseEnVolEnKillometreHeure = vitesseEnVolEnKillometreHeure;
		this.poids = poids;
		this.cri = cri;
		this.habitat = habitat;
		
	}
	
	// GETTEURS
	public String getNom() { return this.nom; }
	public float getTaille() { return this.taille; }
	public float[] getTaillePlumes() { return this.taillePlumes; }
	public String[] getCouleurPlumes() { return this.couleurPlumes; }
	public float getTailleAiles() { return this.tailleAiles; }
	public float getTailleBec() { return this.tailleBec; }
	public float[] getPosition() { return this.position; }
	public String[] getRegimeAlimentaire() { return this.regimeAlimentaire; }
	public int getDistanceParcourueEnMetres() { return this.distanceParcourueEnMetres; }
	public int getAgeEnJour() { return this.ageEnJour; }
	public int getEsperanceDeVieEnJour() { return this.esperanceDeVieEnJour; }
	public boolean getEstUnHomme() { return this.estUnHomme; }
	public float getVitesseEnKillometreHeure() { return this.vitesseEnVolEnKillometreHeure; }
	public float getPoids() { return this.poids; }
	public String getCri() { return this.cri; }
	public String getHabitat() { return this.habitat; }
	
	// AUTRES METHODES
	public void vieillir(int nbJours) {
		ageEnJour = nbJours;
	}
	public boolean estMort() { 
		return ageEnJour>= esperanceDeVieEnJour;
	}
	public void voler(float[] to) {
		double dstParcourue = Math.sqrt(Math.pow(to[0]-this.position[0], 2)+Math.pow(to[1]-this.position[1], 2)+Math.pow(to[2]-this.position[2],2));
		this.position = to;
		distanceParcourueEnMetres += (int)dstParcourue;
		
	}
	public void marcher(float[] to) {
		double dstParcourue = Math.sqrt(Math.pow(to[0]-position[0], 2)+Math.pow(to[1]-position[1], 2)+Math.pow(to[2]-position[2],2));
		this.position = to;
		distanceParcourueEnMetres += (int)dstParcourue;
	}
	public String crier() {
		return cri.toUpperCase()+"!";
	}
	public String decrire() {
		String str;
		str = "Je suis un " + nom + " je mesure " + taille + "m" +
		", j'ai un bec de " + tailleBec + " cm et des ailes de " + tailleAiles +
		"cm,\nj'ai parcourue une distance de " + distanceParcourueEnMetres + " metres," +
		"\nje suis agé de " + ageEnJour + " jours, mon esperance de vie est " + esperanceDeVieEnJour;
		if (estUnHomme)
			str+= ", je suis de sexe masculin";
		else
			str+= ", je suis de sexe feminin";
		str+= "\n.Je vole a une vitesse de " + vitesseEnVolEnKillometreHeure + " km/h, je pèse " + poids + " kg" +
			"\net m'exprime a base de \""+ cri + "\", je vis principalement dans " + habitat +
			"\net me nourris de: ";
		for (int i=0; i < regimeAlimentaire.length; i++) {
			str+=regimeAlimentaire[i];
			if (i+1 != regimeAlimentaire.length)
				str+=",";
		}
		return str;
	}
	public boolean manger(String aliment) {
		int i;
		i = 0;
		boolean rt = false;
		while (rt == false || i < regimeAlimentaire.length) {
			if (regimeAlimentaire[i].equals(aliment)) {
				poids+=0.05;
				rt = true;
			}
			i++;
		}
		return rt;
	}
	
	private String nom;
	private float taille;
	private float[] taillePlumes;
	private String[] couleurPlumes;
	private float tailleAiles;
	private float tailleBec;
	private float[] position;
	private String[] regimeAlimentaire;
	private int distanceParcourueEnMetres;
	private int ageEnJour;
	private int esperanceDeVieEnJour;
	private boolean estUnHomme;
	private float vitesseEnVolEnKillometreHeure;
	private float poids;
	private String cri;
	private String habitat;
	
	
}
