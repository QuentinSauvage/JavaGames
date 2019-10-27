import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JSlider;

/**
 * Crée un JSlider avec des propriétés spécifiques.
 * @author Quentin Sauvage
 *
 */
public class SlideChoix extends JSlider implements MouseListener{
	/**
	 * L'ID de SlideChoix, générée automatiquement;
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Un panneau mettant à jour les informations entrées.
	 */
	private PanneauChoix panneauChoix;
	
	/**
	 * Constructeur du SlideChoix en fonction des paramètres donnés.
	 * @param i La borne inférieure.
	 * @param j La borne supérieure.
	 * @param pc Le panneau dans lequel sont affichés les variables.
	 * @param space L'écart entre chaque valeur du slider.
	 */
	public SlideChoix(int i, int j, PanneauChoix pc, int space) {
		super(i, j);
		this.setForeground(Color.WHITE);
		this.setBackground(new Color(51, 51, 51));
		panneauChoix = pc;
		addMouseListener(this);
		
		this.setMajorTickSpacing(space);
		this.setMinorTickSpacing(1);
		this.setPaintTicks(true);
		this.setPaintLabels(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		panneauChoix.updateLabel();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
}
