/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package tools;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * The tool used to create rectangle objects. 
 * @author Brandon Bell
 * @version 7 May 2014
 */
public class RectangleTool extends AbstractTool {


    /**
     * Instantiates a new rectangle tool.
     *
     * @param theStartPoint the the start point
     * @param theEndPoint the the end point
     */
    public RectangleTool(final Point2D theStartPoint, final Point2D theEndPoint) {
        super(theStartPoint, theEndPoint);
    }
    
    /**
     * Zero argument constructor for the rectangle tool.
     *
     */
    public RectangleTool() {
        super(null, null);
    }

    /** {@inheritDoc} */
    @Override
    public Shape getShape() {
        final Rectangle2D.Double copy = new Rectangle2D.Double(getStartPoint().getX(),
                                                         getEndPoint().getY(),
                                                         getWidth(), getHeight());
        //allow the rectangle to be drawn in any direction
        copy.setFrameFromDiagonal(getStartPoint(), getEndPoint());
        return copy;
    }
    
    /**
     * Gets the height of the rectangle.
     *
     * @return height of the rectangle.
     */
    private double getHeight() {
        return Math.abs(getStartPoint().getY() - getEndPoint().getY());
    }
    
    /**
     * Gets the width of the rectangle.
     *
     * @return width of the rectangle.
     */
    private double getWidth() {
        return Math.abs(getStartPoint().getX() - getEndPoint().getX());
    }

}
