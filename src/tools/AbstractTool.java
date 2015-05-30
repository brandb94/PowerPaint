/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package tools;

import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * The parent class to all of the Tool child objects.
 * @author Brandon Bell
 * @version 19 May 2014
 */
public abstract class AbstractTool implements Tool {

    /** The my start point. */
    private Point2D myStartPoint;
    
    /** The my end point. */
    private Point2D myEndPoint; 
  
    
    /**
     * Parent constructor for all tool subclasses.
     * Instantiates all fields.
     * Note: only ever passed null values.
     *
     * @param theStartPoint the start point of the tool
     * @param theEndPoint the end point of the tool
     */
    protected AbstractTool(final Point2D theStartPoint, final Point2D theEndPoint) {
        if (theStartPoint != null && theEndPoint != null) {
            myStartPoint = (Point2D) theStartPoint.clone();
            myEndPoint = (Point2D) theEndPoint.clone(); 
        }
    }
    
    
    /** {@inheritDoc} */

    @Override
    public Point2D getStartPoint() {
        Point2D result;
        
        //these if/else tests were added to avoid calling clone on null
        //myStartPoint is unassigned

        if (myStartPoint == null) {
            result = myStartPoint; //set result to null

            
        //myStartPoint has already been assigned
        } else {
            result = (Point2D) myStartPoint.clone();

        }
        return result;
        
        
        

        
    }

    /** {@inheritDoc} */
    @Override
    public Point2D getEndPoint() {
        Point2D result;
        
        //see comments on getStartPoint for logic;
        if (myEndPoint == null) {
            result = myEndPoint;
        } else {
            result = (Point2D) myEndPoint.clone();

        }
        return result;
    }
    
   
    
    /** {@inheritDoc} */
    public abstract Shape getShape();
    

    /** {@inheritDoc} */
    @Override
    public void setStartPoint(final Point2D thePoint) {
        if (thePoint == null) {
            myStartPoint = thePoint;

        } else {
            myStartPoint = (Point2D) thePoint.clone();

        }
    }

    /** {@inheritDoc} */
    @Override
    public void setEndPoint(final Point2D theEndPoint) {
        if (theEndPoint == null) {
            //assign the point to null, handled in drawing panel
            myEndPoint = theEndPoint;

        } else {
            myEndPoint = (Point2D) theEndPoint.clone();

        }
        
    }
   




}
