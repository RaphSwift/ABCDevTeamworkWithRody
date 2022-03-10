package utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import pieces.*;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Plateau implements java.io.Serializable{
	private final byte width;
	private final byte height;
	private ArrayList<Piece> pieces;
	private Image imageBois;
	private ImagePattern imagePattern;
	private Canvas[] canvas ;
	/*private Piece selectedPiece;
	private boolean playerTurnBlack;*/

	public Plateau(Plateau from) {
		this(from.width, from.height, from.pieces,from.imageBois,from.imagePattern,from.canvas);

	}


	public Plateau(byte _width, byte _height) {
		canvas = new Canvas[6];
		width = _width;
		height = _height;
		/*playerTurnBlack = false;
		selectedPiece = null;*/
		pieces = new ArrayList<Piece>();
		imageBois = new Image(getClass().getResourceAsStream("/img/bois.jpg"));
		imagePattern = new ImagePattern(imageBois, 0,0,imageBois.getWidth(), imageBois.getHeight(),false);
		canvas[0] = new Canvas();
		canvas[0].setWidth(width*Piece.getImageWidth());
		canvas[0].setHeight(height*Piece.getImageHeight());
		GraphicsContext gctmp = canvas[0].getGraphicsContext2D();
		gctmp.drawImage(imageBois,0,0,canvas[0].getWidth(), canvas[0].getHeight());
		canvas[1] = new Canvas();
		canvas[1].setWidth(width*Piece.getImageWidth());
		canvas[1].setHeight(height*Piece.getImageHeight());
		canvas[2] = new Canvas();
		canvas[2].setWidth(width*Piece.getImageWidth());
		canvas[2].setHeight(height*Piece.getImageHeight());
		Utils.faireDamier(canvas[2],width,height);

		canvas[3] = new Canvas();
		canvas[3].setWidth(width*Piece.getImageWidth());
		canvas[3].setHeight(height*Piece.getImageHeight());
		
		canvas[4] = new Canvas();
		canvas[4].setWidth(width*Piece.getImageWidth());
		canvas[4].setHeight(height*Piece.getImageHeight());
		canvas[4].setOpacity(0);
		direGagnant(canvas[4],"Noir a gagné");
		canvas[5] = new Canvas();
		canvas[5].setWidth(width*Piece.getImageWidth());
		canvas[5].setHeight(height*Piece.getImageHeight());
		canvas[5].setOpacity(0);
		direGagnant(canvas[5],"Blanc a gagné");
		reset();
	}
	
	private void direGagnant(Canvas canvas, String str) {
		Text text = new Text();
		text.setFont(new Font(72));
		text.setText(str);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.rgb(0,0,0,0.5));
		gc.fillRect(0, 0, canvas.getWidth(),canvas.getHeight());
		gc.setFill(Color.rgb(255,255,255));
		gc.setFont(new Font(72));
		Bounds textBounds = text.getBoundsInLocal();
		gc.fillText(str,canvas.getWidth()/2-text.getBoundsInLocal().getWidth()/2, canvas.getHeight()/2-(textBounds.getMinY()+textBounds.getMaxY())/2);


	}



	public Plateau(byte _width, byte _height, ArrayList<Piece> _pieces, Image _img, ImagePattern _pattern,
				   Canvas[] _canvas) {
		width = _width;
		height = _height;
		pieces = new ArrayList<Piece>();
		for (int i = 0; i < _pieces.size(); i++) {
			if (_pieces.get(i) instanceof Piece_Pion) {
				pieces.add(new Piece_Pion((Piece_Pion)_pieces.get(i)));
			} else if (_pieces.get(i) instanceof Piece_Tour) {
				pieces.add(new Piece_Tour((Piece_Tour)_pieces.get(i)));
			} else if (_pieces.get(i) instanceof Piece_Cavalier) {
				pieces.add(new Piece_Cavalier((Piece_Cavalier)_pieces.get(i)));
			} else if (_pieces.get(i) instanceof Piece_Roi) {
				pieces.add(new Piece_Roi((Piece_Roi)_pieces.get(i)));
			} else if (_pieces.get(i) instanceof Piece_Reine) {
				pieces.add(new Piece_Reine((Piece_Reine)_pieces.get(i)));
			} else {
				// FOU
				pieces.add(new Piece_Fou((Piece_Fou)_pieces.get(i)));
			}
			imageBois= _img;
			imagePattern = _pattern;
			canvas = _canvas;

		}
	}


	public final byte getWidth() {
		return width;
	}

	public final byte getHeight(){ return height;}

	public Piece peutEtrePromu(){
		int i=0;
		Piece pieceTmp = null;
		while (pieceTmp == null && i<pieces.size()) {
			if (pieces.get(i) instanceof Piece_Pion) {
				if (pieces.get(i).estNoir() && pieces.get(i).getPosition().getY() == 7) {
					pieceTmp = pieces.get(i);
				} else if (!pieces.get(i).estNoir() && pieces.get(i).getPosition().getY()==0) {
					pieceTmp = pieces.get(i);
				}
			}
			i++;
		}
		return pieceTmp;
	}

	public void promouvoir() {
		int i=0;
		Piece pieceTmp = null;
		while (pieceTmp == null && i<pieces.size()) {
			if (pieces.get(i) instanceof Piece_Pion) {
				if (pieces.get(i).estNoir() && pieces.get(i).getPosition().getY() == 7) {
					pieceTmp = pieces.get(i);
				} else if (!pieces.get(i).estNoir() && pieces.get(i).getPosition().getY()==0) {
					pieceTmp = pieces.get(i);
				}
			}
			i++;
		}
		if (pieceTmp != null) {
			Scanner sc = new Scanner(System.in);
			String saisie;
			Coordonees coordTmp = new Coordonees(pieceTmp.getPosition());
			boolean couleurTmp = pieceTmp.estNoir();
			do {
				System.out.println("En quoi voulez vous promouvoir " + pieceTmp + "?");
				saisie = sc.next().toLowerCase();
			} while (!saisie.equals("cavalier") &&
					!saisie.equals("fou") &&
					!saisie.equals("reine") &&
					!saisie.equals("tour"));
			if (saisie.equals("reine")) {
				removeFromCoord(coordTmp);
				pieces.add(new Piece_Reine(coordTmp, couleurTmp));
			} else if (saisie.equals("cavalier")) {
				removeFromCoord(coordTmp);
				pieces.add(new Piece_Cavalier(coordTmp, couleurTmp));
			} else if (saisie.equals("tour")) {
				removeFromCoord(coordTmp);
				pieces.add(new Piece_Tour(coordTmp, couleurTmp,true));
			} else if (saisie.equals("fou")) {
				removeFromCoord(coordTmp);
				pieces.add(new Piece_Fou(coordTmp, couleurTmp));
			}
			//sc.close();
		}
	}


	@Override
	public String toString() {
		Piece pcs[][] = new Piece[width][height];
		for (int i = 0; i < pieces.size();i++) {
			pcs[pieces.get(i).getPosition().getX()][pieces.get(i).getPosition().getY()] = pieces.get(i);
		}
		String str = "";
		Piece tmp= null;
		for (int i = 0; i < height; i++) {
			str+=i + " ";
			for (int j = 0; j < width; j++) {
				if (pcs[j][i] != null) {
					if (pcs[j][i] instanceof Piece_Roi) {
						str+="K";
					} else if (pcs[j][i] instanceof Piece_Reine) {
						str+="Q";
					} else if (pcs[j][i] instanceof Piece_Fou) {
						str+="F";
					} else if (pcs[j][i] instanceof Piece_Tour) {
						str+="T";
					} else if (pcs[j][i] instanceof Piece_Cavalier) {
						str+="B";
					} else {
						str+="P";
					}
					if (pcs[j][i].estNoir()) {
						str+="b";
					} else {
						str+="w";
					}

				} else {
					str += "--";
				}
				str += " ";
			}
			str+="\n";
		}
		str += "/  0  1  2  3  4  5  6  7";
		return str;
	}

	/*public boolean deplacerPiece(Mouvement mouvement, boolean estNoir) {
		if (mouvement.getType().equals("move")) {
			return deplacerPiece(mouvement.getFrom(), mouvement.getTo(), estNoir);
		} else if (mouvement.getType().equals("roque")) {
			return roquerPiece(estNoir, mouvement.getTo());
		}
		return false;
	}*/

	public boolean roquerPiece(Mouvement mouvement, boolean estNoir ) {

		Plateau p = simulerRoque(estNoir,mouvement.getTo());
		if (p!= null) {
			if (p.getRoi(estNoir).estEnEchec(p).size() >0) {
				return false;
			}
			if (mouvement.getTo().getX()==0) {
				// GRAND ROQUE
				Plateau rt = new Plateau(this);
				Piece roi = rt.getRoi(estNoir);
				if (roi == null) {
					return false;
				}
				getRoi(estNoir).setCoord(new Coordonees((byte)(roi.getPosition().getX()-2), roi.getPosition().getY()),rt);
				getPieceFromCoord(mouvement.getTo()).setCoord(new Coordonees((byte)(mouvement.getTo().getX()+3), mouvement.getTo().getY()),rt);

				return true;
			} else {
				// PETIT ROQUE
				Plateau rt = new Plateau(this);
				Piece roi = rt.getRoi(estNoir);
				if (roi == null) {
					return false;
				}
				getRoi(estNoir).setCoord(new Coordonees((byte)(roi.getPosition().getX()+2), roi.getPosition().getY()),rt);
				getPieceFromCoord(mouvement.getTo()).setCoord(new Coordonees((byte)(mouvement.getTo().getX()-2), mouvement.getTo().getY()),rt);

				return true;
			}
		}
		return false;
	}

	public boolean deplacerPiece(Mouvement mouvement, boolean estNoir) {
		Piece tmp = getPieceFromCoord(mouvement.getFrom());
		Plateau copie = new Plateau(this);
		Piece tmp2 = copie.getPieceFromCoord(mouvement.getFrom());
		if (tmp2 == null || tmp.estNoir() != estNoir) {
			return false;
		} else {
			if (!tmp2.deplacerPiece(mouvement.getTo(), copie)) {
				return false;
			} else {
				copie.removeFromCoord(mouvement.getTo());
				tmp2.setCoord(mouvement.getTo(), copie);
				if (tmp2 instanceof Piece_Roi) {
					if (tmp2.estEnEchec(copie).size() > 0){
						return false;
					}
				} else {
					Piece roi = copie.getRoi(estNoir);
					if (roi.estEnEchec(copie).size() > 0){
						return false;
					}
				}
			}
		}
		if (tmp != null && tmp.estNoir() == estNoir) {
			tmp2 = getPieceFromCoord(mouvement.getTo());
			if(tmp.deplacerPiece(mouvement.getTo(), this)) {
				removeFromCoord(mouvement.getTo());
				tmp.setCoord(mouvement.getTo(), this);

				return true;
			}
		}
		return false;
	}


	public void reset() {
		if (pieces.size() > 0) {
			pieces.clear();
		}

		initialiserPieces();
		//debug1();
	}
	
	private void debug1() {
		pieces.add(new Piece_Roi(new Coordonees((byte)0,(byte)0),false));
		pieces.add(new Piece_Pion(new Coordonees((byte)4,(byte)2),false));
		pieces.add(new Piece_Tour(new Coordonees((byte)1,(byte)2),true));
		pieces.add(new Piece_Tour(new Coordonees((byte)2,(byte)1),true));
		pieces.add(new Piece_Roi(new Coordonees((byte)0,(byte)7),true));
		
	}
	
	private void initialiserPieces() {
		pieces.add(new Piece_Tour(new Coordonees((byte)0,(byte)0), true));
		pieces.add(new Piece_Cavalier(new Coordonees((byte)1,(byte)0), true));
		pieces.add(new Piece_Fou(new Coordonees((byte)2,(byte)0), true));
		pieces.add(new Piece_Reine(new Coordonees((byte)3,(byte)0), true));
		pieces.add(new Piece_Roi(new Coordonees((byte)4,(byte)0), true));
		pieces.add(new Piece_Fou(new Coordonees((byte)5,(byte)0), true));
		pieces.add(new Piece_Cavalier(new Coordonees((byte)6,(byte)0), true));
		pieces.add(new Piece_Tour(new Coordonees((byte)7,(byte)0), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)0,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)1,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)2,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)3,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)4,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)5,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)6,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)7,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)0,(byte)6), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)1,(byte)6), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)2,(byte)6), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)3,(byte)6), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)4,(byte)6), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)5,(byte)6), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)6,(byte)6), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)7,(byte)6), false));
		pieces.add(new Piece_Tour(new Coordonees((byte)0,(byte)7), false));
		pieces.add(new Piece_Cavalier(new Coordonees((byte)1,(byte)7), false));
		pieces.add(new Piece_Fou(new Coordonees((byte)2,(byte)7), false));
		pieces.add(new Piece_Reine(new Coordonees((byte)3,(byte)7), false));
		pieces.add(new Piece_Roi(new Coordonees((byte)4,(byte)7), false));
		pieces.add(new Piece_Fou(new Coordonees((byte)5,(byte)7), false));
		pieces.add(new Piece_Cavalier(new Coordonees((byte)6,(byte)7), false));
		pieces.add(new Piece_Tour(new Coordonees((byte)7,(byte)7), false));
	}


	public GAMESTATUS verifierPlateau() {

		Piece_Roi roi_blanc = (Piece_Roi)getRoi(false);
		Piece_Roi roi_noir = (Piece_Roi)getRoi(true);

		if (roi_blanc.estEchecEtMat(this)) {
			return GAMESTATUS.WHITE_CHECKMATE;
		} else if (roi_noir.estEchecEtMat(this)) {
			return GAMESTATUS.BLACK_CHECKMATE;
		} else if (roi_blanc.estEnEchec(this).size()>0) {
			return GAMESTATUS.WHITE_CHECK;
		} else if (roi_noir.estEnEchec(this).size()>0) {
			return GAMESTATUS.BLACK_CHECK;
		}
		return GAMESTATUS.NOTHING;

	}

	public GAMESTATUS verifierPlateau(boolean estNoir){
		long timerEntree = Utils.getEllapsedTimeInMs();
		GAMESTATUS rt = GAMESTATUS.NOTHING;
		Piece_Roi roi = (Piece_Roi)getRoi(estNoir);
		if ((roi.calculerMouvement(this).size() == 0 && getPieceFromColor(estNoir).size() == 1)) {
			if (estNoir)
				rt = GAMESTATUS.BLACK_CHECKMATE;
			else
				rt = GAMESTATUS.WHITE_CHECKMATE;
		} else {
			if (roi.estEnEchec(this).size() > 0) {
				if (roi.estEchecEtMat(this)){
					if (estNoir){
						rt= GAMESTATUS.BLACK_CHECKMATE;
					}
					rt= GAMESTATUS.WHITE_CHECKMATE;
				}
			}
		}
		long timerSortie = Utils.getEllapsedTimeInMs()-timerEntree;
		return rt;
	}

	public ArrayList<Piece> getPieces(){
		return pieces;
	}

	public ArrayList<Piece> getPieceFromColor(boolean isBlack){
		ArrayList<Piece> rt = new ArrayList<Piece>();
		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i).estNoir() == isBlack) {
				rt.add(pieces.get(i));
			}
		}
		return rt;
	}


	public void dessiner(Group g){
		int i;

		clear(canvas[1]);
		dessinerPiece(canvas[3]);
		
		for (i = 0; i < canvas.length; i++){
			g.getChildren().add(canvas[i]);
		}

	}

	public void dessinerPromotion(boolean color,Group g){
		int i;
		clear(canvas[1]);
		ArrayList<Piece> piecesTmp = new ArrayList<Piece>();
		piecesTmp.add(new Piece_Fou(new Coordonees((byte)2,(byte)4),color));
		piecesTmp.add(new Piece_Tour(new Coordonees((byte)3,(byte)4),color, true));
		piecesTmp.add(new Piece_Reine(new Coordonees((byte)4,(byte)4),color));
		piecesTmp.add(new Piece_Cavalier(new Coordonees((byte)5,(byte)4),color));

		dessinerPiece(canvas[3],piecesTmp);

		for (i = 0; i < canvas.length; i++){
			g.getChildren().add(canvas[i]);
		}

	}
	

	public boolean promouvoirTour(Piece p){
		boolean rt = true;
		Piece_Tour tmp = new Piece_Tour(new Coordonees(p.getPosition()),p.estNoir(),true);
		rt = pieces.remove(p);
		if (rt){
			rt = pieces.add(tmp);
		}
		return rt;
	}

	public boolean promouvoirFou(Piece p){
		boolean rt = true;
		Piece_Fou tmp = new Piece_Fou(new Coordonees(p.getPosition()),p.estNoir());
		rt = pieces.remove(p);
		if (rt){
			rt = pieces.add(tmp);
		}
		return rt;
	}

	public boolean promouvoirReine(Piece p){
		if (p==null)
			return false; 
		boolean rt = true;
		Piece_Reine tmp = new Piece_Reine(new Coordonees(p.getPosition()),p.estNoir());
		rt = pieces.remove(p);
		if (rt){
			rt = pieces.add(tmp);
		}
		return rt;
	}

	public boolean promouvoirCavalier(Piece p){
		boolean rt = true;
		Piece_Cavalier tmp = new Piece_Cavalier(new Coordonees(p.getPosition()),p.estNoir());
		rt = pieces.remove(p);
		if (rt){
			rt = pieces.add(tmp);
		}
		return rt;
	}
	public void dessinerPiece(Canvas c){
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.clearRect(0,0,c.getWidth(),c.getHeight());
		for (int i = 0; i < pieces.size();i++){
			pieces.get(i).dessiner(c);
		}
	}

	public void dessinerPiece(Canvas c, ArrayList<Piece> p){
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.clearRect(0,0,c.getWidth(),c.getHeight());
		for (int i = 0; i < p.size();i++){
			p.get(i).dessiner(c);
		}
	}

	public ArrayList<Piece> getPiecesEnLignes(Coordonees c){
		Coordonees tmp;
		ArrayList<Piece> rt = new ArrayList<Piece>();
		for (int i = 0; i < pieces.size();i++){
			tmp = pieces.get(i).getPosition().getDistanceFrom(c).toAbsolute();
			if (Utils.logicalXOR(tmp.getX() == 0, tmp.getY() == 0)){
				rt.add(pieces.get(i));
			}
		}
		return rt;
	}

	public ArrayList<Piece> getPiecesEnDiagonales(Coordonees c){
		Coordonees tmp;
		ArrayList<Piece> rt = new ArrayList<Piece>();
		for (int i = 0; i < pieces.size();i++){
			tmp = pieces.get(i).getPosition().getDistanceFrom(c).toAbsolute();
			if (tmp.getY() == tmp.getX() && tmp.getX() != 0){
				rt.add(pieces.get(i));
			}
		}
		return rt;
	}

	public ArrayList<Piece> getLLignes(Coordonees c){
		Coordonees tmp;
		ArrayList<Piece> rt = new ArrayList<Piece>();
		for (int i = 0; i < pieces.size();i++){
			tmp = pieces.get(i).getPosition().getDistanceFrom(c).toAbsolute();
			if ((tmp.getX() == 2 && tmp.getY() == 1 ) ||
					(tmp.getX() == 1 && tmp.getY() == 2)){
				rt.add(pieces.get(i));
			}
		}
		return rt;
	}

	public Piece getRoi(boolean estNoir) {
		Piece piece = null;
		int i = 0;
		while (i < pieces.size() && piece == null) {
			if (pieces.get(i) instanceof Piece_Roi && pieces.get(i).estNoir() == estNoir) {
				piece = pieces.get(i);
			}
			i++;
		}
		return piece;
	}

	public Piece getPieceFromCoord(Coordonees coord) {
		Piece piece = null;
		int i = 0;
		while (i < pieces.size() && piece == null) {
			if (pieces.get(i).getPosition().equals(coord)) {
				piece = pieces.get(i);
			}
			i++;
		}
		return piece;
	}


	public boolean removeFromCoord(Coordonees coord) {
		boolean finded = false;
		int i = 0;
		while (i < pieces.size() && !finded) {
			if (pieces.get(i).getPosition().equals(coord)) {
				pieces.remove(i);
				finded = true;
			}
			i++;
		}
		return finded;
	}

	public Plateau simulerRoque(boolean estNoir, Coordonees tour) {
		Coordonees tmp;

		Piece_Roi roi;
		Piece tourRoque;

		if (estNoir) {
			roi = (Piece_Roi) getRoi(estNoir);
			if (roi == null || roi.aBouge()) {
				return null;
			}
			if (roi.getPosition().getX() != 4 || roi.getPosition().getY() != 0) {
				return null;
			}
			if (tour.getY() == 0 && (tour.getX() ==7 || tour.getX()==0)) {
				tourRoque = getPieceFromCoord(tour);
				if (tourRoque != null) {
					if (tourRoque instanceof Piece_Tour) {
						if (!((Piece_Tour)tourRoque).aBouge()) {
							Coordonees coordTestes;
							Piece pieceTmp = null;
							boolean findedPiece =false;
							tmp = roi.getPosition().getDistanceFrom(tour).getDirection();
							coordTestes=new Coordonees((byte)(roi.getPosition().getX()+tmp.getX()),
									(byte)(roi.getPosition().getY()+tmp.getY()));
							do {
								if(getPieceFromCoord(coordTestes)!=null) {
									findedPiece = true;
								}
								coordTestes = new Coordonees((byte)(coordTestes.getX()+tmp.getX()),
										(byte)(coordTestes.getY()-tmp.getY()));

							} while (!coordTestes.equals(tour) && !findedPiece);
							// SI C'EST A GAUCHE
							if (findedPiece) {
								return null;
							}
							if (tour.getX()==0) {
								// GRAND ROQUE
								Plateau rt = new Plateau(this);
								rt.getRoi(estNoir).setCoord(new Coordonees((byte)(roi.getPosition().getX()-2), roi.getPosition().getY()),rt);
								rt.getPieceFromCoord(tour).setCoord(new Coordonees((byte)(tourRoque.getPosition().getX()+3), tourRoque.getPosition().getY()),rt);
								return rt;
							} else {
								// PETIT ROQUE
								Plateau rt = new Plateau(this);
								rt.getRoi(estNoir).setCoord(new Coordonees((byte)(roi.getPosition().getX()+2), roi.getPosition().getY()),rt);
								rt.getPieceFromCoord(tour).setCoord(new Coordonees((byte)(tourRoque.getPosition().getX()-2), tourRoque.getPosition().getY()),rt);
								return rt;
							}
						}
					}
				}

			}
		} else {
			roi = (Piece_Roi) getRoi(estNoir);
			if (roi == null || roi.aBouge()) {
				return null;
			}
			if (roi.getPosition().getX() != 4 || roi.getPosition().getY() != 7) {
				return null;
			}
			if (tour.getY() == 7 && (tour.getX() ==7 || tour.getX()==0)) {
				tourRoque = getPieceFromCoord(tour);
				if (tourRoque != null) {
					if (tourRoque instanceof Piece_Tour) {
						if (!((Piece_Tour)tourRoque).aBouge()) {
							Coordonees coordTestes;
							Piece pieceTmp = null;
							boolean findedPiece =false;
							tmp = roi.getPosition().getDistanceFrom(tour).getDirection();
							coordTestes=new Coordonees((byte)(roi.getPosition().getX()+tmp.getX()),
									(byte)(roi.getPosition().getY()+tmp.getY()));
							do {
								if(getPieceFromCoord(coordTestes)!=null) {
									findedPiece = true;
								}
								coordTestes = new Coordonees((byte)(coordTestes.getX()+tmp.getX()),
										(byte)(coordTestes.getY()-tmp.getY()));

							} while (!coordTestes.equals(tour) && !findedPiece);
							// SI C'EST A GAUCHE
							if (findedPiece) {
								return null;
							}
							if (tour.getX()==0) {
								// GRAND ROQUE
								Plateau rt = new Plateau(this);
								rt.getRoi(estNoir).setCoord(new Coordonees((byte)(roi.getPosition().getX()-2),
										roi.getPosition().getY()),rt);
								rt.getPieceFromCoord(tour).setCoord(new Coordonees((byte)(tourRoque.getPosition().getX()+3),
										tourRoque.getPosition().getY()),rt);
								return rt;
							} else {
								// PETIT ROQUE
								Plateau rt = new Plateau(this);
								rt.getRoi(estNoir).setCoord(new Coordonees((byte)(roi.getPosition().getX()+2), roi.getPosition().getY()),
										rt);
								rt.getPieceFromCoord(tour).setCoord(new Coordonees((byte)(tourRoque.getPosition().getX()-2),
										tourRoque.getPosition().getY()),rt);
								return rt;
							}
						}
					}
				}

			}
		}
		return null;
	}


	public Plateau simulerMouvement(Mouvement m) {
		Plateau rt = new Plateau(this);
		Piece rts[] = new Piece[2];
		rts[0] = rt.getPieceFromCoord(m.getFrom());
		rts[1] = rt.getPieceFromCoord(m.getTo());
		if (rts[0] == null) {
			return null;
		} else {
			if (rts[1] != null) {
				if (rts[1].estNoir() != rts[0].estNoir()) {
					if (!rt.removeFromCoord(m.getTo())) {
						return null;
					}
				} else {
					return null;
				}
			}
			rt.getPieceFromCoord(m.getFrom()).setCoord(m.getTo(), rt);
		}
		return rt;

	}


	public void dessinerIndicator(Coordonees tmp, Piece selectedPiece){
		if (selectedPiece == null) {
			Piece piece = getPieceFromCoord(tmp);

			selectionner(piece);
		} else {

			Piece piece = getPieceFromCoord(tmp);
			if (!selectionner(piece)){
				ArrayList<Mouvement> mouvements = selectedPiece.calculerMouvement(this);
				//deplacerPiece(new Mouvement(selectedPiece.getPosition(), tmp))
			}

		}
	}

	public boolean selectionner(Piece p){
		if (p != null ) {
			Piece selectedPiece = p;
			GraphicsContext gc = canvas[1].getGraphicsContext2D();
			gc.clearRect(0, 0, canvas[1].getWidth(), canvas[1].getHeight());
			gc.setFill(Color.rgb(57, 255, 20, 0.75));
			gc.fillRect(p.getPosition().getX()*Piece.getImageWidth(),p.getPosition().getY()*Piece.getImageHeight(),
					Piece.getImageWidth(), Piece.getImageHeight());
			ArrayList<Mouvement> move = p.calculerMouvement(this);
			if (p instanceof Piece_Pion){
				move.addAll(((Piece_Pion) p).calculerMouvementManger(this));
			}
			Coordonees tmpMove;
			Piece tmpPiece;
			for (int i = 0; i < move.size(); i++) {
				tmpMove = move.get(i).getTo();
				if (move.get(i).getType().equals("move")){
						gc.setFill(Color.rgb(0, 255, 255, 0.75));
				} else if (move.get(i).getType().equals("eat")){
					gc.setFill(Color.rgb(255,0,0,0.75));
				} else if (move.get(i).getType().equals("roque")){
					gc.setFill(Color.rgb(255,255,0,0.75));
				}
				gc.fillRect(tmpMove.getX() * Piece.getImageWidth(), tmpMove.getY() * Piece.getImageHeight(),
						Piece.getImageWidth(), Piece.getImageHeight());
			}

			return true;
		}
		return false;
	}

	private void clear(Canvas c){
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.clearRect(0,0,c.getWidth(),c.getHeight());
	}
}
