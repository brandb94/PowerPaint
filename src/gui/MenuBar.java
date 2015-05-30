/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package gui;

import actions.ColorAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



/**
 * The JMenuBar for the PowerPoint GUI.
 *
 * @author Brandon Bell
 * @version 19 May 2014
 */
@SuppressWarnings("serial")
public class MenuBar extends JMenuBar implements PropertyChangeListener {
    
    /** The default JSlider thickness. */
    private static final int DEF_THICKNESS = 5;
    
    /** The string for the grid button, created to eliminate repeat 
     * string error. 
     */
    private static final String GRID = "grid";
    
    /** The file menu. */
    private final JMenu myFileMenu;

    /** The options menu. */
    private final JMenu myOptionsMenu;

    /** The tools menu. */
    private final JMenu myToolsMenu;

    /** The help menu. */
    private final JMenu myHelpMenu;
    
    /** The undo button. */
    private final JMenuItem myUndo;
    /** The redo button. */
    private final JMenuItem myRedo;


    /** The button group used to group the tool buttons. */
    private final ButtonGroup myGroup;

    /** The color button. */
    private final JMenuItem myColorButt;


    /**
     * Constructs the menu bar.
     *
     * @param theAction the action that opens up the color chooser.
     */
    public MenuBar(final ColorAction theAction) {
        super();
        myFileMenu = new JMenu("File");
        myOptionsMenu = new JMenu("Options");
        myToolsMenu = new JMenu("Tools");
        myHelpMenu = new JMenu("Help");
        myUndo = new JMenuItem("Undo");
        myRedo = new JMenuItem("Redo");

        myColorButt = new JMenuItem(theAction);
        myGroup = new ButtonGroup();
        setup();

    }

    /**
     * Sets up the components of the frame.
     *
     */
    private void setup() {
        myFileMenu.setMnemonic(KeyEvent.VK_F);
        myOptionsMenu.setMnemonic(KeyEvent.VK_O);
        myToolsMenu.setMnemonic(KeyEvent.VK_T);
        myHelpMenu.setMnemonic(KeyEvent.VK_H);

        createFileMenu();
        createEditMenu();
        createOptionsMenu();
        createToolsMenu();

        final JMenuItem about = new JMenuItem("About...");
        about.setMnemonic(KeyEvent.VK_A);

        about.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final StringBuilder sb = new StringBuilder(40);
                sb.append("TCSS 305 PowerPaint\nSpring 2014");
                JOptionPane.showMessageDialog(null, sb.toString(),
                                              "About",
                                              JOptionPane.INFORMATION_MESSAGE);
            }

        });

        myHelpMenu.add(about);
        add(myHelpMenu);


    }

    /**
     * Creates the contents of the file menu.
     *
     */
    private void createFileMenu() {

        final JMenuItem clear = new JMenuItem("Clear");
        clear.setMnemonic(KeyEvent.VK_C);
        clear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("clear", false, true);
            }
        });


        myFileMenu.add(clear);
        myFileMenu.addSeparator();
        final JMenuItem exit = new JMenuItem("Exit");

        exit.setMnemonic(KeyEvent.VK_X);
        //tell the exit button to inform the frame that it should close
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("closeButton", false, true);

            }
        });
        myFileMenu.add(exit);
        add(myFileMenu);

    }
    
    /**
     * Creates the edit menu (Extra credit).
     *
     */
    private void createEditMenu() {
        final JMenu edit = new JMenu("Edit");
        edit.setMnemonic(KeyEvent.VK_D);
        
        myUndo.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
        myRedo.setAccelerator(KeyStroke.getKeyStroke("ctrl Y"));
        
        //created locally to avoid having to hold a reference to the drawing area
        myUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("callUndo", false, true);
            }
        });
        myRedo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("callRedo", false, true);
            }
        });
        myUndo.setEnabled(false);
        myRedo.setEnabled(false);
        edit.add(myUndo);
        edit.add(myRedo);
        add(edit);
        
    }

    /**
     * Creates the options menu, containing both grid and the thickness slider.
     */
    private void createOptionsMenu() {
      

        final JCheckBoxMenuItem grid = new JCheckBoxMenuItem("Grid");
        
        grid.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final JCheckBoxMenuItem box = (JCheckBoxMenuItem) theEvent.getSource();
                if (box.isSelected()) {
                    firePropertyChange(GRID, false, true);
                } else {
                    firePropertyChange(GRID, true, false);
                }
            }
            
        });

        grid.setMnemonic(KeyEvent.VK_G);

        myOptionsMenu.add(grid);
        final JMenu thickSliderMenu = new JMenu("Thickness");
        thickSliderMenu.setMnemonic(KeyEvent.VK_T);
        final JSlider thickness = createSlider();
        thickSliderMenu.add(thickness);    

        myOptionsMenu.add(thickSliderMenu);



        add(myOptionsMenu);
    }

    /**
     * Creates the thickness JSlider. Made 
     * to reduce the clutter in the createOptionsMenu method.
     *
     * @return the thickness slider
     */
    private JSlider createSlider() {
        final JSlider thickSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 20,
                                                5);
        thickSlider.setMajorTickSpacing(DEF_THICKNESS);
        thickSlider.setMinorTickSpacing(1);
        thickSlider.setPaintLabels(true);
        thickSlider.setPaintTicks(true);

        thickSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final int value = thickSlider.getValue();

                firePropertyChange("thickness", DEF_THICKNESS, value);

            }
        });
        return thickSlider;
    }

    /**
     * Creates the tools menu.
     *
     */
    private void createToolsMenu() {
        myToolsMenu.add(myColorButt);
        myToolsMenu.addSeparator();

        add(myToolsMenu);
    }

    
    /**
     * Used to create all the tool buttons in the tools menu.
     *
     * @param theAction to assign to each button as they are created.
     */
    public void createToolButton(final Action theAction) {
        final JRadioButtonMenuItem button = new JRadioButtonMenuItem(theAction);

        myGroup.add(button);
        myToolsMenu.add(button);
    }
    
    /**
     * {@inheritDoc}
     * enables or disables the undo and redo buttons.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("undo".equalsIgnoreCase(theEvent.getPropertyName())) {
            myUndo.setEnabled((boolean) theEvent.getNewValue());
           
        } else if ("redo".equalsIgnoreCase(theEvent.getPropertyName())) {
            myRedo.setEnabled((boolean) theEvent.getNewValue());
        }
        
    }


}
