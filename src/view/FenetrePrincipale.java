package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.Direction;
import controller.Partie;

public class FenetrePrincipale extends JFrame {
	
	private Partie partie;
	private ChoixCouleur choixCouleur;
	private ChoixImage choixImage;
	private Regles regles;

	public FenetrePrincipale() {
		super("Taquin");
		partie = new Partie();
		choixCouleur = new ChoixCouleur("Choix couleur");
		choixImage = new ChoixImage("Choix image");
		regles = new Regles("Règles");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(contPane());
		setJMenuBar(menuBar());
		pack();
		setVisible(true);
	}
	
	public JPanel contPane() {
		JPanel contPane = new JPanel();
		contPane.setPreferredSize(new Dimension(500, 500));
		return contPane;
	}
	
	public JMenuBar menuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuPartie = new JMenu("Partie");
		JMenu menuParametre = new JMenu("Paramètre");
		JMenu menuAide = new JMenu("Aide");
		
		menuBar.add(menuPartie);
		menuBar.add(menuParametre);
		menuBar.add(menuAide);
		
		JMenuItem itemNouvellePartie = new JMenuItem("Nouvelle ..");
		JMenuItem itemArreterPartie = new JMenuItem("Arrêter ..");
		
		itemArreterPartie.setEnabled(false);
		
		itemNouvellePartie.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				partie.nouvelle();
				itemArreterPartie.setEnabled(true);
				itemNouvellePartie.setEnabled(false);
	        }
			
		});
		
		itemArreterPartie.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				partie.arreter();
				itemArreterPartie.setEnabled(false);
				itemNouvellePartie.setEnabled(true);
	        }
			
		});
		
		menuPartie.add(itemNouvellePartie);
		menuPartie.add(itemArreterPartie);
		
		JMenuItem itemCouleurParametre = new JMenuItem("Couleur");
		JMenuItem itemImageParametre = new JMenuItem("Image");
		
		itemCouleurParametre.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				choixCouleur.setVisible(true);
	        }
		});
		
		itemImageParametre.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				choixImage.setVisible(true);
	        }
		});
		
		menuParametre.add(itemCouleurParametre);
		menuParametre.add(itemImageParametre);
		
		JMenuItem itemRegleAide = new JMenuItem("Règles");
		JMenuItem itemCoupSuivantAide = new JMenuItem("Coup Suivant");
		JMenuItem itemSolutionAide = new JMenuItem("Solution");
		
		itemRegleAide.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				regles.setVisible(true);
	        }
		});
		
		itemCoupSuivantAide.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				aideCoupSuivant();
	        }
		});
		
		itemSolutionAide.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				solution();
	        }
		});
		
		menuAide.add(itemRegleAide);
		menuAide.add(itemCoupSuivantAide);
		menuAide.add(itemSolutionAide);
		
		return menuBar;
	}
	
	private void aideCoupSuivant(){
		
	}
	
	private void solution() {
		
	}

	public static void main(String[] args) {
		FenetrePrincipale fenetre = new FenetrePrincipale();
		/*
		Partie partie = new Partie();
		System.out.println(partie.getGrille());
		
		partie.nouvelle();
		System.out.println(partie.getGrille());
		
		try {
			partie.deplacer(Direction.DROITE);
			System.out.println(partie.getGrille());
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		partie.arreter();
		System.out.println(partie.getGrille());*/
	}
}
