package utils;

import java.util.Random;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Utils {
    public static void clipChildren(Region region, double width, double height) {

        final Rectangle outputClip = new Rectangle();
        outputClip.setWidth(width);
        outputClip.setHeight(height);
        region.setClip(outputClip);

        region.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
            outputClip.setWidth(newValue.getWidth());
            outputClip.setHeight(newValue.getHeight());
        });
    }
    
    public static double[] getAbsoluteCoord(Node nd) {
		double[] pos = new double[4];
		pos[0] = nd.getBoundsInLocal().getMinX();
		pos[1] = nd.getBoundsInLocal().getMinX();
		pos[2] = nd.getBoundsInLocal().getWidth();
		pos[3] = nd.getBoundsInLocal().getHeight();
		while (nd.getParent() != null) {
			nd = nd.getParent();
			pos[0]+= nd.getBoundsInParent().getMinX();
			pos[1]+= nd.getBoundsInParent().getMinY();
		}
		pos[2] += pos[0];
		pos[3] += pos[1];
		return pos;
	}



    public static Region createClipped(Rectangle sh, int w, int h) {
        final Pane pane = new Pane(sh);
        //pane.setBorder(createBorder());

        pane.setPrefSize(sh.getX()+ w, sh.getY()+h);

        // clipped children still overwrite Border!
        clipChildren(pane, w , h);
        return pane;
    }

    public static boolean faireDamier(Canvas c, int wdam, int hdam){

        if (c.getWidth()%wdam == 0 &&
                c.getHeight()%hdam == 0) {
            int w = (int) (c.getWidth() / wdam);
            int h = (int) (c.getHeight() / hdam);
            GraphicsContext gc = c.getGraphicsContext2D();
            for (int y = 0; y < w; y++) {
                for (int x = 0; x < h; x++) {
                    if ((x+y)%2==0){
                        gc.setFill(Color.rgb(255,255,255,0.5));
                    } else {
                        gc.setFill(Color.rgb(0,0,0,0.5));
                    }
                    gc.fillRect(x*w,y*h,w,h);
                }
            }
            return true;
        }
        return false;

    }

    public static long getEllapsedTimeInMs(){
        return (long)(System.nanoTime()/1000000);
    }

    public static boolean logicalXOR(boolean a, boolean b){
        return ((a || b) && !(a && b));
    }
    
    
    public static int generateRandom(int min, int max){
    	Random rnd = new Random();
    	return rnd.nextInt(max-min)+min;
    			
    }
}
