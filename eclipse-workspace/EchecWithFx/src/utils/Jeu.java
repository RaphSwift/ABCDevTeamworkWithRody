package utils;


import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import pieces.Piece;
import pieces.Piece_Pion;
import pieces.Piece_Reine;
import pieces.Piece_Roi;
import pieces.Piece_Tour;


public class Jeu implements java.io.Serializable, EventHandler<MouseEvent> {
	private Plateau plateauActuel;
	private Joueur participants[];
	private GridPane grid;
	protected Piece selected;
	private Joueur joueurActuel;
	private CommandManager commands;
	private GAMESTATUS lastPlateau;
	private SoundManager soundManager;
	private Font mainFont;
	private Thread timeManager;
	private Image imageTige;

	public boolean serialize() {
		boolean rt = true;
		
		try {
			FileOutputStream fileOut = new FileOutputStream("save.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (Exception e) {
			//e.printStackTrace();
			rt= false;
		}
		return rt;
	}
	
	private void initGridConstraint(GridPane grid) {
		for (int i =0; i < 4; i++) {
			ColumnConstraints columnConstraint = new ColumnConstraints();
			columnConstraint.setPercentWidth(25);
			columnConstraint.setMinWidth(300);
			columnConstraint.setHalignment(HPos.CENTER);
			grid.getColumnConstraints().add(columnConstraint);
		}
		
		RowConstraints rowConstraint = new RowConstraints();
		RowConstraints rowConstraint2 = new RowConstraints();
		rowConstraint.setPercentHeight(75);
		rowConstraint.setMinHeight(600);
		rowConstraint2.setPercentHeight(25);
		rowConstraint.setMinHeight(200);
		grid.getRowConstraints().add(rowConstraint);
		grid.getRowConstraints().add(rowConstraint2);
	}
	
	private void initHistory() {
		VBox vb = new VBox();
		vb.setId("#history");
		vb.setPrefSize(300, 800);
		grid.add(vb, 3, 0, 1, 2);
	}
	
	private Group prepareCanvas() {
		VBox vb = (VBox) getNodeById("#history");
		Group gp = new Group();
		Canvas canvas = new Canvas(vb.getWidth(),(vb.getBoundsInParent().getHeight()-40)/10);
		Image imageBois = new Image(getClass().getResourceAsStream("/img/textureMetal.jpg"));
		GraphicsContext gc_timer = canvas.getGraphicsContext2D();
		gc_timer.drawImage(imageBois, 0, 0, canvas.getWidth(), canvas.getHeight(),
				0,0,canvas.getWidth(), canvas.getHeight());
		gp.getChildren().add(canvas);
		return gp;

		
	}
	
	private Group prepareMoveCanvas(MoveCommand mc) {
		Group gp = prepareCanvas();
		Canvas canvas = (Canvas)gp.getChildren().get(0);
		Canvas canvasCouleur = new Canvas(canvas.getWidth(), canvas.getHeight());
		GraphicsContext gc = canvasCouleur.getGraphicsContext2D();
		Paint p = null;
		if (mc.isBlack()) {
			p = Color.rgb(0,255,0,0.2); 
		} else {
			p = Color.rgb(0,255,0,0.35); 
		}
		Text text = new Text();
		text.setFont(new Font(42));
		String textstr = mc.getMove().getFrom() + " -> " + mc.getMove().getTo();
		text.setText(textstr);
		Canvas canvasText = new Canvas(text.getBoundsInLocal().getWidth(),
				text.getBoundsInLocal().getHeight());
		
		GraphicsContext gc_text = canvasText.getGraphicsContext2D();
		gc_text.setFont(new Font(42));
		Image imagePiece = new Image(getClass().getResourceAsStream("/img/pieces.png"));
		Canvas canvasPiece = new Canvas(canvas.getHeight()-10, canvas.getHeight()-10);
		Canvas canvasPiceceBackground = new Canvas(canvasPiece.getWidth()+5, canvasPiece.getWidth()+5);
		
		GraphicsContext gc_piece = canvasPiece.getGraphicsContext2D();
		gc_piece.drawImage(imagePiece, mc.getType()*Piece.getImageWidth(), (mc.isBlack() ? Piece.getImageHeight():0),
				Piece.getImageWidth(),Piece.getImageHeight(),
				0,0,canvasPiece.getWidth(),canvasPiece.getHeight());
		GraphicsContext gc_pieceBackground = canvasPiceceBackground.getGraphicsContext2D();
		gc_pieceBackground.setFill(mc.isBlack() ? Color.rgb(255,255,255,0.4) : Color.rgb(0,0,0,0.4));
		gc_pieceBackground.fillOval(0, 0, canvasPiceceBackground.getWidth(), canvasPiceceBackground.getHeight());
		canvasPiece.setTranslateX(5);
		canvasPiece.setTranslateY(5);
		canvasPiceceBackground.setTranslateX(canvasPiece.getTranslateX()-3);
		canvasPiceceBackground.setTranslateY(canvasPiece.getTranslateY());
		
		
		gc_text.setFill((mc.isBlack() ? Color.BLACK : Color.WHITE));
		gc_text.fillText(textstr, 0, -text.getBoundsInLocal().getMinY());
		canvasText.setTranslateY(canvas.getHeight()/2 - text.getBoundsInLocal().getHeight()/2);
		canvasText.setTranslateX(92);
		gc.setFill(p);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gp.getChildren().add(canvasCouleur);
		gp.getChildren().add(canvasPiceceBackground);
		gp.getChildren().add(canvasPiece);
		gp.getChildren().add(canvasText);
		
		return gp;
	}
	
	private Group prepareEatCanvas(EatCommand mc) {
		Group gp = prepareCanvas();
		Canvas canvas = (Canvas)gp.getChildren().get(0);
		Canvas canvasCouleur = new Canvas(canvas.getWidth(), canvas.getHeight());
		GraphicsContext gc = canvasCouleur.getGraphicsContext2D();
		Paint p = null;
		if (mc.isBlack()) {
			p = Color.rgb(255,0,0,0.2); 
		} else {
			p = Color.rgb(255,0,0,0.35); 
		}
		Text text = new Text();
		text.setFont(new Font(42));
		String textstr = mc.getMove().getFrom() + " -> " + mc.getMove().getTo();
		text.setText(textstr);
		Canvas canvasText = new Canvas(text.getBoundsInLocal().getWidth(),
				text.getBoundsInLocal().getHeight());
		
		GraphicsContext gc_text = canvasText.getGraphicsContext2D();
		gc_text.setFont(new Font(42));
		Image imagePiece = new Image(getClass().getResourceAsStream("/img/pieces.png"));
		Canvas canvasPiece = new Canvas(canvas.getHeight()-10, canvas.getHeight()-10);
		Canvas canvasPiceceBackground = new Canvas(canvasPiece.getWidth()+5, canvasPiece.getWidth()+5);
		
		GraphicsContext gc_piece = canvasPiece.getGraphicsContext2D();
		gc_piece.drawImage(imagePiece, mc.getType()*Piece.getImageWidth(), (mc.isBlack() ? Piece.getImageHeight():0),
				Piece.getImageWidth(),Piece.getImageHeight(),
				0,0,canvasPiece.getWidth(),canvasPiece.getHeight());
		GraphicsContext gc_pieceBackground = canvasPiceceBackground.getGraphicsContext2D();
		gc_pieceBackground.setFill(mc.isBlack() ? Color.rgb(255,255,255,0.4) : Color.rgb(0,0,0,0.4));
		gc_pieceBackground.fillOval(0, 0, canvasPiceceBackground.getWidth(), canvasPiceceBackground.getHeight());
		canvasPiece.setTranslateX(5);
		canvasPiece.setTranslateY(5);
		canvasPiceceBackground.setTranslateX(canvasPiece.getTranslateX()-3);
		canvasPiceceBackground.setTranslateY(canvasPiece.getTranslateX());
		
		Canvas canvasPieceMange = new Canvas(canvasPiece.getWidth()/2, canvasPiece.getHeight()/2);
		Canvas canvasPiceceBackgroundMange = new Canvas(canvasPieceMange.getWidth()+5, canvasPieceMange.getWidth()+5);
		
	
		GraphicsContext gc_pieceMange = canvasPieceMange.getGraphicsContext2D();
		gc_pieceMange.drawImage(imagePiece, mc.getRelatedPiece().getTypeId()*Piece.getImageWidth(), 
				(mc.getRelatedPiece().estNoir() ? Piece.getImageHeight():0),Piece.getImageWidth(),Piece.getImageHeight(),
				0,0,canvasPieceMange.getWidth(),canvasPieceMange.getHeight());
		GraphicsContext gc_pieceBackgroundMange = canvasPiceceBackgroundMange.getGraphicsContext2D();
		gc_pieceBackgroundMange.setFill(mc.getRelatedPiece().estNoir() ? Color.rgb(255,255,255,0.4) : Color.rgb(0,0,0,0.4));
		gc_pieceBackgroundMange.fillOval(0, 0, canvasPiceceBackgroundMange.getWidth(), canvasPiceceBackgroundMange.getHeight());
		canvasPieceMange.setTranslateX(5+canvasPiece.getWidth()/2);
		canvasPieceMange.setTranslateY(5+canvasPiece.getHeight()/2);
		canvasPiceceBackgroundMange.setTranslateX(canvasPieceMange.getTranslateX()-2);
		canvasPiceceBackgroundMange.setTranslateY(canvasPieceMange.getTranslateX());
		
		/* AJOUTER UNE CROIX */
		Canvas canvasCroix = new Canvas(canvasPieceMange.getWidth(), 5);
		canvasCroix.setTranslateX(canvasPieceMange.getTranslateX());
		canvasCroix.setTranslateY(canvasPieceMange.getTranslateY()+canvasPieceMange.getHeight()/2);
		canvasCroix.rotateProperty().set(45.0f);
		GraphicsContext gc_croix = canvasCroix.getGraphicsContext2D();
		gc_croix.setFill(Color.rgb(255,0,0,0.5));
		gc_croix.fillRect(0, 0, canvasCroix.getWidth(), canvasCroix.getHeight());
		
		Canvas canvasCroix2 = new Canvas(canvasPieceMange.getWidth(), 5);
		canvasCroix2.setTranslateX(canvasCroix.getTranslateX());
		canvasCroix2.setTranslateY(canvasCroix.getTranslateY());
		canvasCroix2.rotateProperty().set(-45.0f);
		GraphicsContext gc_croix2 = canvasCroix2.getGraphicsContext2D();
		gc_croix2.setFill(Color.rgb(255,0,0,0.5));
		gc_croix2.fillRect(0, 0, canvasCroix.getWidth(), canvasCroix.getHeight());
		
		gc_text.setFill((mc.isBlack() ? Color.BLACK : Color.WHITE));
		gc_text.fillText(textstr, 0, -text.getBoundsInLocal().getMinY());
		canvasText.setTranslateY(canvas.getHeight()/2 - text.getBoundsInLocal().getHeight()/2);
		canvasText.setTranslateX(92);
		gc.setFill(p);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gp.getChildren().add(canvasCouleur);
		gp.getChildren().add(canvasPiceceBackground);
		gp.getChildren().add(canvasPiece);
		gp.getChildren().add(canvasPiceceBackgroundMange);
		gp.getChildren().add(canvasPieceMange);
		gp.getChildren().add(canvasCroix);
		gp.getChildren().add(canvasCroix2);
		gp.getChildren().add(canvasText);
		
		return gp;
	}
	
	private Group preparePromouvoirCanvas(PromouvoirCommand mc) {
		Group gp = prepareCanvas();
		Canvas canvas = (Canvas)gp.getChildren().get(0);
		Canvas canvasCouleur = new Canvas(canvas.getWidth(), canvas.getHeight());
		GraphicsContext gc = canvasCouleur.getGraphicsContext2D();
		Paint p = null;
		if (mc.getFrom().estNoir()) {
			p = Color.rgb(255,255,0,0.2); 
		} else {
			p = Color.rgb(255,255,0,0.35); 
		}
		gc.setFill(p);
		
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		Canvas canvasPieceAvant = new Canvas(canvas.getHeight()/1.5-10, canvas.getHeight()/1.5-10);
		Canvas canvasPieceApres = new Canvas(canvas.getHeight()-10, canvas.getHeight()-10);
		Canvas canvasBackgroundAvant = new Canvas(canvasPieceAvant.getWidth()+5, canvasPieceAvant.getHeight()+5);
		Canvas canvasBackgroundApres = new Canvas(canvasPieceApres.getWidth()+5, canvasPieceApres.getHeight()+5);
		canvasPieceAvant.setTranslateX(10);
		canvasPieceAvant.setTranslateY(canvas.getHeight()/2-canvasPieceAvant.getHeight()/2);
		canvasBackgroundAvant.setTranslateX(canvasPieceAvant.getTranslateX()-3);
		canvasBackgroundAvant.setTranslateY(canvasPieceAvant.getTranslateY()-3);
		canvasPieceApres.setTranslateX(canvas.getWidth()-canvasBackgroundApres.getWidth()-20);
		canvasPieceApres.setTranslateY(canvas.getHeight()/2-canvasPieceApres.getHeight()/2);
		canvasBackgroundApres.setTranslateX(canvasPieceApres.getTranslateX()-3);
		canvasBackgroundApres.setTranslateY(canvasPieceApres.getTranslateY()-3);
		GraphicsContext gc_avantPiece = canvasPieceAvant.getGraphicsContext2D();
		GraphicsContext gc_avantBackground = canvasBackgroundAvant.getGraphicsContext2D();
		GraphicsContext gc_apresPiece = canvasPieceApres.getGraphicsContext2D();
		GraphicsContext gc_apresBackground = canvasBackgroundApres.getGraphicsContext2D();
		Image imagePiece = new Image(getClass().getResourceAsStream("/img/pieces.png"));
		gc_avantPiece.drawImage(imagePiece, mc.getFrom().getTypeId()*Piece.getImageWidth(),
				(mc.getFrom().estNoir() ? Piece.getImageHeight():0),
				Piece.getImageWidth(),Piece.getImageHeight(),
				0,0,canvasPieceAvant.getWidth(),canvasPieceAvant.getHeight());
		gc_avantBackground.setFill(mc.getFrom().estNoir() ? Color.rgb(255,255,255,0.4) : Color.rgb(0,0,0,0.4));
		gc_avantBackground.fillOval(0, 0, canvasBackgroundAvant.getWidth(), canvasBackgroundAvant.getHeight());
		gc_apresPiece.drawImage(imagePiece, mc.getTo().getTypeId()*Piece.getImageWidth(),
				(mc.getTo().estNoir() ? Piece.getImageHeight():0),Piece.getImageWidth(),Piece.getImageHeight(),
				0,0,canvasPieceApres.getWidth(),canvasPieceApres.getHeight());
		gc_apresBackground.setFill(mc.getTo().estNoir() ? Color.rgb(255,255,255,0.4) : Color.rgb(0,0,0,0.4));
		gc_apresBackground.fillOval(0, 0, canvasBackgroundApres.getWidth(), canvasBackgroundApres.getHeight());
		
		
		gp.getChildren().add(canvasCouleur);
		gp.getChildren().add(canvasBackgroundAvant);
		gp.getChildren().add(canvasPieceAvant);
		gp.getChildren().add(canvasBackgroundApres);
		gp.getChildren().add(canvasPieceApres);
		return gp;
	}
	
	private Group prepareRoqueCanvas(RoqueCommand mc) {
		Group gp = prepareCanvas();
		Canvas canvas = (Canvas)gp.getChildren().get(0);
		Canvas canvasCouleur = new Canvas(canvas.getWidth(), canvas.getHeight());
		
		GraphicsContext gc = canvasCouleur.getGraphicsContext2D();
		Paint p = null;
		if (mc.isBlack()) {
			p = Color.rgb(0,0,255,0.2); 
		} else {
			p = Color.rgb(0,0,255,0.35); 
		}
		String textStr = "->"+ mc.getMove().getTo();
		Text text = new Text();
		text.setFont(new Font(42));
		text.setText(textStr);
		Image imagePiece = new Image(getClass().getResourceAsStream("/img/pieces.png"));
		
		Canvas canvasPiece = new Canvas(canvas.getHeight()-20, canvas.getHeight()-20);
		Canvas canvasBackground = new Canvas(canvasPiece.getWidth()+5, canvasPiece.getHeight()+5);
		Canvas canvasText = new Canvas(text.getBoundsInLocal().getWidth(),text.getBoundsInLocal().getHeight());
		
		
		canvasPiece.setTranslateX(10);
		canvasPiece.setTranslateY(10);
		canvasBackground.setTranslateX(canvasPiece.getTranslateX()-3);
		canvasBackground.setTranslateY(canvasPiece.getTranslateY()-3);
		canvasText.setTranslateX(15+canvasBackground.getWidth());
		canvasText.setTranslateY(canvas.getHeight()/2-text.getBoundsInLocal().getHeight()/2);
		
		GraphicsContext gc_piece = canvasPiece.getGraphicsContext2D();
		GraphicsContext gc_background = canvasBackground.getGraphicsContext2D();
		GraphicsContext gc_text = canvasText.getGraphicsContext2D();
		
		
		gc_piece.drawImage(imagePiece, 0, (mc.isBlack() ? Piece.getImageHeight():0),Piece.getImageWidth(),Piece.getImageHeight(),
				0,0,canvasPiece.getWidth(),canvasPiece.getHeight());
		gc_background.setFill((mc.isBlack() ? Color.rgb(255,255,255,0.4) : Color.rgb(0,0,0,0.4)));
		gc_background.fillOval(0, 0, canvasBackground.getWidth(), canvasBackground.getHeight());
		gc.setFill(p);
		gc_text.setFont(new Font(42));
		gc_text.setFill((mc.isBlack() ? Color.rgb(0, 0, 0) : Color.rgb(255, 255, 255)));
		gc_text.fillText(textStr, 0, -text.getBoundsInLocal().getMinY());
		
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gp.getChildren().add(canvasCouleur);
		gp.getChildren().add(canvasBackground);
		gp.getChildren().add(canvasPiece);
		gp.getChildren().add(canvasText);		
		
		return gp;
	}
	
	
	private void addMove() {
		VBox vb = (VBox)getNodeById("#history");
		if (vb.getChildren().size() == 10) {
			vb.getChildren().remove(vb.getChildren().get(0));
		}		
		Group gp = new Group();
		if (commands.getLastCommand() instanceof MoveCommand) {
			gp = prepareMoveCanvas((MoveCommand)commands.getLastCommand());
		} else {
			if (commands.getLastCommand() instanceof EatCommand) {
				gp = prepareEatCanvas((EatCommand)commands.getLastCommand());
			} else if (commands.getLastCommand() instanceof RoqueCommand) {
				gp = prepareRoqueCanvas((RoqueCommand)commands.getLastCommand());
			} else if (commands.getLastCommand() instanceof PromouvoirCommand){
				gp = preparePromouvoirCanvas((PromouvoirCommand)commands.getLastCommand());
			}
			
			
		}
		
		vb.getChildren().add(gp);
	}

	
	private Group initClock(boolean estNoir) {
		Group whiteGroup = new Group();
		Canvas canvasPrincipal = new Canvas(300,300);
		mainFont = Font.loadFont(getClass().getResourceAsStream("/ttf/technology.ttf"),42);
		
	
		Canvas interrupteur = new Canvas(64,48);
		Canvas canvasTimer = new Canvas(canvasPrincipal.getWidth(), canvasPrincipal.getHeight()-interrupteur.getHeight());
		canvasTimer.setTranslateY(interrupteur.getHeight());
		canvasTimer.setTranslateY(interrupteur.getHeight());
		Canvas canvasTimerCouleur = new Canvas(canvasTimer.getWidth(), canvasTimer.getHeight());
		canvasTimerCouleur.setTranslateX(canvasTimer.getTranslateX());
		canvasTimerCouleur.setTranslateY(canvasTimer.getTranslateY());
		Image imageBois = new Image(getClass().getResourceAsStream("/img/textureMetal.jpg"));
		GraphicsContext gc_timer = canvasTimer.getGraphicsContext2D();
		gc_timer.drawImage(imageBois, 0, 0, imageBois.getWidth(), imageBois.getHeight(),
				0,0,canvasTimer.getWidth(), canvasTimer.getHeight());
		
		GraphicsContext gc_timerCouleur = canvasTimerCouleur.getGraphicsContext2D();
		Paint tmpColor = (estNoir ? Color.rgb(0, 0, 0,0.6) : Color.rgb(255, 255, 255,0.2));
		gc_timerCouleur.setFill(tmpColor);
		gc_timerCouleur.fillRect(0, 0, canvasTimerCouleur.getWidth(), canvasTimerCouleur.getHeight());
		
		interrupteur.setId((estNoir ?  "#tigeNoir" : "#tigeBlanc"));
		interrupteur.setTranslateX(canvasPrincipal.getWidth()/2-interrupteur.getWidth()/2);
		
		GraphicsContext gc_interrupteurBlanc = interrupteur.getGraphicsContext2D();
		gc_interrupteurBlanc.drawImage(imageTige, (estNoir ? 0:64), 0, 64,64,
				0,0,interrupteur.getWidth(), interrupteur.getHeight());


		Text tmp = new Text();
		tmp.setFont(mainFont);
		tmp.setText("40:00.00");
		tmp.setBoundsType(TextBoundsType.LOGICAL);
		Bounds bnd = tmp.getLayoutBounds();
		
		Canvas whiteTimerCanvas = new Canvas(bnd.getWidth()+10,
				bnd.getHeight()/2+10);
		
		GraphicsContext wtcContext = whiteTimerCanvas.getGraphicsContext2D();
		wtcContext.setFill(Color.rgb(64,64,64));
		wtcContext.fillRect(0,0,whiteTimerCanvas.getWidth(),whiteTimerCanvas.getHeight());
		wtcContext.setFont(mainFont);
		wtcContext.setFill(Color.CYAN);
		wtcContext.fillText("40:00.00",0,whiteTimerCanvas.getHeight());
		
		whiteTimerCanvas.setTranslateX(canvasTimer.getTranslateX() + (canvasTimer.getWidth()/2 - whiteTimerCanvas.getWidth()/2));
		whiteTimerCanvas.setTranslateY(canvasTimer.getTranslateY() + whiteTimerCanvas.getHeight());
		
		whiteGroup.getChildren().add(canvasPrincipal);
		whiteGroup.getChildren().add(canvasTimer);
		whiteGroup.getChildren().add(canvasTimerCouleur);
		whiteGroup.getChildren().add(whiteTimerCanvas);
		whiteGroup.getChildren().add(interrupteur);
		return whiteGroup;
	}
	

	
	public void initGrid() {
		grid = new GridPane();
		grid.setPrefSize(1200,800);
		grid.setMinSize(1200,800);
		//grid.setGridLinesVisible(true);
		initGridConstraint(grid);
		
		imageTige = new Image(getClass().getResourceAsStream("/img/clockTige.png"));
		Group group = new Group();
		group.setId("#echiquier");
		grid.add(group,1,0,2,1);
		HBox boxBlanc = new HBox();
		boxBlanc.setMinSize(300,200);
		boxBlanc.setMaxSize(300, 200);
		boxBlanc.setId("#whiteTimer");
		HBox boxNoir = new HBox();
		boxNoir.setMinSize(300,200);
		boxNoir.setMaxSize(300, 200);
		boxNoir.setId("#blackTimer");
		grid.add(boxBlanc, 1, 1);
		grid.add(boxNoir, 2,1,1,1);
		Group whiteGroup = initClock(false);
		Group blackGroup = initClock(true);
		initHistory();
		boxBlanc.getChildren().add(whiteGroup);
		boxNoir.getChildren().add(blackGroup);
	}
	
	
	public Jeu(String nomJ1, String nomJ2) {
		//timersBox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		commands = new CommandManager();
		plateauActuel = new Plateau((byte)8,(byte)8);
		participants = new Joueur[2];
		participants[0]=new Joueur(nomJ1,false);
		participants[1]=new Joueur(nomJ2,true);
		joueurActuel = participants[0];
		initGrid();
		lastPlateau = plateauActuel.verifierPlateau();
		soundManager = new SoundManager();
		timeManager = new Thread(new TimeManager(this));
		initSound();
		
		timeManager.start();
	}
	
	@Override
	public void finalize() {
		timeManager.interrupt();
		timeManager.stop();
	}
	
	public Jeu(Plateau _plateauActuel,Joueur _participants[], Joueur _joueurActuel, CommandManager _commands,
			   GAMESTATUS _lastPlateau) {
		commands = _commands;
		plateauActuel = _plateauActuel;
		participants = _participants;
		joueurActuel = _joueurActuel;
		lastPlateau = _lastPlateau;

		soundManager = new SoundManager();
		timeManager = new Thread(new TimeManager(this));
		initSound();
		timeManager.start();
	}
	
	public void changerJoueur() {
		if (joueurActuel == participants[0]) {
			joueurActuel = participants[1];
		} else {
			joueurActuel = participants[0];
		}
		
		Canvas tigeNoir = (Canvas)((Group)((HBox)getNodeById("#blackTimer")).getChildren().get(0)).getChildren().get(4);
		Canvas tigeBlanc =  (Canvas)((Group)((HBox)getNodeById("#whiteTimer")).getChildren().get(0)).getChildren().get(4);
		if (tigeNoir != null && tigeBlanc != null) {
			GraphicsContext gcb = tigeNoir.getGraphicsContext2D();
			GraphicsContext gcw = tigeBlanc.getGraphicsContext2D();
			gcb.clearRect(0, 0, tigeNoir.getWidth(), tigeNoir.getHeight());
			gcb.drawImage(imageTige, (joueurActuel.estNoir() ? 64 : 0), 0, 64,64,
					0,0,tigeNoir.getWidth(), tigeNoir.getHeight());
			gcw.clearRect(0, 0, tigeBlanc.getWidth(), tigeBlanc.getHeight());
			gcw.drawImage(imageTige, (joueurActuel.estNoir() ? 0 : 64), 0, 64,64,
					0,0,tigeBlanc.getWidth(), tigeBlanc.getHeight());
		}
		
	}

	public void dessiner(Group g){
		long deb = Utils.getEllapsedTimeInMs();
		dessiner();
		
		Bounds groupBounds = g.getBoundsInParent();
		Bounds thisBounds = grid.getLayoutBounds();

		g.getChildren().add(grid);

	}

	public void dessinerTimer(String str) {
		HBox timer = (HBox)getNodeById((joueurActuel.estNoir() ? "#blackTimer" : "#whiteTimer"));
		if (timer != null) {
			if (timer.getChildren().size() == 1) {
				Canvas getText = (Canvas)((Group)timer.getChildren().get(0)).getChildren().get(3);
				GraphicsContext gc = getText.getGraphicsContext2D();
				gc.clearRect(0, 0, getText.getWidth(), getText.getHeight());
				gc.setFill(Color.rgb(64,64,64));
				gc.fillRect(0,0,getText.getWidth(),getText.getHeight());
				gc.setFill(Color.CYAN);
				gc.setFont(mainFont);
				gc.fillText(str, 0,getText.getHeight());
			}
		}
		
	}

	public void dessiner(){



		//gameBox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		/*Canvas c = new Canvas(plateauActuel.getWidth() * Piece.getImageWidth(),
				plateauActuel.getWidth() * Piece.getImageHeight());*/
		
		
		Group gp = (Group)getNodeById("#echiquier");
		if (gp != null) {
			grid.getChildren().remove(gp);
		}

		gp = new Group();
		gp.setId("#echiquier");
		plateauActuel.dessiner(gp);
		
		grid.add(gp,1,0,2,1);

	}
	
	/*public void setTimer(boolean estNoir, String text) {
		timers[(estNoir ? 1:0)].setText(text);
	}*/
	
	
	public void initSound() {
		soundManager.addSound("/sfx/onEat.mp3", "onEat");
		soundManager.addSound("/sfx/onEat.mp3", "onKingEat");
		soundManager.addSound("/sfx/onMove.mp3", "onMove");
		soundManager.addSound("/sfx/queenEatQueen.mp3", "queenEatQueen");
		soundManager.addSound("/sfx/onEat.mp3", "onQueenEaten");
		soundManager.addSound("/sfx/onRoque.mp3", "onRoque");
		soundManager.addSound("/sfx/onEat.mp3", "onEat");
		soundManager.addSound("/sfx/beep.wav", "timeEnd");
	}
	
	public Node getNodeById(String str) {
		int i, j;
		i = 0;
		j = 0;
		Node rt=null;
		
		while (i<grid.getChildren().size() && rt == null) {
			String compare =grid.getChildren().get(i).getId();
			if (compare!= null && str.equals(compare)) {
				rt = grid.getChildren().get(i);
			}
			i++;
		}
		return rt;
		
	}
	
	public void setOnEchec(GAMESTATUS gs) {
		lastPlateau = gs;
		soundManager.playSoundByName("timeEnd");
		if (lastPlateau == GAMESTATUS.BLACK_CHECKMATE) {
			dessinerNoir();
		} else if (lastPlateau == GAMESTATUS.WHITE_CHECKMATE) {
			dessinerBlanc();
		}
			
		timeManager.interrupt();
		timeManager.stop();
		
	}
	
	

	@Override
	public void handle(MouseEvent mouseEvent) {
		Node n =((Group)getNodeById("#echiquier")).getChildren().get(0);
		boolean moved = false;
		long timeTotal, timeDebut, timeEnd;
		Piece eatenOne = null;
		timeDebut = Utils.getEllapsedTimeInMs();

		if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED) {
			timeEnd = Utils.getEllapsedTimeInMs()-timeDebut;



			if (lastPlateau != GAMESTATUS.BLACK_CHECKMATE &&
					lastPlateau != GAMESTATUS.WHITE_CHECKMATE) {
				double mouseX = mouseEvent.getX();
				double mouseY = mouseEvent.getY();
				
				Bounds boxlocal = n.getBoundsInParent();
				Bounds boxParent = n.getParent().getBoundsInParent();
				Bounds localBox = n.getBoundsInLocal();
				/*double[] pos = {boxParent.getMinX()+boxlocal.getMinX(),
						boxParent.getMinY()+boxlocal.getMinY(),
						boxParent.getMinX()+boxlocal.getMinX()+boxlocal.getWidth(),
						boxParent.getMinY()+boxlocal.getMinY()+boxlocal.getHeight()
				};*/
				double pos[] = Utils.getAbsoluteCoord(n);
				if (mouseX > pos[0] &&
						mouseX <= pos[2]&&
						mouseY > pos[1] &&
						mouseY <= pos[3]) {
					Piece promu;
					Mouvement mv = null;
					promu = plateauActuel.peutEtrePromu();
					mouseX -= pos[0];
					mouseY -= pos[1];
					Coordonees tmp = new Coordonees((byte) (mouseX / Piece.getImageWidth()), (byte) (mouseY / Piece.getImageHeight()));
					Piece tmpPiece = plateauActuel.getPieceFromCoord(tmp);
					if (promu == null) {
						if (selected == null) {

							if (tmpPiece != null && tmpPiece.estNoir() == joueurActuel.estNoir()) {
								plateauActuel.selectionner(tmpPiece);
								selected = tmpPiece;
							}
						} else {
							if (tmpPiece != null && tmpPiece.estNoir() == joueurActuel.estNoir()) {
								if (selected instanceof Piece_Roi &&
									tmpPiece instanceof Piece_Tour){
									ArrayList<Mouvement> roiMove = selected.calculerMouvement(plateauActuel);
									
									int i=0;
									while (mv==null && i< roiMove.size()){
										if (roiMove.get(i).getTo().equals(tmpPiece.getPosition()))
											mv = roiMove.get(i);
										i++;
									}
									if (mv != null){
										moved = commands.executeCommand(new RoqueCommand(plateauActuel, new Mouvement(mv), joueurActuel.estNoir()));
										addMove();
									} else {
										plateauActuel.selectionner(tmpPiece);
										selected = tmpPiece;
									}
								} else {
									plateauActuel.selectionner(tmpPiece);
									selected = tmpPiece;
								}
							} else {
								ArrayList<Mouvement> moves = selected.calculerMouvement(plateauActuel);
								if (selected instanceof Piece_Pion) {
									moves.addAll(((Piece_Pion) selected).calculerMouvementManger(plateauActuel));
								}
								
								int i = 0;
								while (mv == null && i < moves.size()) {
									if (moves.get(i).getTo().equals(tmp)) {
										mv = moves.get(i);
									}
									i++;
								}
								if (mv != null) {
									if (mv.getType().equals("eat")) {
										try {
											eatenOne = (Piece)plateauActuel.getPieceFromCoord(mv.getTo()).clone();
										} catch (CloneNotSupportedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										moved = commands.executeCommand(new EatCommand(plateauActuel, new Mouvement(mv), joueurActuel.estNoir(),eatenOne));
										addMove();
									} else {
										moved = commands.executeCommand(new MoveCommand(plateauActuel, new Mouvement(mv), joueurActuel.estNoir()));
										addMove();
									}
								}
								moves.clear();
							}

						}

						if (moved) {
							
							if (mv.getType().equals("move")) {
								soundManager.playSoundByName("onMove");							
							} else if (mv.getType().equals("eat")){
								if (selected instanceof Piece_Roi) {
									soundManager.playSoundByName("onKingEat");		
								} else if (selected instanceof Piece_Reine) {
									if (eatenOne != null && eatenOne instanceof Piece_Reine)
										soundManager.playSoundByName("queenEatQueen");		
									else
										soundManager.playSoundByName("onEat");		
								} else {
									if (eatenOne != null && eatenOne instanceof Piece_Reine)
										soundManager.playSoundByName("onQueenEaten");
									else
										soundManager.playSoundByName("onEat");		
								}
							} else if (mv.getType().equals("roque")) {
								soundManager.playSoundByName("onRoque");		
							}
							lastPlateau = plateauActuel.verifierPlateau(!joueurActuel.estNoir());
							dessiner();
							if (lastPlateau == GAMESTATUS.BLACK_CHECKMATE) {
								dessinerBlanc();
								timeManager.interrupt();
								timeManager.stop();
							} else if (lastPlateau == GAMESTATUS.WHITE_CHECKMATE) {
								dessinerNoir();
								timeManager.interrupt();
								timeManager.stop();
							} else {
								if (plateauActuel.peutEtrePromu() != null) {
									dessinerPromouvoir();
								} else {
									selected = null;
									changerJoueur();
								}
							}
							
						}
					} else {
						gererPromotion(tmp);
					}
				}
			}
		}
		timeTotal = Utils.getEllapsedTimeInMs()-timeDebut;
	}

	public Joueur getJoueurActuel() {
		return joueurActuel;
	}
	
	private boolean gererPromotion(Coordonees c){
		boolean rt = false;
		if (c.getY() == 4){
			if (c.getX() == 2){
				rt = commands.executeCommand(new PromouvoirCommand(
						plateauActuel,
						selected,
						(byte)0));
			} else if (c.getX() == 3){
				rt = commands.executeCommand(new PromouvoirCommand(
						plateauActuel,
						selected,
						(byte)1));
			} else if (c.getX() == 4){
				rt = commands.executeCommand(new PromouvoirCommand(
						plateauActuel,
						selected,
						(byte)2));
			} else if (c.getX() == 5){
				rt = commands.executeCommand(new PromouvoirCommand(
						plateauActuel,
						selected,
						(byte)3));
			}
		}
		if (rt){
			addMove();
			dessiner();
			changerJoueur();
		}
		return rt;
	}

	public void dessinerBlanc(){
		
		Canvas canvas = new Canvas(plateauActuel.getWidth() * Piece.getImageWidth(),
				plateauActuel.getWidth() * Piece.getImageHeight());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Group gp = (Group)getNodeById("#echiquier");
		Canvas tmp = ((Canvas)gp.getChildren().get(5));
		tmp.setOpacity(1);


	}

	public void dessinerPromouvoir(){
		//gameBox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		Canvas c = new Canvas(plateauActuel.getWidth() * Piece.getImageWidth(),
				plateauActuel.getWidth() * Piece.getImageHeight());

		
		Group gp = new Group();
		gp.setId("#echiquier");
		plateauActuel.dessinerPromotion(joueurActuel.estNoir(),gp);
		grid.getChildren().remove(getNodeById("#echiquier"));
		grid.add(gp,1,0,2,1);
	}

	public void dessinerNoir(){
		
		Canvas canvas = new Canvas(plateauActuel.getWidth() * Piece.getImageWidth(),
				plateauActuel.getWidth() * Piece.getImageHeight());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Group gp = (Group)getNodeById("#echiquier");
		Canvas tmp = ((Canvas)gp.getChildren().get(4));
		tmp.setOpacity(1);


	}
	
	
	
}
