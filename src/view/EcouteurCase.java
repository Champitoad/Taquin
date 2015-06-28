package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import model.Direction;
import model.Vector;
import controller.Partie;

public class EcouteurCase implements ActionListener {

	private int i;
	private int j;
	private Partie partie;
	private FenetrePrincipale parent;
	
	public EcouteurCase(int v, int w,Partie partie, FenetrePrincipale parent){
		this.i = v;
		this.j = w;
		this.partie = partie;
		this.parent = parent;

	}
	public void actionPerformed(ActionEvent arg0) {
		
		Vector caseVide = partie.getGrille().getCaseVide();

		if(caseVide.x == i && caseVide.y == j - 1){
			this.partie.deplacer(Direction.HAUT);
			parent.updateTabCases();
		}
		if(caseVide.x == i && caseVide.y == j + 1){
			this.partie.deplacer(Direction.BAS);
			parent.updateTabCases();
		}
		if(caseVide.x == i - 1 && caseVide.y == j){
			this.partie.deplacer(Direction.GAUCHE);
			parent.updateTabCases();
		}
		if(caseVide.x == i + 1 && caseVide.y == j){
			this.partie.deplacer(Direction.DROITE);
			parent.updateTabCases();
		}
		parent.fin();
		
	}

}
