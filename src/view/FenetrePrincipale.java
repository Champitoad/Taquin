package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.IOException;
import java.net.URL;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Direction;
import controller.Partie;

public class FenetrePrincipale extends JFrame {
	
	private Partie partie;
	private Regles regles;
	private JButton[] cases = new JButton[16];
	private int imageSize;
	private Image[] imagesCases;
	private Color couleur = Color.BLUE;
	JPanel panneauDeJeu;
	JMenuItem itemNouvellePartie;
	JMenuItem itemArreterPartie;
	JMenuItem itemCoupSuivantAide;
	JMenuItem itemSolutionAide;
	JMenuItem itemImageParametre;
	JMenuItem itemCouleurParametre;
	Timer t;

	public FenetrePrincipale() {
		super("Taquin");
		try { // Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
		    e.getMessage();
		}
		partie = new Partie();
		regles = new Regles(this, "Règles");
		//setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(contPane());
		setJMenuBar(menuBar(this));
		pack();
		setVisible(true);
	}
	
	public JPanel contPane() {
		JPanel contPane = new JPanel();
		contPane.setBackground(Color.WHITE);
		contPane.setLayout(new BorderLayout());
		contPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		panneauDeJeu = new JPanel(new GridLayout(4,4));
		panneauDeJeu.setPreferredSize(new Dimension(400, 400));
		panneauDeJeu.setBackground(Color.WHITE);


		for(int i = 0; i < 16; i++) {

			JButton bouton = new JButton();
			bouton.setFont(new Font("Arial", Font.PLAIN, 20));
			bouton.setBackground(couleur);
			bouton.setOpaque(true);
			bouton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			cases[i] = bouton;
			panneauDeJeu.add(cases[i]);
		}
		
		updateCases(partie.getGrille().getMatrice());
		
		contPane.add(panneauDeJeu, BorderLayout.CENTER);
		return contPane;
	}
	
	public JMenuBar menuBar(final FenetrePrincipale parent) {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuPartie = new JMenu("Partie");
		JMenu menuParametre = new JMenu("Paramètre");
		JMenu menuAide = new JMenu("Aide");
		
		menuBar.add(menuPartie);
		menuBar.add(menuParametre);
		menuBar.add(menuAide);
		
		itemNouvellePartie = new JMenuItem("Nouvelle");
		itemArreterPartie = new JMenuItem("Arrêter");
		
		itemArreterPartie.setEnabled(false);
		
		itemNouvellePartie.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				partie.nouvelle();
				updateTabCases();
				itemArreterPartie.setEnabled(true);
				itemNouvellePartie.setEnabled(false);
				itemCoupSuivantAide.setEnabled(true);
				itemSolutionAide.setEnabled(true);
				itemImageParametre.setEnabled(false);
				itemCouleurParametre.setEnabled(false);
	        }
		});
		
		itemArreterPartie.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				partie.arreter();
				updateTabCases();
				itemArreterPartie.setEnabled(false);
				itemNouvellePartie.setEnabled(true);
				itemCoupSuivantAide.setEnabled(false);
				itemSolutionAide.setEnabled(false);
				itemImageParametre.setEnabled(true);
				itemCouleurParametre.setEnabled(true);
	        }
			
		});
		
		menuPartie.add(itemNouvellePartie);
		menuPartie.add(itemArreterPartie);
		
		itemCouleurParametre = new JMenuItem("Couleur");
		itemImageParametre = new JMenuItem("Image");
		itemImageParametre.setEnabled(true);
		
		itemCouleurParametre.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				couleur = JColorChooser.showDialog(null, "couleur du fond", couleur);
				updateTabCases();
	        }
		});
		
		itemImageParametre.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				JFileChooser dialogue = new JFileChooser(new File("."));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & png", "jpg", "png");
				dialogue.setFileFilter(filter);
				
				BufferedImage img = null;
				boolean imageValide = false;
				
				while(!imageValide && dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File fichier = dialogue.getSelectedFile();			 
					try {
						img = ImageIO.read(fichier);
						int w = img.getWidth();
						int h = img.getHeight();
						int scrH = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
						if(w != h)
							JOptionPane.showMessageDialog(null, "Veuillez sélectionner une image carrée", "Erreur", JOptionPane.ERROR_MESSAGE);
						else if(w < 100 || w > scrH - 50) {
							JOptionPane.showMessageDialog(null, "Veuillez sélectionner une image d'une taille comprise entre 100 et " + (scrH - 50) + " pixels", "Erreur", JOptionPane.ERROR_MESSAGE);
						} else {
							imageValide = true;
							imageSize = w;
						}
					} catch(IOException ioe) {
						JOptionPane.showMessageDialog(null, "" + ioe, "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if(imageValide) {
					updateImagesCases(img);
					updateTabCases();
				}
	        }
		});
		
		menuParametre.add(itemCouleurParametre);
		menuParametre.add(itemImageParametre);
		
		JMenuItem itemRegleAide = new JMenuItem("Règles");
		itemCoupSuivantAide = new JMenuItem("Coup Suivant");
		itemCoupSuivantAide.setEnabled(false);
		itemSolutionAide = new JMenuItem("Solution");
		itemSolutionAide.setEnabled(false);
		itemImageParametre.setEnabled(true);
		itemCouleurParametre.setEnabled(true);
		
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

				 t = new Timer();
				 t.schedule(new AutomaticSolution(), 0, 1*700);
	        }
		});
		
		menuAide.add(itemRegleAide);
		menuAide.add(itemCoupSuivantAide);
		menuAide.add(itemSolutionAide);
		
		return menuBar;
	}
	class AutomaticSolution extends TimerTask {
	    int nbrRepetitions = 3;

	    public void run() {
	      solution();
		     try{
			      if(partie.getGrille().getPileCoups().getNbCoup()==0){
			    	  t.cancel();
			      }
		     }catch(NullPointerException e){
		    	 t.cancel();
		     }
	    }
	}
	
	private void aideCoupSuivant(){
		Direction prochainCoup = partie.getGrille().getPileCoups().getResolution();
		partie.getGrille().getPileCoups().depiler();
		if(prochainCoup == Direction.BAS){
			partie.deplacer(Direction.HAUT);
		}
		if(prochainCoup == Direction.HAUT){
			partie.deplacer(Direction.BAS);
		}
		if(prochainCoup == Direction.DROITE){
			partie.deplacer(Direction.GAUCHE);
		}
		if(prochainCoup == Direction.GAUCHE){
			partie.deplacer(Direction.DROITE);
		}
		updateTabCases();
		fin();
	}
	
	private void solution() {
		
			Direction prochainCoup = partie.getGrille().getPileCoups().getResolution();
			partie.getGrille().getPileCoups().depiler();
			if(prochainCoup == Direction.BAS){
				partie.deplacer(Direction.HAUT);
			}
			if(prochainCoup == Direction.HAUT){
				partie.deplacer(Direction.BAS);
			}
			if(prochainCoup == Direction.DROITE){
				partie.deplacer(Direction.GAUCHE);
			}
			if(prochainCoup == Direction.GAUCHE){
				partie.deplacer(Direction.DROITE);
			}
			updateTabCases();
			fin();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}
	
	private void updateCases(int[][] matrice) {
		int cpt = 0;
		for(int i = 0; i < matrice.length; i++){
			for(int j = 0; j < matrice[i].length; j++){
				if(matrice[i][j] == 0){
					cases[cpt].setEnabled(false);
				} else {
					if(imagesCases == null)
						cases[cpt].setText(""+matrice[i][j]);
					else
						cases[cpt].setIcon(new ImageIcon(imagesCases[matrice[i][j] - 1]));
				}
				cpt++;
			}
		}
	}
	
	private void updateImagesCases(BufferedImage img) {		
		int dim = 4;
		int cdim = img.getWidth() / dim;
		imagesCases = new Image[dim * dim];
		int cpt = 0;
        for(int x = 0; x < dim; x++) {
            int sx = x * cdim;
            for(int y = 0; y < dim; y++) {
		 	int sy = y * cdim;
			    imagesCases[cpt] = Toolkit.getDefaultToolkit().createImage(
			        new FilteredImageSource(
			            img.getSource(),
			            new CropImageFilter(sy, sx, cdim, cdim)));
			    cpt++;
            }
        }
	}

	public void updateTabCases(){
		panneauDeJeu.removeAll();
		int v = 0;
		int w = 0;
		for(int i = 0; i < 16; i++) {
			
			if(i%4 ==0 && i!= 0){
				v = 0;
				w ++;
			}
			JButton bouton = new JButton();
			if(imagesCases != null) {
				bouton.setPreferredSize(new Dimension(imageSize, imageSize));
			}
			bouton.setFont(new Font("Arial", Font.PLAIN, 20));
			bouton.addActionListener(new EcouteurCase(v, w, partie, this));
			bouton.setBackground(couleur);
			bouton.setOpaque(true);
			bouton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			cases[i] = bouton;
			panneauDeJeu.add(cases[i]);
			v++;
		}
		
		updateCases(partie.getGrille().getMatrice());
	}

	public void fin(){
		int[][] matrice = partie.getGrille().getMatrice();
		int cpt = 1;
		boolean gagner = true;
		
		for(int i = 0; i < matrice.length; i++){
			for(int j = 0; j < matrice[i].length; j++){
				if(matrice[i][j] != cpt && matrice[i][j] > 0){
					gagner = false;
					break;
				} 
				cpt++;
			}
		}
		if(gagner){
			JOptionPane.showMessageDialog(this, "Vous avez gagné !", "BRAVO !!!", JOptionPane.PLAIN_MESSAGE,null);
			partie.arreter();
			updateTabCases();
			itemArreterPartie.setEnabled(false);
			itemNouvellePartie.setEnabled(true);
			itemCoupSuivantAide.setEnabled(false);
			itemSolutionAide.setEnabled(false);
			itemImageParametre.setEnabled(false);
			itemImageParametre.setEnabled(true);
			itemCouleurParametre.setEnabled(true);
		}
	}
	
	public static void main(String[] args) {
		FenetrePrincipale fenetre = new FenetrePrincipale();
	}
}
