
public class LibComplexe {

	
	public static double module(Complexe c){
		return Math.sqrt(Math.pow(c.getReelle(),2));
	}
	
	public static Complexe somme(Complexe c1, Complexe c2){
		return new Complexe(c1.getReelle() + c2.getReelle(), c1.getImaginaire() + c2.getImaginaire());
	}
	
	public static Complexe multiplication(Complexe c1, Complexe c2){
		return new Complexe(c1.getReelle()*c2.getReelle() - c1.getImaginaire()*c2.getImaginaire(), c1.getReelle()*c2.getImaginaire() - c1.getImaginaire()*c2.getReelle());
	}

}
