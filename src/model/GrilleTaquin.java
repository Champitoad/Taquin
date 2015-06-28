package model;

import java.util.Random;

public class GrilleTaquin
{
	private final Random RND = new Random();
	private Direction[] memCoup;
	
	private int[][] matrice;
	private Vector caseVide;
	
	private PileCoups pile;
	
	public GrilleTaquin()
	{
		matrice = new int[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				matrice[i][j] = 4 * i + j + 1;
			}
		}
		matrice[3][3] = 0;
		caseVide = new Vector(3);
	}
	
	public int[][] getMatrice()
	{
		return matrice;
	}
	
	public Vector getCaseVide(){
		return caseVide;
	}
	
	public PileCoups getPileCoups(){
		return pile;
	}
	
	public void deplacer(Direction direction) throws ArrayIndexOutOfBoundsException
	{
		Vector caseADeplacer = new Vector(caseVide.x, caseVide.y);
		
		switch(direction) {
		case HAUT:
			caseADeplacer.y++;
			break;
		case GAUCHE:
			caseADeplacer.x++;
			break;
		case BAS:
			caseADeplacer.y--;
			break;
		case DROITE:
			caseADeplacer.x--;
			break;
		}
		
		try {
			int tmp = matrice[caseADeplacer.y][caseADeplacer.x];
			matrice[caseADeplacer.y][caseADeplacer.x] = matrice[caseVide.y][caseVide.x];
			matrice[caseVide.y][caseVide.x] = tmp;
			caseVide = caseADeplacer;
		} catch(ArrayIndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	public void melanger(int nbMelanges)
	{
		Direction direction;
		Direction[] directions = Direction.class.getEnumConstants();
		
		memCoup = new Direction [nbMelanges];
		int i = 0;
		while(i < nbMelanges) {
			direction = directions[RND.nextInt(directions.length)];
			try {
				memCoup[i] = direction;
				deplacer(direction);
				
				i++;
			} catch(ArrayIndexOutOfBoundsException e) {}
		}
		
		pile = new PileCoups(memCoup);
		
	}
	
	public String toString()
	{
		String str = "";
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				str += matrice[i][j] + "\t";
			}
			str += "\n";
		}
		
		return str;
	}
}
