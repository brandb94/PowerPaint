/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package tools;

import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * 
 * The interface with all common method stubs for tools.
 * @author Brandon Bell
 * @version 6 May 2014
 *
 */
public interface Tool {

    /**
     * Gets the shape object of the current tool.
     * Line2D, for example. 
     * 
     * @return the shape that the tool represents. 
     */
    Shape getShape();
    
    /**
     * Gets the start point of the shape.
     * @return the starting point.
     */
    Point2D getStartPoint();
    
    /**
     * Gets the end point of the shape.
     * 
     * @return the end point. 
     */
    Point2D getEndPoint(); 
        
    /**
     * Sets the start point.
     *
     * @param thePoint the new start point
     */
    void setStartPoint(Point2D thePoint);
    
    /**
     * Sets the end point of the shape.
     *
     * @param theEndPoint the new end point
     */
    void setEndPoint(Point2D theEndPoint);
    
    

    
}
