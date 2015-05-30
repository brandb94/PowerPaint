/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package actions;

import gui.DrawingArea;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * The action for the undo button.
 * @author Brandon Bell
 * @version 16 May 2014
 *
 */
@SuppressWarnings("serial")
public class UndoAction extends AbstractAction {

    /** The Drawing Panel affected by this action. */
    private final DrawingArea myCanvas;

    /**
     * Creates an action for the undo button.
     *
     * @param theCanvas the Drawing Panel this action affects. 
     */
    public UndoAction(final DrawingArea theCanvas) {
        super("Undo");
        myCanvas = theCanvas;
    }
    /** {@inheritDoc}
     *  Calls the undo method from the drawing panel.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myCanvas.undo();

    }

}
