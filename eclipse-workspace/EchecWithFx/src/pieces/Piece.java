package pieces;


import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;
import utils.Utils;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Piece implements java.io.Serializable, Cloneable{
	protected boolean isBlack;
	protected Coordonees position;

	protected Image image;
	protected Rectangle rect;
	protected ImagePattern pattern;

	private static final int IMG_WIDTH = 64;
	private static final int IMG_HEIGHT = 64;

	public static final int getImageWidth() { return IMG_WIDTH; }
	public static final int getImageHeight() { return IMG_HEIGHT; }

	public Piece(Piece from) {
		this(from.position, from.isBlack);
	}
	
	public abstract byte getTypeId();


	public abstract Object clone() throws CloneNotSupportedException;

	public Piece(Coordonees _position, boolean _isBlack) {
		position = _position;
		isBlack = _isBlack;
		image = new Image(getClass().getResourceAsStream("/img/pieces.png"));
		rect = new Rectangle(position.getX()*getImageWidth(),position.getY() * getImageHeight(),getImageWidth(),
				getImageHeight());

	}


	public boolean estNoir(){
		return isBlack;
	}


	public Coordonees getPosition() {
		return position;
	}

	public boolean setCoord(Coordonees c, Plateau p){
		if (c.getX() >= 0 && c.getX() < p.getWidth() && c.getY() >= 0 && c.getY() < p.getHeight()) {
			position = c;
			rect = new Rectangle(position.getX()*getImageWidth(),position.getY() * getImageHeight(),getImageWidth(),
					getImageHeight());
			return true;
		}
		return false;
	}

	@Override
	public abstract String toString();

	public boolean deplacerPiece(Coordonees coord, Plateau plateau) {
		ArrayList<Mouvement> mvt = calculerMouvement(plateau);
		int i = 0;
		boolean finded = false;
		while (!finded && i < mvt.size()) {
			if (coord.equals(mvt.get(i).getTo())) {
				finded = true;
			}
			i++;
		}
		return finded;
	}

	public abstract ArrayList<Mouvement> calculerMouvement(Plateau p);

	public abstract void dessiner(Canvas c);

	public ArrayList<Mouvement> estEnEchec(Plateau p){
		ArrayList<Mouvement> mvt = new ArrayList<Mouvement>();
		mvt.addAll(testerLignes(p));
		mvt.addAll(testerDiagonales(p));
		mvt.addAll(testerLLines(p));
		
		return mvt;
	}



	protected ArrayList<Mouvement> testerLignes(Plateau p){
		ArrayList<Mouvement> rt = new ArrayList<Mouvement>();

		Piece[] closest = trierTableauLignes(p);
		// L'ordre est défini par un cercle trigonométrique
		for (int i = 0; i < closest.length; i++){
			if (closest[i] != null){
				if (closest[i].estNoir() != isBlack){
					if (closest[i] instanceof Piece_Reine ||
							closest[i] instanceof Piece_Tour){
						rt.add(new Mouvement(
								new Coordonees(closest[i].getPosition()),
								new Coordonees(position)
						));
					} else if (closest[i] instanceof Piece_Roi){
						Coordonees tmp = closest[i].getPosition().getDistanceFrom(position).toAbsolute();
						if (tmp.getX() == 1 || tmp.getY() == 1){
							rt.add(new Mouvement(
									new Coordonees(closest[i].getPosition()),
									new Coordonees(position)
							));
						}
					}
				}
			}
		}
		return rt;
	}

	protected ArrayList<Mouvement> testerLLines(Plateau p){
		ArrayList<Piece> pieces = p.getLLignes(position);
		ArrayList<Mouvement> rt = new ArrayList<Mouvement>();
		for (int i = 0; i < pieces.size() ; i++){
			if (pieces.get(i).estNoir() != isBlack){
				if (pieces.get(i) instanceof Piece_Cavalier){
					rt.add(new Mouvement(
							new Coordonees(pieces.get(i).position),
							new Coordonees(position)
					));
				}
			}
		}
		return rt;
	}


	protected ArrayList<Mouvement> testerDiagonales(Plateau p){
		ArrayList<Mouvement> rt = new ArrayList<Mouvement>();

		Piece[] closest = trierTableauDiagonales(p);
		Coordonees tmp;
		// L'ordre est défini par un cercle trigonométrique
		for (int i = 0; i < closest.length; i++){
			if (closest[i] != null){
				if (closest[i].estNoir() != isBlack){
					if (closest[i] instanceof Piece_Reine ||
							closest[i] instanceof Piece_Fou){
						rt.add(new Mouvement(
								new Coordonees(closest[i].getPosition()),
								new Coordonees(position)
						));
					} else if (closest[i] instanceof Piece_Roi){
						tmp = closest[i].getPosition().getDistanceFrom(position).toAbsolute();
						if (tmp.getX() == 1){
							rt.add(new Mouvement(
									new Coordonees(closest[i].getPosition()),
									new Coordonees(position)
							));
						}
					} else if (closest[i] instanceof  Piece_Pion){
						tmp = closest[i].getPosition().getDistanceFrom(position);
						if (tmp.toAbsolute().getX() == 1){
							// TEST
							if (isBlack){
								if (tmp.getY() < 0){
									rt.add(new Mouvement(
											new Coordonees(closest[i].getPosition()),
											new Coordonees(position)
									));
								}
							} else {
								if (tmp.getY() > 0){
									rt.add(new Mouvement(
											new Coordonees(closest[i].getPosition()),
											new Coordonees(position)
									));
								}
							}
						}
					}
				}
			}
		}
		return rt;
	}

	private Piece[] trierTableauLignes(Plateau p){
		Coordonees tmp;
		Piece[] closest = new Piece[4];
		ArrayList<Piece> from = p.getPiecesEnLignes(position);
		Coordonees[] closestCoord = new Coordonees[4];
		Coordonees tmpCoord;
		for (int i = 0; i < from.size(); i++){
			tmp = from.get(i).getPosition().getDistanceFrom(position);
			tmpCoord = tmp.toAbsolute();
			if (tmp.getX() > 0) {
				if (closestCoord[0] != null) {
					if (tmpCoord.toAbsolute().getX() < closestCoord[0].getX()) {
						closestCoord[0] = new Coordonees(tmpCoord);
						closest[0] = from.get(i);
					}
				} else {
					closestCoord[0] = new Coordonees(tmpCoord);
					closest[0] = from.get(i);
				}
			}
			else if (tmp.getY() > 0) {
				if (closestCoord[1] != null) {
					if (tmpCoord.toAbsolute().getY() < closestCoord[1].getY()) {
						closestCoord[1] = new Coordonees(tmpCoord);
						closest[1] = from.get(i);
					}
				} else {
					closestCoord[1] = new Coordonees(tmpCoord);
					closest[1] = from.get(i);
				}
			} else if (tmp.getX() < 0){
				if (closestCoord[2] != null){
					if (tmpCoord.toAbsolute().getX() < closestCoord[2].getX()){
						closestCoord[2] = new Coordonees(tmpCoord);
						closest[2] = from.get(i);
					}
				} else {
					closestCoord[2] = new Coordonees(tmpCoord);
					closest[2] = from.get(i);
				}
			} else if (tmp.getY() < 0 ){
				if (closestCoord[3] != null){
					if (tmpCoord.toAbsolute().getY() < closestCoord[3].getY()){
						closestCoord[3] = new Coordonees(tmpCoord);
						closest[3] = from.get(i);
					}
				} else {
					closestCoord[3] = new Coordonees(tmpCoord);
					closest[3] = from.get(i);
				}
			}
		}
		return closest;
	}

	private Piece[] trierTableauDiagonales(Plateau p){
		Coordonees tmp;
		Piece[] closest = new Piece[4];
		ArrayList<Piece> from = p.getPiecesEnDiagonales(position);
		Coordonees[] closestCoord = new Coordonees[4];
		for (int i = 0; i < from.size(); i++){
			tmp = from.get(i).getPosition().getDistanceFrom(position);
			if (tmp.getX() > 0 && tmp.getY() > 0){
				if (closest[0] != null){
					if (closestCoord[0].toAbsolute().getX() > tmp.toAbsolute().getX()){
						closest[0] = from.get(i);
						closestCoord[0] = new Coordonees(tmp);
					}
				} else {
					closest[0] = from.get(i);
					closestCoord[0] = new Coordonees(tmp);
				}
			} else if (tmp.getY() > 0 && tmp.getX() < 0){
				if (closest[1] != null){
					if (closestCoord[1].toAbsolute().getY() > tmp.toAbsolute().getY()){
						closest[1] = from.get(i);
						closestCoord[1] = new Coordonees(tmp);
					}
				} else {
					closest[1] = from.get(i);
					closestCoord[1] = new Coordonees(tmp);
				}
			} else if (tmp.getX() < 0 && tmp.getY() < 0){
				if (closest[2] != null){
					if (closestCoord[2].toAbsolute().getX() > tmp.toAbsolute().getX()){
						closest[2] = from.get(i);
						closestCoord[2] = new Coordonees(tmp);
					}
				} else {
					closest[2] = from.get(i);
					closestCoord[2] = new Coordonees(tmp);
				}
			} else if (tmp.getY() < 0 && tmp.getX() > 0){
				if (closest[3] != null){
					if (closestCoord[3].toAbsolute().getX() > tmp.toAbsolute().getX()){
						closest[3] = from.get(i);
						closestCoord[3] = new Coordonees(tmp);
					}
				} else {
					closest[3] = from.get(i);
					closestCoord[3] = new Coordonees(tmp);
				}
			}
		}
		return closest;
	}

}
