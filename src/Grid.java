import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Grid extends Board {

	public Boolean visible;

	public Grid() {

	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
		System.out.println("test");
	}

	@Override
	public void paintComponent(Graphics g) {

		if (visible = true)
			drawDonut(g);
	}

	private void drawDonut(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		Dimension size = getSize();

		System.out.println(size);

		for (int i = 0; i <= 1200; i += 20) {

			// Draw Grit Layout
			g2d.drawLine(i, 0, i, (int) size.getHeight());
			g2d.drawLine(0, i, (int) size.getWidth(), i);
			g2d.setColor(Color.lightGray);

		}

	}

}