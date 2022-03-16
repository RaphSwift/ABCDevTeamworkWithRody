package utils;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class Utils {
	
	
	public static void getParcours(ArrayList<Ville> _villes,Parcours parcour,
			ArrayList<Parcours> parcoursList, int indDep){
		
		ArrayList<Ville> villes = new ArrayList<Ville>();
		for (int i = 0; i < _villes.size(); i++) {
			villes.add(new Ville(_villes.get(i)));
		}
		
		parcour.add(new Ville(villes.get(indDep)));
		villes.remove(indDep);
		if (villes.size() == 0) {
			
			parcoursList.add(parcour);
		} else {
			for (int i = 0; i < villes.size(); i++) {
				getParcours(villes,new Parcours(parcour),parcoursList,i);
			}
		}
	}
	
	public static void haveMin(ArrayList<Parcours> arr) {
		
		if (arr.size() > 1) {
			
			double min = arr.get(arr.size()-1).getTotalDst();
			for (int i = arr.size()-2; i >= 0;i--) {
				if (min > arr.get(i).getTotalDst()) {
					min=arr.get(i).getTotalDst();
					arr.remove(arr.size()-1);
				} else {
					arr.remove(i);
				}
			}
		}
	}
	
    
    public static ArrayList<Ville> getElements(){
    	ArrayList<Ville> villes= new ArrayList<Ville>();
		villes.add(new Ville("Paris", new Coordonees(0,0)));
		villes.add(new Ville("Nantes", new Coordonees(-500,-400)));
		villes.add(new Ville("Metz",new Coordonees(500,50)));
		villes.add(new Ville("Millau",new Coordonees(50, -1200)));
		villes.add(new Ville("Niort",new Coordonees(-450,-600)));
		return villes;
    }
	
	
}
