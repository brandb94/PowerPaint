/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package actions;

import gui.ColorIcon;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
//import javax.swing.Icon;
import javax.swing.JColorChooser;

/**
 * Handles the selection of colors. 
 * 
 * @author Brandon Bell
 * @version 8 May 2014
 *
 */
@SuppressWarnings("serial")
public class ColorAction extends AbstractAction {


    /** The Current Color.*/
    private Color myColor; 


    /**
     * Instantiates a new color action.
     *
     */
    public ColorAction() { 
        super("Color...");
        putValue(Action.SMALL_ICON, new ColorIcon(Color.black));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);

        myColor = Color.black;
    }


    /** {@inheritDoc} */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {

        final Color temp = JColorChooser.showDialog(null, "A Color Chooser", myColor);
        //avoid setting myColor to null
        if (temp != null) {
            myColor = temp;
        } 


        putValue(Action.SMALL_ICON, new ColorIcon(myColor));

        firePropertyChange("color", Color.black, myColor);


    }

}
