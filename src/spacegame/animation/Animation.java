
package spacegame.animation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import java.util.ArrayList;
import java.util.Arrays;

public class Animation {

	private int speed;
	private int frames;
	private int index = 60;
	private int count = -1;

	private BufferedImage currentImg;

	private ArrayList<BufferedImage> images = new ArrayList<>();

	public Animation(int speed, BufferedImage... images) {
		this.speed = speed;
		this.frames = images.length - 1;
		this.images.addAll(Arrays.asList(images));
	}

	public void runAnimation() {
		index++;
		if (index > speed) {
			index = 0;
			nextFrame();
		}
	}

	public void nextFrame() {

		if (count < frames) {
			count++;
		} else {
			count = 0;
		}
		currentImg = images.get(count);
	}

	public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

		double rads = Math.toRadians(angle);
		double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
		int w = img.getWidth();
		int h = img.getHeight();
		int newWidth = (int) Math.floor(w * cos + h * sin);
		int newHeight = (int) Math.floor(h * cos + w * sin);

		BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = rotated.createGraphics();
		AffineTransform at = new AffineTransform();
		at.translate((newWidth - w) / 2, (newHeight - h) / 2);

		int x = w / 2;
		int y = h / 2;

		at.rotate(rads, x, y);
		g2d.setTransform(at);
		g2d.drawImage(img, 0, 0, null);
		g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
		g2d.dispose();

		return rotated;
	}

	public void drawAnimation(Graphics g, double x, double y, double angle) {
		rotateImageByDegrees(currentImg, (angle) % 360);
	}

	public void drawAnimation(Graphics g, double x, double y, int offsetX, int offsetY) {
		g.drawImage(currentImg, (int) x - offsetX, (int) y - offsetY, null);
	}

	public void drawAnimation(Graphics g, double x, double y) {
		g.drawImage(currentImg, (int) x, (int) y, null);
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getFrames() {
		return this.frames;
	}

	public ArrayList<BufferedImage> getImages() {
		return this.images;
	}

	public void setImages(ArrayList<BufferedImage> images) {
		this.images = images;
	}

}