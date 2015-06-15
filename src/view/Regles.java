package view;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Regles extends JFrame{
	public Regles(String titre){
		super(titre);
		setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panneauDeContenu());
        pack();
	}
	
	 JPanel panneauDeContenu(){

	    	JPanel main = new JPanel();
	    	main.setLayout(new BorderLayout());
	    	main.setPreferredSize(new Dimension(300,200));
	    	
	    	JLabel regles = new JLabel("<html>Il y a un emplacement vide, ce qui permet de déplacer un bloc en le faisant glisser. <br> Il vous suffit de <b>cliquer</b> sur un bloc pour qu'il prenne la place de l'emplacement vide. <br> Les blocs sont tout d'abord mélangés, et <b>la partie est gagnée quand la disposition initiale est atteinte.</b></html>", SwingConstants.CENTER);
	    	Border paddingBorder = BorderFactory.createEmptyBorder(20,20,20,20);
	    	regles.setBorder(paddingBorder);
	    	
	    	main.add(regles, BorderLayout.CENTER);
	    	return main;
	 }
	 
}
