/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package actions;

import gui.DrawingArea;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * The action for the redo button. 
 * @author Brandon Bell
 * @version 16 May 2014
 *
 */
@SuppressWarnings("serial")
public class RedoAction extends AbstractAction {
    
    /** The drawing panel for this action to affect. */
    private final DrawingArea myCanvas;
    
    /**
     * Instantiates a new redo action.
     *
     * @param theCanvas the canvas
     */
    public RedoAction(final DrawingArea theCanvas) {
        super("Redo");
        myCanvas = theCanvas;
        
    }

    
    /** {@inheritDoc} 
     *Simply calls the drawing area's redo method.
     *
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myCanvas.redo();
    }

}
