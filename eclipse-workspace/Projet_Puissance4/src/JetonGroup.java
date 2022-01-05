import java.util.ArrayList;

/* Classe qui designe un ensemble constitué
 * d'au moins deux jetons qui semblent formé
 * une ligne ou diagonale permettant de les lier entre eux
 */
public class JetonGroup {
	private ArrayList<Jeton> jetons;
	private boolean color;
	private byte direction;
	// CONSTANTES DE DIRECTION
	public final static byte HORIZONTAL = 0;
	public final static byte VERTICAL = 1;
	public final static byte DIAGONAL_FORWARD = 2;	
	public final static byte DIAGONAL_BACK = 3;
	
	public JetonGroup(boolean _color, Jeton _jeton1, Jeton _jeton2) {
		color = _color;
		
	}
	 

	public Coordonees[] getBorder(/*Plateau p*/) {
		Coordonees[] rt= {null,null};
		//
		byte[] bounds = getBounds();
		// ----------
		
		if (direction == DIAGONAL_BACK) {
			rt[0] = new Coordonees((byte)(bounds[0]-1),(byte)(bounds[1]-1));
			rt[1] = new Coordonees((byte)(bounds[1]+1),(byte)(bounds[3]+1));
		} else if (direction == DIAGONAL_FORWARD) {
			rt[0] = new Coordonees((byte)(bounds[2]+1),(byte)(bounds[1]-1));
			rt[1] = new Coordonees((byte)(bounds[0]-1),(byte)(bounds[3]+1));
		} else if (direction == VERTICAL) {
			rt[0] = new Coordonees((byte)(bounds[0]),(byte)(bounds[1]-1));
			rt[1] = new Coordonees((byte)(bounds[0]),(byte)(bounds[3]+1));
		} else {
			rt[0] = new Coordonees((byte)(bounds[0]-1),(byte)(bounds[1]));
			rt[1] = new Coordonees((byte)(bounds[2]+1),(byte)(bounds[1]));
		}
		/*if (rt[0].getXPos() < 0 || rt[0].getXPos() > p.getWidth())
			rt[0] = null;
		if (rt[1].getXPos() < 0 || rt[1].getXPos() > p.getWidth())
			rt[1] = null;*/
		return rt;
	}
	
	public boolean getColor() {
		return color;
	}
	
	public byte[] getBounds() {
		byte min_X, min_Y, max_X, max_Y;
		min_X = jetons.get(0).posX;
		min_Y = jetons.get(0).posY;
		max_X = jetons.get(0).posX;
		max_Y = jetons.get(0).posY;
		for (int i = 1 ; i < jetons.size(); i++) {
			if (jetons.get(i).getXPos() > max_X ) {
				max_X = jetons.get(i).getXPos();
			} else if (jetons.get(i).getXPos() < min_X){
				min_X = jetons.get(i).getXPos();
			}
			if (jetons.get(i).getYPos() > max_Y) {
				max_Y = jetons.get(i).getYPos();
			} else if (jetons.get(i).getXPos() < max_Y){
				min_Y = jetons.get(i).getYPos();
			}
		}
		return new byte[] {min_X, min_Y, max_X, max_Y};
	}
	
	public boolean isOnTheGroup(Jeton j) {
		boolean rt;
		rt = false;
		int index = 0; 
		while (!rt && index <= jetons.size()) {
			if (jetons.get(index).equals(j))
				rt = true;
			index++;
		}
		return rt;
	}
	
	public boolean ajouterJeton(Jeton j) {
		boolean rt = false;
		if (j.couleurJeton == color) {
			Coordonees[] borders = getBorder();
			int i = 0;
			boolean finded = false;
			while (i < 2 && !finded) {
				if (borders[i].equals(j)) {
					jetons.add(j);
					rt = true;
					finded = true;
				}
			}
			
		}
		return rt;
	}
	
	public boolean fusion(JetonGroup b) {
		int i = 0;

		boolean finded = false;
		
		if (b.direction == direction && b.color == color) {
			while (i < b.jetons.size() && !finded) {
				if (isOnTheGroup(b.jetons.get(i)))
					finded = true;
				i++;
			}
			if (finded) {
				jetons.addAll(b.jetons);
			}
		}				
		return finded;
	}
}
