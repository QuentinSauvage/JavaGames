import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JSlider;

@SuppressWarnings("serial")
public class SlideChoix extends JSlider implements MouseListener{
	private PanneauChoix panneauChoix;

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
