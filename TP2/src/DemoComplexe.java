
public class DemoComplexe {

	public static void main(String[] args) {
		Complexe c1 = new Complexe(5, 3);
		Complexe c2 = new Complexe(2,7);
		System.out.println(c1.affiche());
		System.out.println("Module : " + c1.module());
		Complexe c3 = c1.somme(c2);
		System.out.println(c3.affiche());
		c3 = (c1.multiplication(c2));
		System.out.println(c3.affiche());
	}

}
