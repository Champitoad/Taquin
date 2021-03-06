package view;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class Regles extends JDialog{
	public Regles(JFrame owner, String titre){
		super(owner, titre);

    	JPanel main = new JPanel();
    	main.setLayout(new BorderLayout());
    	main.setPreferredSize(new Dimension(300,200));
    	
    	JLabel regles = new JLabel("<html>Il y a un emplacement vide, ce qui permet de déplacer un bloc en le faisant glisser. <br> Il vous suffit de <b>cliquer</b> sur un bloc pour qu'il prenne la place de l'emplacement vide. <br> Les blocs sont tout d'abord mélangés, et <b>la partie est gagnée quand la disposition initiale est atteinte.</b></html>", SwingConstants.CENTER);
    	Border paddingBorder = BorderFactory.createEmptyBorder(20,20,20,20);
    	regles.setBorder(paddingBorder);
    	
    	main.add(regles, BorderLayout.CENTER);
    	
    	JButton ok = new JButton("ok");
    	
    	ok.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
    		
    	});
    	main.add(ok, BorderLayout.SOUTH);
    	add(main);
    	pack();
	 }
	 
}
