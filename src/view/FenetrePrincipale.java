package view;

import java.awt.Dimension;

import javax.swing.JFrame;

import model.Direction;
import controller.Partie;

public class FenetrePrincipale extends JFrame {

	public FenetrePrincipale() {
		super("Taquin");
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		FenetrePrincipale fenetre = new FenetrePrincipale();
		
		Partie partie = new Partie();
		System.out.println(partie.getGrille());
		
		partie.nouvelle();
		System.out.println(partie.getGrille());
		
		try {
			partie.deplacer(Direction.DROITE);
			System.out.println(partie.getGrille());
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		partie.arreter();
		System.out.println(partie.getGrille());
	}
}
