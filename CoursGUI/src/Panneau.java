import java.awt.Graphics;
import javax.swing.JPanel;
 
public class Panneau extends JPanel { 
  public void paintComponent(Graphics g){
	  int x1 = this.getWidth()/4;
	  int y1 = this.getHeight()/4;
	  g.fillOval(0, 0, this.getWidth()/2, this.getHeight()/2);
	  g.drawOval(x1, y1, this.getWidth()/2, this.getHeight()/2);
  }               
}