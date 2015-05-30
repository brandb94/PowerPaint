/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package gui;

import actions.ColorAction;
import actions.RedoAction;
import actions.UndoAction;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;




/**
 * The JToolbar for the PowerPaint frame.
 * 
 * @author Brandon Bell
 * @version 8 May 2014
 *
 */
@SuppressWarnings("serial")
public class ToolBar extends JToolBar implements PropertyChangeListener {
    
    //button name constants
    
    /** Name of the undo button, used for propertyChanges.
     *  made to eliminate warnings. 
     */
    public static final String UNDO = "undo";
    
    /** Name of the undo button, used for propertyChanges.
     *  Made to eliminate warnings. 
     */
    public static final String REDO = "redo";
    
    /** The button group for all of the toolbar buttons. */
    private final ButtonGroup myGroup;
    

    
    /** The action to associate with the color button. */
    private final ColorAction myAction;
    

    
    /** The undo button. */
    private final JButton myUndo;
    
    /** The redo button. */
    private final JButton myRedo;
    
    
    /**
     * Construct the toolbar.
     *
     * @param theAction the action for the color button.
     * @param theCanvas the drawing panel.
     */
    public ToolBar(final ColorAction theAction, final DrawingArea theCanvas) {
        super();
        myGroup = new ButtonGroup();
        myAction = theAction;
        babySetup();
        
        //instantiate buttons using the actions,
        //effectively adding the actions to the action map for the toolbar.
        myUndo = new JButton(new UndoAction(theCanvas));
        myRedo = new JButton(new RedoAction(theCanvas));
        
    }
    
    
    /**
     * Adds the color button to the toolbar.
     */
    private void babySetup() {
        final JButton colors = new JButton(myAction);
        add(colors);
    }

    /**
     * Creates a toggle button for the toolbar.
     *
     * @param theAction to associate with the created togglebutton. 
     * 
     */
    public void createToggleButtons(final Action theAction) {
        final JToggleButton tog = new JToggleButton(theAction);
        myGroup.add(tog);
        add(tog);
    }
    
    /**
     * Creates and adds actions to the undo and redo buttons.
     */
    public void createUndoRedo() {
       
        myUndo.setEnabled(false);
        
        
        myRedo.setEnabled(false);
        addSeparator();
        add(myUndo);
        add(myRedo);
        
//        //Add the keyStrokes for the undo and redo button to the input map, ctrl-z 
//        //and ctrl-y, respectively.
//        //UNDO and REDO are constants containing the strings "undo", and "redo"
//        final InputMap imap = this.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
//        imap.put(KeyStroke.getKeyStroke("ctrl Z"), UNDO);
//        imap.put(KeyStroke.getKeyStroke("ctrl Y"), REDO);
//        
//        //associate the actions located in the actionmap 
//        //(from when the buttons were instantiated)
//        //with the new keys, "undo", and "redo"
//        final ActionMap aMap = this.getActionMap();
//        aMap.put(UNDO, myUndoAction);
       // aMap.put(REDO, myRedoAction);
        
        // no longer need any of the above, as the accelerators for the
         //menubar accomplish the same goal.
        //kept here to demonstrate the ability to add accelerators to JButtons,
        //though convoluted
    }


    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (UNDO.equals(theEvent.getPropertyName())) {
            myUndo.setEnabled((boolean) theEvent.getNewValue());
                
        } else if (REDO.equals(theEvent.getPropertyName())) {
            myRedo.setEnabled((boolean) theEvent.getNewValue());
        }
        
    }
    
    

}
