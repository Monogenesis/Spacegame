import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	
	
	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent e) {
	
		System.out.println("entered");
		System.out.println(e.getPoint());
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		


	}

	public void mouseReleased(MouseEvent e) {
		
		
		
		int mx = e.getX();
		int my = e.getY();

		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220 && my >= 150 && my <= 200) {
			// pressed playbutton
			Game.state = Game.STATE.Game;
			
		}
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220 && my >= 250 && my <= 300) {
			// pressed playbutton
			Game.state = Game.STATE.Help;

		}
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220 && my >= 350 && my <= 400) {
			System.exit(1);

		}

	}

}
