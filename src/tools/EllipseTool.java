/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * The tool object used to draw ellipses.
 * @author Brandon Bell
 * @version 7 May 2014
 */
public class EllipseTool extends AbstractTool {
    
    
    /**
     * Instantiates a new ellipse tool.
     *
     * @param theStartPoint start point of the ellipse.
     * @param theEndPoint the end point of the ellipse.
     */
    public EllipseTool(final Point2D theStartPoint, final Point2D theEndPoint) {
                     
        super(theStartPoint, theEndPoint);
    }
    
    /**
     * Zero argument constructor for the ellipse tool.
     *
     */
    public EllipseTool() {
        super(null, null);
    }

    /** {@inheritDoc}
     * returns an Ellipse2D object to be drawn on the panel. 
     */
    @Override
    public Shape getShape() {
        final Ellipse2D.Double copy = new Ellipse2D.Double(getStartPoint().getX(),
                                                               getStartPoint().getY(),
                                                               getWidth(), getHeight());
        copy.setFrameFromDiagonal(getStartPoint(), getEndPoint());
        return copy;
    }
    
    /**
     * Gets the height of the ellipse based off of the second
     * point's distance from the first.
     *
     * @return the height
     */
    private double getHeight() {
        return Math.abs(getStartPoint().getY() - getEndPoint().getY());
    }
    
    /**
     * Gets the width of the ellipse based off of the second point's
     * distance from the first.
     *
     * @return the width
     */
    private double getWidth() {
        return Math.abs(getStartPoint().getX() - getEndPoint().getX());
    }

    

}
