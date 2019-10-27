import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
 
public class Fenetre extends JFrame {
	private JPanel pan = new JPanel();
	private JLabel l;
	private JSlider slide;
	
  public Fenetre(){             
	this.setTitle("Animation");
	this.setSize(300, 150);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	l = new JLabel("0");
	JPanel b4 = new JPanel();
	slide = new JSlider(0, 50);
	slide.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			updateLabel();
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
	JButton b = new JButton("OK");
	b.addActionListener(new ActionListener(){
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(slide.getValue());
		}
		  
	});
	slide.setMajorTickSpacing(5);
	slide.setMinorTickSpacing(1);
	slide.setPaintTicks(true);
	slide.setPaintLabels(true);
	b4.add(slide);
	b4.add(b);
	b4.add(l);
	this.getContentPane().add(b4);
	this.setVisible(true);
  }
  
  public void updateLabel() {
	  l.setText(String.valueOf(slide.getValue()));
  }
}