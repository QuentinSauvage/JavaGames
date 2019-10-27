package fr.univ_artois;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Jeu {
	private Fenetre fenetre;
	
	public Jeu(){
		fenetre = new Fenetre(this);
	}
	
	public String genererMot(){
		String line = "";
		int nbLignes = 0;
		File f = new File("mots.txt");
		int i = 0;
		try{
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while(br.readLine() != null)
				nbLignes++;
			i = (int)(Math.random()*(nbLignes));
			br.close();
			br = new BufferedReader(new FileReader(f));
			while(i >= 0){
				i--;
				line = br.readLine();
			}
			br.close();
			fr.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return line;
	}
	
	public void recommencer(boolean b){
		if(b)
			//regenerer mot
			fenetre = new Fenetre(this);
		else
			fenetre.auRevoir();	
	}
}
