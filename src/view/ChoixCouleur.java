package view;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ChoixCouleur extends JDialog {

    public ChoixCouleur(JFrame owner, String titre) {
    	super(owner, titre);
    	JPanel panneau = new JPanel();
 
    	pack();
    }
}

