package view;

import java.util.ArrayList;

/**
 * Met à jour les Observables.
 * @author Quentin Suavage
 *
 */
public interface Observer {
	
	public void update(int x, int y, int tour, ArrayList<int[]> al, int s1, int s2, String str);
	public void update(String str);
}
