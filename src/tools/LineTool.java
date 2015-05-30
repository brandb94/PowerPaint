/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package tools;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


/**
 * The tool used to draw a straight line.
 * @author Brandon Bell
 * @version 6 May 2014 
 */
public class LineTool extends AbstractTool {
    

 
    /**
     * Instantiates a new line tool.
     *
     * @param theStartPoint the the start point
     * @param theEndPoint the the end point
     */
    public LineTool(final Point2D theStartPoint, final Point2D theEndPoint) {
        super(theStartPoint, theEndPoint);
        
    }
    
    /**
     * Zero argument constructor for the line tool.
     *
     */
    public LineTool() {
        super(null, null);
    }
    
    
    
    
    @Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
        
        
    }

   
}
