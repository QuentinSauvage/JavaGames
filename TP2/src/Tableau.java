public class Tableau {
	private int[] tab;
	
	public Tableau(){
		tab = new int[0];
	}
	
	public Tableau(int sz){
		tab = new int[sz];
	}
	
	public void remplir(){
		for(int i = 0; i < tab.length; i++){
			tab[i] = (int)(Math.random()*(100-50)) + 50;
		}
	}
	
	public int max(){
		int m = 0;
		for(int i = 0; i < tab.length; i++){
			if(tab[i] > m)
				m = tab[i];
		}
		return m;
	}
	
	public void affiche(){
		for(int i = 0; i < tab.length; i++){
			System.out.println(tab[i]);
		}
	}
}
