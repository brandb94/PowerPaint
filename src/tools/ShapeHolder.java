/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package tools;

import java.awt.Color;
import java.awt.Shape;

/**
 * The Class ShapeHolder.
 * @author Brandon Bell
 * @version 9 May 2014
 */
public class ShapeHolder {
    
    /** The Shape object that this shapeHolder contains. */
    private final Shape myShape;
    
    /** the Color of the shape. */
    private final Color myColor;
    
    /** The line thickness of the shape. */
    private final int myThickness;
    
    
    /**
     * Instantiates a new shape holder.
     *
     * @param theShape the shape that this object holds
     * @param theColor the color of the shape
     * @param theThickness of the shape.
     */
    public ShapeHolder(final Shape theShape, final Color theColor, final int theThickness) {
        super();
        myShape = theShape;
        myColor = theColor;
        myThickness = theThickness;
    }
    
    /**
     *Used to obtain the Shape object.
     *
     * @return the shape object
     */
    public Shape getShape() {
        return myShape;
    }
    
    
    /**
     * Gets the color of the shape.
     *
     * @return the color of the shape
     */
    public Color getColor() {
        return myColor;
    }
    /**
     * Gets the line thickness of the shape.
     * @return the integer representing the stroke width.
     */
    public int getThickness() {
        return myThickness;
    }
    

}
