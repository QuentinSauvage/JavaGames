import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JSlider;

public class SlidePlateau extends JSlider{
	private static final long serialVersionUID = 1L;
	private PanneauChoix panneauChoix;
	
	public SlidePlateau(int i, int j, PanneauChoix pc) {
		super(i, j);
		this.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
		panneauChoix = pc;
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				panneauChoix.updateLabel();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		this.setMajorTickSpacing(5);
		this.setMinorTickSpacing(1);
		this.setPaintTicks(true);
		this.setPaintLabels(true);
	}
}
