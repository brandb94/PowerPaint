/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * The class used to create the icon for the color chooser buttons.
 * @author Brandon Bell
 * @version 19 May 2014
 */
public class ColorIcon implements Icon {
    
    /** The height of the icon. */
    private static final int HEIGHT = 15;
    
    /** The width of the icon. */
    private static final int WIDTH = 15;
    
    /** The color of the icon. */
    private final Color myColor;
    
    /**
     * Instantiates a new color icon.
     *
     * @param theColor the color to set the icon to.
     */
    public ColorIcon(final Color theColor) {
        myColor = theColor;
    }

    /** {@inheritDoc} */
    @Override
    public int getIconHeight() {
        return HEIGHT;
    }

    /** {@inheritDoc} */
    @Override
    public int getIconWidth() {
        return WIDTH;
        
    }

    /** {@inheritDoc} */
    @Override
    public void paintIcon(final Component theComp, final Graphics theGraph,
                          final int theX, final int theY) {
        theGraph.setColor(myColor);
        theGraph.fillRect(theX, theY, WIDTH - 1, HEIGHT - 1);
        theGraph.setColor(Color.BLACK);
        theGraph.drawRect(theX, theY, WIDTH - 1, HEIGHT - 1);

    }

}
