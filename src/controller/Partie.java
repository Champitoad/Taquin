package controller;

import model.*;

public class Partie
{
	private GrilleTaquin grille;
	
	public Partie()
	{
		grille = new GrilleTaquin();
	}
	
	public GrilleTaquin getGrille()
	{
		return grille;
	}
	
	public void nouvelle()
	{
		grille.melanger(40);
	}
	
	public void arreter()
	{
		grille = new GrilleTaquin();
	}
	
	public void deplacer(Direction direction)
	{
		grille.deplacer(direction);
	}
}
