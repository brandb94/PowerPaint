/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package gui;

import java.awt.EventQueue;



/**
 * Runs PowerPaint by instantiating and starting the PowerPainGUI.
 * 
 * @author Brandon Bell
 * @version 19 May 2014
 */
public final class PowerPaintMain {

    /**
     * Private constructor to prevent this class from being instantiated.
     */
    private PowerPaintMain() {
        throw new IllegalStateException(); 
    }
    
    
    
    /**
     * The main method, invokes the PowerPaint GUI.
     *
     * @param theArgs the arguments
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final PowerPaintGUI gui = new PowerPaintGUI();
                gui.start();
            }
        });
    }

}
