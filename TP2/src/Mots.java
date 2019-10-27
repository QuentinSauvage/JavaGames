public class Mots {
	public Mots(){

	}

	public static boolean nombresMot(String mot){
		int nbVoyelles = 0;
		char tab[] = new  char[]{'a', 'e', 'i', 'o', 'u', 'y'};
		for(int i = 0; i < mot.length(); i++){
			boolean voy = false;
			for(char c : tab){
				System.out.println('1');
				if(mot.charAt(i) == c){
					voy = true;
					break;
				}
			}
			System.out.println('1');
			if(voy){
				nbVoyelles++;
				System.out.println('1');
				if(nbVoyelles > mot.length()/2){
					return true;
				}
			}
		}
		return false;
	}
}
//Les mots les plus longs à tester seront ceux qui auront exactement 50% de 'y', et 50% de consonnes
