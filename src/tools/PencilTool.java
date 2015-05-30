/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package tools;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


/**
 * The Pencil tool, used to draw freeform lines in the drawing panel.
 * @author Brandon Bell
 * @version 19 May 2014
 */
public class PencilTool extends AbstractTool {
    
    /** The object used to create the freeform lines. */
    private GeneralPath myGeneral;

    
    /**
     * Instantiates a new pencil tool.
     *
     * @param theStartPoint the the start point
     * @param theEndPoint the the end point
     */
    public PencilTool(final Point2D theStartPoint, final Point2D theEndPoint) {
                     
        super(theStartPoint, theEndPoint);
        myGeneral = new GeneralPath();
   
    }
    
    /**
     * Zero argument constructor for the pencil tool.
     *
     */
    public PencilTool() {
        super(null, null);
        myGeneral = new GeneralPath();
    }
    
    /** {@inheritDoc}
     * Overridden because the pencil needs to be re-instantiated when
     * it is moved to a new point. 
     */
    @Override
    public void setStartPoint(final Point2D thePoint) {
        if (thePoint != null) {
            super.setStartPoint(thePoint);
        }
        myGeneral = new GeneralPath();
        if (thePoint != null) {
            myGeneral.moveTo(thePoint.getX(), thePoint.getY()); //= thePoint;

        }

    }
    /**
     *  {@inheritDoc}
     *  Overridden because pencilTool cannot simply return
     *  a new general path given two points.
     */
    @Override
    public void setEndPoint(final Point2D theEndPoint) {
        super.setEndPoint(theEndPoint);
       
        if (theEndPoint != null) {
            myGeneral.lineTo(theEndPoint.getX(), theEndPoint.getY());

        }
        
    }
    
    /** {@inheritDoc} */

    @Override
    public Shape getShape() {
        return new GeneralPath(myGeneral);
    }


   
}
