package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * An ImageView wrapper that handles additional parameterization
 */
public class IconView extends ImageView {

    /**
     * Constructor for IconView, including all common mutators
     * @param image         Image to be anchored on the top left corner
     * @param x             x-coordinate of the top left corner
     * @param y             y-coordinate of the top left corner
     * @param width         width of the image
     * @param height        height of the image
     * @param preservation  whether or not to preserve the ratio of the original image
     */
    public IconView(Image image, double x, double y, double width, double height, boolean preservation) {
        super(image);
        this.setX(x);
        this.setY(y);
        this.setFitWidth(width);
        this.setFitHeight(height);
        this.setPreserveRatio(preservation);
    }
}
