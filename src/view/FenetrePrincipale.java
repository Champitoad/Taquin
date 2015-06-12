package view;

import java.awt.Dimension;
import javax.swing.JFrame;

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
	}

}
